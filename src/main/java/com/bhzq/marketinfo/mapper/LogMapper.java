package com.bhzq.marketinfo.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.vo.SearchLog;


@Component
public interface LogMapper {
    /**
     * 查询日志日志列表
     * @return
     */
    List<LogEntity> list(SearchLog searchLog);
    
    void add(LogEntity log);
}
