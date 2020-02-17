package com.bhzq.marketinfo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhzq.marketinfo.common.ApiResponse;
import com.bhzq.marketinfo.entity.domain.LogEntity;
import com.bhzq.marketinfo.entity.vo.SearchLog;
import com.bhzq.marketinfo.service.LogService;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;


@Controller
@RequestMapping("/log")
public class LogController {
	
	private final LogService logService;

    @Autowired
    public LogController(LogService jobService) {
        this.logService = jobService;
    }
	
    @GetMapping
    public ResponseEntity<ApiResponse> jobList(Integer currentPage, Integer pageSize,SearchLog searchLog) {
        if (ObjectUtil.isNull(currentPage)) {
            currentPage = 1;
        }
        if (ObjectUtil.isNull(pageSize)) {
            pageSize = 10;
        }        
        
        PageInfo<LogEntity> all = logService.list(currentPage, pageSize,searchLog);
        return ResponseEntity.ok(ApiResponse.ok(Dict.create().set("total",all.getTotal()).set("data", all.getList())));
    }
    
    /**
     * 保存定时任务
     */
    @GetMapping("add")
    public ResponseEntity<ApiResponse> addJob(@Valid LogEntity logEntity) {
        try {
        	logService.addLog(logEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.msg(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ApiResponse.msg("操作成功"), HttpStatus.CREATED);
    }

}
