package com.bhzq.marketinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.vo.SearchLog;
import com.bhzq.marketinfo.mapper.LogMapper;
import com.bhzq.marketinfo.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogServiceImpl implements LogService {
	private final LogMapper logMapper;
	
	@Autowired
    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

	@Override
	public PageInfo<LogEntity> list(Integer currentPage, Integer pageSize,SearchLog searchLog) {
		log.debug("currentPage = " + currentPage + " pageSize=" + pageSize);
		
		PageHelper.startPage(currentPage, pageSize);
        List<LogEntity> list = logMapper.list(searchLog);
        return new PageInfo<>(list);
	}

	@Override
	public void addLog(LogEntity logEntity) {
		logMapper.add(logEntity);		
	}
}
