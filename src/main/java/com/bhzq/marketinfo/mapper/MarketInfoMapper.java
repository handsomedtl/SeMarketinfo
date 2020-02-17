package com.bhzq.marketinfo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bhzq.marketinfo.entity.domain.BaseInfo;
import com.bhzq.marketinfo.entity.domain.Dict;


@Component
public interface MarketInfoMapper {
	
	List<BaseInfo> indexList(List<String> codeList);
	List<BaseInfo> newsList(List<String> codeList);
	List<Dict> typeList();
}
