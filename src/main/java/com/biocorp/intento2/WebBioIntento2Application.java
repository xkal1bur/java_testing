package com.biocorp.intento2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-security.yml")
public class WebBioIntento2Application {

    public static void main(String[] args) {
        SpringApplication.run(WebBioIntento2Application.class);
    }
}

