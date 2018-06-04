package com.serendipity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Remember: SpringBootApplication brings in ComponentScan which only scans sub-packages by default,
 * so it's important for this to be in the base package.
 *
 * I've decided to use Postgresql + postgis
 * It seems to be the most popular and performant DB to use for spatial operations, and has all the relational
 * DB stuff I will need.
 */
@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
