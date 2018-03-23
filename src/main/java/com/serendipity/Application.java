package com.serendipity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Remember: SpringBootApplication brings in ComponentScan which only scans sub-packages by default,
 * so it's important for this to be in the base package.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
