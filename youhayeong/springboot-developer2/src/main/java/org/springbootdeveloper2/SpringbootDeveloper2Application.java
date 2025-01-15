package org.springbootdeveloper2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootDeveloper2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDeveloper2Application.class, args);
    }

}
