package com.example.es9log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Es9LogApp implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Es9LogApp.class);

    public static void main(String[] args) {
        SpringApplication.run(Es9LogApp.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("ES9 Log Generator started.");
    }

    // 5초마다 에러를 생성해 전송 (프로토타입용)
    @Scheduled(fixedDelay = 5000L)
    public void generateErrorLog() {
        try {
            causeError2();
        } catch (Exception e) {
            log.error("Intentional error for ES logging demo", e);
        }
    }

    private void causeError2() {
        Object x = new Object();
        x.toString(); // NPE

        int a= 5;
        int b= 0;
        int c= a / b; // ArithmeticException

    }
}
