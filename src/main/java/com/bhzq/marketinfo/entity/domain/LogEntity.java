package com.bhzq.marketinfo.entity.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * <p>
 * 日志实体类
 * </p>
 *
 * @description: 日志实体类
 * @author: terry.duan
 * @date: Created in 2020-01-21 15:05
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 */
@Data
public class LogEntity implements Serializable{

	private static final long serialVersionUID = 4078779153053517544L;
	
	/**
     * 日志ID
     */
    private long id;
    /**
     * 日志类型
     */
    private String type;
    
    /**
     * 日志级别
     */
    private String level;
           
    /**
     * 内容
     */
    private String content;  
    
    /**
     * 产生时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp insertTime;
    
    public static LogEntity create(LogType type,Level level,String msg){
    	LogEntity log = new LogEntity();
    	log.setContent(msg);
    	log.setInsertTime(new Timestamp(System.currentTimeMillis()));
    	log.setLevel(String.valueOf(level.index));
    	log.setType(String.valueOf(type.index));
    	
    	return log;
    }
    
    /**
     * 日志分类
     * @author terry.duan
     */
    public static enum LogType {
    	INTERNAL("内部运行",1),SERVICE("HTTP服务",2),EXTRACT("行情采集",3),OTHER("其它",4);
    	
    	// 成员变量
        private String name;
        private int index;
        
    	private LogType(String name,int index){
    		this.name = name;
            this.index = index;
    	}

    }
    
    /**
     * 日志分类
     * @author terry.duan
     */
    public static enum Level {
    	DEBUG("调试",1),INFO("正常",2),WARN("警告",3),ERROR("错误",4);
    	
    	// 成员变量
        private String name;
        private int index;
        
    	private Level(String name,int index){
    		this.name = name;
            this.index = index;
    	}

    }
    
}