package net.app.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@ComponentScan(basePackages = "net.app.base")
@EntityScan(basePackages = "net.app.base.model")
public class CrawlerReadFeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerReadFeedApplication.class, args);
    }
}
