package com.improving.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args); //run Spring using this class, which we annotated as SpringBootApp)...this sets up a web server for us so we can start interacting with website via code
    }

}
