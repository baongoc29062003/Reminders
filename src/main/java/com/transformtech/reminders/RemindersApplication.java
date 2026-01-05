package com.transformtech.reminders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RemindersApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemindersApplication.class, args);
    }

}
