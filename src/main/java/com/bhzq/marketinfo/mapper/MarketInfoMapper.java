package com.bhzq.marketinfo.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bhzq.marketinfo.entity.domain.BaseInfo;
import com.bhzq.marketinfo.entity.domain.Dict;


@Component
public interface MarketInfoMapper {
	
	List<BaseInfo> indexList(List<String> codeList);
	List<BaseInfo> newsList(List<String> codeList);
	List<Dict> typeList();
	int findCount(@Param("startTime") Timestamp startTime,@Param("endTime") Timestamp endTime,
			@Param("category") String category);
}
