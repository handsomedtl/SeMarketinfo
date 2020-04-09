package com.bhzq.marketinfo.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhzq.marketinfo.common.ApiResponse;
import com.bhzq.marketinfo.entity.domain.BaseInfo;
import com.bhzq.marketinfo.entity.domain.Dict;
import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.vo.ChartDataSet;
import com.bhzq.marketinfo.entity.vo.ChartDataSet.ColorType;
import com.bhzq.marketinfo.entity.vo.Initparam;
import com.bhzq.marketinfo.service.LogService;
import com.bhzq.marketinfo.service.MarketInfoService;

import cn.hutool.core.util.ObjectUtil;


@Controller
@RequestMapping("/marketinfo")
public class MarketInfoController {
	
	@Autowired
	private MarketInfoService marketInfoService;
	
	@Autowired
	private Initparam initParam;
	
	@Autowired
	private LogService logService;
	
	
	/**
	 * 获取资讯信息
	 * @param type 信息类型 1：指数     2：期货     3：资讯
	 * @param codes 代码，可以用逗号分割
	 * @return
	 */
    @GetMapping
    public ResponseEntity<ApiResponse> messageList(Integer type, String codes,
    		HttpServletRequest request) {
    	
    	logService.addLog(LogEntity.create(LogEntity.LogType.SERVICE, 
				LogEntity.Level.INFO, request.getRemoteAddr() + 
				" type=" + type + " codes=" + codes +
    			" 请求到来   url:/marketinfo/messageList"));
		
		ResponseEntity<ApiResponse> validStatus = marketInfoService.isValidUser(request.getHeader("user"), request.getHeader("password"));
		
		if(HttpStatus.OK != validStatus.getStatusCode()){
			return validStatus;
		}		
    	
        if (ObjectUtil.isNull(type)) {
        	logService.addLog(LogEntity.create(LogEntity.LogType.SERVICE, 
    				LogEntity.Level.ERROR, "type参数不能为空"));
        	
        	return new ResponseEntity<>(ApiResponse.msg("type参数不能为空"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isEmpty(codes)) {
        	logService.addLog(LogEntity.create(LogEntity.LogType.SERVICE, 
    				LogEntity.Level.ERROR, "codes参数不能为空，以逗号分割"));
        	return new ResponseEntity<>(ApiResponse.msg("codes参数不能为空"), HttpStatus.BAD_REQUEST);
        }
       
        List <String> codeList = Arrays.asList(codes.split(","));
        List<BaseInfo> resultList =  marketInfoService.list(type, codeList);
        if(null == resultList)
        	return new ResponseEntity<>(ApiResponse.msg("不支持的信息类型，type=" + type), HttpStatus.BAD_REQUEST);
        
        return ResponseEntity.ok(ApiResponse.ok(resultList));
    }
    
    @GetMapping("init-chart")
    public ResponseEntity<ApiResponse> initChart() throws CloneNotSupportedException {    	
    	
    	long time = System.currentTimeMillis();
    	//取得最数据大类别：期货、指数 ...
    	List<Dict> typeList = marketInfoService.typeList();
    	List<String> labels = new ArrayList<>();
    	List<String> labels1 = new ArrayList<>();
    	ChartDataSet [] dataSets = new ChartDataSet[typeList.size()];
    	ChartDataSet [] dataSets1 = new ChartDataSet[typeList.size()];
    	
    	for(int i=0; i< typeList.size();i++){
    		//准备第一个图表数据（条形图）
    		ChartDataSet cdata = new ChartDataSet();
    		cdata.setLabel(typeList.get(i).getTypeName());
    		cdata.setBackgroundColor(ChartDataSet.ColorType.getColor( i% ColorType.values().length));
    		cdata.setBorderColor(cdata.getBackgroundColor());
    		
        	List<Integer> data = new ArrayList<>();
        	for(int j = 0; j< initParam.getTimecount(); j++){
        		Timestamp startTime = new Timestamp(time -(j+1)*1000*initParam.getTimespan());
        		Timestamp endTime = new Timestamp(time -j*1000*initParam.getTimespan());
        		if(0 == i){
        			DateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        			labels.add(0, sdf.format(endTime));
        		}
        		//倒序
        		data.add(0, marketInfoService.findCount(startTime, endTime, typeList.get(i).getType()));         		
        	}        	
        	
        	cdata.setData(data); 
        	dataSets[i] = cdata;
        	
        	//准备第二个图表数据（柱状图）
        	ChartDataSet cdata1 = (ChartDataSet) cdata.clone();
        	List<Integer> data1 = new ArrayList<>();
			for (int k = 0; k < initParam.getDatecount(); k++) {
				Timestamp startDate ,endDate;				
				if(0 == k){//今天日期处理
        			startDate = toBegin(new Timestamp(time));
        			endDate = new Timestamp(time);
        		}
				else{
					startDate = toBegin(new Timestamp(time-k*86400000l));    				
    				endDate = toEnd(new Timestamp(time-k*86400000l));
				}
				if(0 == i){
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    			labels1.add(0, sdf.format(endDate)); 
				}
				//倒序
        		data1.add(0, marketInfoService.findCount(startDate, endDate, typeList.get(i).getType())); 
			}
			cdata1.setData(data1);
			dataSets1[i] = cdata1;
    	}
    	
    	return ResponseEntity.ok(ApiResponse.ok(cn.hutool.core.lang.Dict.create().set("labels",labels).
    			set("dataSets", dataSets).set("timeSpan", initParam.getTimespan()).set("dataSets1", dataSets1).
    			set("labels1", labels1)));
    }
    
    @GetMapping("update-chart")
    public ResponseEntity<ApiResponse> updateChart(){
    	cn.hutool.core.lang.Dict result= cn.hutool.core.lang.Dict.create();
    	
    	long time = System.currentTimeMillis();
    	//取得最数据大类别：期货、指数 ...
    	List<Dict> typeList = marketInfoService.typeList();
    	List<cn.hutool.core.lang.Dict> datas = new ArrayList<>();
    	List<cn.hutool.core.lang.Dict> datas1 = new ArrayList<>();
    	for(int i=0; i< typeList.size();i++){    		
    		Timestamp startTime = new Timestamp(time -1000*initParam.getTimespan());
    		Timestamp endTime = new Timestamp(time);
    		
    		cn.hutool.core.lang.Dict dict = cn.hutool.core.lang.Dict.create().set("label",typeList.get(i).getTypeName());
    		dict.set("data",marketInfoService.findCount(startTime, endTime, typeList.get(i).getType()));
    		datas.add(dict);
    		
    		//每五分钟更新一次统计信息
    		if(endTime.getMinutes() % 5 == 0 && endTime.getSeconds() <=10){
    			dict = cn.hutool.core.lang.Dict.create().set("label",typeList.get(i).getTypeName());
        		dict.set("data",marketInfoService.findCount(toBegin(startTime), endTime, typeList.get(i).getType()));    			
    			datas1.add(dict);
    		}
    	}    	
    	
    	DateFormat sdf = new SimpleDateFormat("HH:mm:ss");    	
    	result.set("datas", datas).set("time", sdf.format(new Timestamp(time)));
    	if(!datas1.isEmpty())
    		result.set("datas1", datas1);
    	
    	return ResponseEntity.ok(ApiResponse.ok(result));
    }
    
    @SuppressWarnings("deprecation")
	private Timestamp toBegin(Timestamp timestamp){
    	timestamp.setHours(0);
    	timestamp.setMinutes(0);
    	timestamp.setSeconds(0);
    	timestamp.setNanos(0);
    	
    	return timestamp;
    }
    
    @SuppressWarnings("deprecation")
	private Timestamp toEnd(Timestamp timestamp){
    	timestamp.setHours(23);
    	timestamp.setMinutes(59);
    	timestamp.setSeconds(59);
    	
    	return timestamp;
    }
    
    

}
