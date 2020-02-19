package com.bhzq.marketinfo.service;

import java.sql.Timestamp;
import java.util.List;

import com.bhzq.marketinfo.entity.domain.BaseInfo;
import com.bhzq.marketinfo.entity.domain.Dict;


public interface MarketInfoService {
    
    
	List<BaseInfo> list(Integer type,List<String> codeList);
	List<Dict> typeList();
	int findCount(Timestamp startTime,Timestamp endTime,String category);
    
   
}
