package com.wkx.myakkaredis;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
public class MyAkkaRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAkkaRedisApplication.class, args);
	}
}
