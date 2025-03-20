package com.biocorp.intento2;

import com.biocorp.intento2.auth.AuthService;
import com.biocorp.intento2.auth.SignUpRequest;
import com.biocorp.intento2.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-security.yml")
public class WebBioIntento2Application {

    public static void main(String[] args) {
        SpringApplication.run(WebBioIntento2Application.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthService authService){
        return args -> {
            var admin = SignUpRequest.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@biocorp.com")
                    .password("password")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Admin token: " + authService.signup(admin).getToken());

            var manager = SignUpRequest.builder()
                    .firstName("manager")
                    .lastName("manager")
                    .email("manager@biocorp.com")
                    .password("password")
                    .role(Role.MANAGER)
                    .build();
            System.out.println("Manager token: " + authService.signup(manager).getToken());
        };
    }
}

