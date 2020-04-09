package com.bhzq.marketinfo.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bhzq.marketinfo.common.ApiResponse;
import com.bhzq.marketinfo.entity.domain.BaseInfo;
import com.bhzq.marketinfo.entity.domain.Dict;
import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.domain.LogEntity.LogType;
import com.bhzq.marketinfo.mapper.MarketInfoMapper;
import com.bhzq.marketinfo.service.LogService;
import com.bhzq.marketinfo.service.MarketInfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MarketInfoServiceImpl implements MarketInfoService {

	@Autowired
	private MarketInfoMapper marketInfoMapper;
	
	@Autowired
	private LogService logService;

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

	@Override
	public ResponseEntity<ApiResponse> isValidUser(String name,String password) {
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
			logService.addLog(LogEntity.create(LogEntity.LogType.SERVICE, 
					LogEntity.Level.ERROR, "name=" + name + " name或password为空"));
			
			return new ResponseEntity<>(ApiResponse.msg("header 中name/password参数不能为空"), HttpStatus.BAD_REQUEST);			
		}
		
		Map<String, String> map = marketInfoMapper.findUser(name.trim());
		
		if(null == map || !password.trim().equals(map.get("value"))){
			logService.addLog(LogEntity.create(LogEntity.LogType.SERVICE, 
					LogEntity.Level.ERROR, "name=" + name + " 用户名或密码错误"));
			
			return new ResponseEntity<>(ApiResponse.msg("用户名或密码错误"), HttpStatus.BAD_REQUEST);	
		}
		
		return ResponseEntity.ok(ApiResponse.msg("login success"));
	}

	/**
	 * @param category 如果category为空，则指数信息和资讯均进行删除
	 */
	@Override
	public void delete(Timestamp endTime, String category) {
		
		if(StringUtils.isEmpty(category)){
			marketInfoMapper.delete(endTime, "M");
			marketInfoMapper.delete(endTime, null);
		}
		else
			marketInfoMapper.delete(endTime, category);
		
	}

}
