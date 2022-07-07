package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItchunApplication /*extends SpringBootServletInitializer */{

    public static void main(String[] args) {
        SpringApplication.run(ItchunApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(this.getClass());
//    }
}
