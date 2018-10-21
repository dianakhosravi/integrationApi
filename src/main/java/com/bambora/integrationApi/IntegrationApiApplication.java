package com.bambora.integrationApi;


import com.bambora.integrationApi.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IntegrationApiApplication implements CommandLineRunner {

    @Autowired
    IntegrationService integrationService;

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        integrationService.initialRepository();
    }
}
