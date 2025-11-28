package com.tsd.libris;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tsd.libris.mapper")
public class LibrisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrisApplication.class, args);
	}

}
