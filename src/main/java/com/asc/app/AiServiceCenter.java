package com.asc.app;

import com.asc.post.processor.DotenvPostProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.asc")
@MapperScan("com.asc.mapper")
@EntityScan("com.asc.entity")
public class AiServiceCenter {

	public static void main(String[] args) {
		DotenvPostProcessor.loadEnv();

		SpringApplication.run(AiServiceCenter.class, args);
	}
}
