package com.bambura.integrationApi;


import com.bambura.integrationApi.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class IntegrationApiApplication implements CommandLineRunner {

    @Autowired
    IntegrationService integrationService;

    public static void main(String[] args)  {
        SpringApplication.run(IntegrationApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        integrationService.initialRepository();
    }
}
