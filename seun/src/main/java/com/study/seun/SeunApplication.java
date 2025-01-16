package com.study.seun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //create_at, updated_at 자동 업데이트
@SpringBootApplication
public class SeunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeunApplication.class, args);
	}

}
