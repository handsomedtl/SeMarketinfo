package com.bhzq.marketinfo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.bhzq.marketinfo.entity.vo.ChartDataSet;
import com.bhzq.marketinfo.service.MarketInfoService;

import cn.hutool.core.util.ObjectUtil;


@Controller
@RequestMapping("/marketinfo")
public class MarketInfoController {
	
	@Autowired
	private MarketInfoService marketInfoService;
	
	/**
	 * 获取资讯信息
	 * @param type 信息类型 1：指数     2：期货     3：资讯
	 * @param codes 代码，可以用逗号分割
	 * @return
	 */
    @GetMapping
    public ResponseEntity<ApiResponse> jobList(Integer type, String codes) {
        if (ObjectUtil.isNull(type)) {
        	return new ResponseEntity<>(ApiResponse.msg("type参数不能为空"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isEmpty(codes)) {
        	return new ResponseEntity<>(ApiResponse.msg("codes参数不能为空"), HttpStatus.BAD_REQUEST);
        }
       
        List <String> codeList = Arrays.asList(codes.split(","));
        List<BaseInfo> resultList =  marketInfoService.list(type, codeList);
                
        
        return ResponseEntity.ok(ApiResponse.ok(resultList));
    }
    
    @GetMapping("init-chart")
    public ResponseEntity<ApiResponse> initChart() {
    	List<ChartDataSet> cDataList = new ArrayList<>();
    	
        //取得最数据大类别：期货、指数 ...
    	List<Dict> typeList = marketInfoService.typeList();
    	for(int i=0; i< typeList.size();i++){
    		ChartDataSet cdata = new ChartDataSet();
    		cdata.setLabel(typeList.get(i).getTypeName());
    		cdata.setBackgroundColor(ChartDataSet.ColorType.getColor(i+1));
    		cdata.setBorderColor(cdata.getBackgroundColor());
    		cDataList.add(cdata);
    	}
    	
    	
    	//计算并每一时间段所入库的数据量
    	
    	
    	
    	
    	
        
        return ResponseEntity.ok(ApiResponse.ok(cDataList));
    }
    
    

}
