package com.bambora.integrationApi;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.bambora.integrationApi.model")
public class MyConfig {
}


/*@Configuration
public class MyConfig {
    private int count = 1;

    @Bean("transaction")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Transaction createTransaction() {
        System.out.println("number of transactions: " + count++);
        return Transaction.builder().build();
    }
}*/
