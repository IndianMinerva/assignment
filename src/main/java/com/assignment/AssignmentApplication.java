package com.assignment;

import com.assignment.service.GoogleSearchResultService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.assignment")
public class AssignmentApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.assignment");
        ctx.getBean(GoogleSearchResultService.class).getLinksFromGoogle("java");
    }
}
