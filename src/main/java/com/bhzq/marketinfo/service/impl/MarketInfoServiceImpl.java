package com.bhzq.marketinfo.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhzq.marketinfo.entity.domain.BaseInfo;
import com.bhzq.marketinfo.entity.domain.Dict;
import com.bhzq.marketinfo.mapper.MarketInfoMapper;
import com.bhzq.marketinfo.service.MarketInfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MarketInfoServiceImpl implements MarketInfoService {

	@Autowired
	private MarketInfoMapper marketInfoMapper;

	@Override
	public List<BaseInfo> list(Integer type, List<String> codeList) {
		List<BaseInfo> list = null;
		
		switch (type) {
		case 1: // 指数
			
		case 2:// 期货
			list = marketInfoMapper.indexList(codeList);
			break;
		case 3:// 资讯
			list = marketInfoMapper.newsList(codeList);
			break;
		default:
			break;
		}

		return list;
	}

	@Override
	public List<Dict> typeList() {		
		return marketInfoMapper.typeList();
	}

	@Override
	public int findCount(Timestamp startTime, Timestamp endTime, String category) {		
		return marketInfoMapper.findCount(startTime, endTime, category);
	}

}
