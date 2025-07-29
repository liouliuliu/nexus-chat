package com.nexus.chat.llm.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nexus.chat")
public class NexusChatBootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(NexusChatBootstrapApplication.class, args);
    }
}