package com.bhzq.marketinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bhzq.marketinfo.entity.vo.Initparam;

import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.bhzq.marketinfo.mapper"})
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
