package com.openclassroom.paymybuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PaymybuddyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymybuddyApplication.class, args);
    }

}
