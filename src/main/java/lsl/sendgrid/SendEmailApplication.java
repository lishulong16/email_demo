package lsl.sendgrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"lsl.sendgrid.controller", "lsl.sendgrid.service"})
public class SendEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendEmailApplication.class, args);

    }
}
