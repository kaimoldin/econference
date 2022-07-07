package com.qd.econference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class EconferenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EconferenceApplication.class, args);
    }

}
