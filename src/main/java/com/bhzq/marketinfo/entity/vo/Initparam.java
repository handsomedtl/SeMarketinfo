package com.bhzq.marketinfo.entity.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="initparam")
public class Initparam {
	private int timespan;
	private int timecount;

}
