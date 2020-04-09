package com.bhzq.marketinfo.job;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.impl.JobDetailImpl;

import com.bhzq.marketinfo.entity.domain.BaseJob;
import com.bhzq.marketinfo.entity.domain.Dict;
import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.form.JobForm;
import com.bhzq.marketinfo.entity.vo.Initparam;
import com.bhzq.marketinfo.service.impl.LogServiceImpl;
import com.bhzq.marketinfo.service.impl.MarketInfoServiceImpl;
import com.bhzq.marketinfo.utils.SpringUtil;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 过期数据删除
 * @author terry
 */
@Slf4j
public class DataCleanJob implements BaseJob {


	@Override
	public void execute(JobExecutionContext context) {
		String command = context.getMergedJobDataMap().getString(JobForm.COMMAND);
		
		switch (command) {
			case "datacheck":
				log.info(((JobDetailImpl) context.getJobDetail()).getName() + " 执行了 ********");
				break;
	
			case "dataclean":
				log.info(((JobDetailImpl) context.getJobDetail()).getName() + " 执行了 ********");
				
				cleanOldData();
				
				break;
	
			default:
	
				break;

		}
	}
	
	/**
	 * 清理日志及旧的信息
	 */
	private void cleanOldData(){
		// 先统计今天的数据总量				
		long time = System.currentTimeMillis();
		Initparam initParam = SpringUtil.getBean(Initparam.class);
		LogServiceImpl logService = SpringUtil.getBean(LogServiceImpl.class);				
		
		MarketInfoServiceImpl marketInfoServ = SpringUtil.getBean(MarketInfoServiceImpl.class);
		List<Dict> typeList = marketInfoServ.typeList();
    	List<cn.hutool.core.lang.Dict> datas = new ArrayList<>();
    	for(int i=0; i< typeList.size();i++){    		
    		Timestamp startTime = new Timestamp(time);
    		cn.hutool.core.lang.Dict dict = cn.hutool.core.lang.Dict.create();
    		dict.set("type",typeList.get(i).getTypeName());
        	dict.set("count",marketInfoServ.findCount(toBegin(startTime), toBegin(new Timestamp(time + 86400000l)), typeList.get(i).getType()));
    		datas.add(dict);    		
    	} 
    	cn.hutool.core.lang.Dict dict = cn.hutool.core.lang.Dict.create();
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dict.set("date",sdf.format(new Date()));
		dict.set("data",datas);		    	
		
    	logService.addLog(LogEntity.create(LogEntity.LogType.OTHER, LogEntity.Level.INFO, JSONUtil.toJsonStr(dict)));
		
		// 进行日志及旧信息的清理操作	
		Timestamp endDate = toBegin(new Timestamp(time - initParam.getDatecount() * 86400000l));	
		marketInfoServ.delete(endDate, null);				
		Timestamp logDate = new Timestamp(time - initParam.getLogReserved() * 86400000l);
		logService.delete(toBegin(logDate));
	}
	
	@SuppressWarnings("deprecation")
	private Timestamp toBegin(Timestamp timestamp){
    	timestamp.setHours(0);
    	timestamp.setMinutes(0);
    	timestamp.setSeconds(0);
    	timestamp.setNanos(0);
    	
    	return timestamp;
    }
}