package com.abnambro.customer.account.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class CustomerAccountManager implements CommandLineRunner {
    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(CustomerAccountManager.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataSource = " + dataSource);
    }
}