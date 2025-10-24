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

        try {
            causeError3();
        } catch (Exception e) {
            log.error("causeError3", e);
        }

        try {
            causeError4();
        } catch (Exception e) {
            log.error("causeError4", e);
        }

        try {
            causeError5();
        } catch (Exception e) {
            log.error("causeError5", e);
        }
    }

    private void causeError2() {
        Object x = null;
        if (x != null) {   // 패치 내용 반영
            x.toString();
        }
    }


    private void causeError3() {
        int a= 5;
        int b= 0;
        int c= a / b; // ArithmeticException
        log.info("causeError3" + c);
    }


    private void causeError4() {
        int[] arr = new int[1];
        int x2 = arr[5]; // ArrayIndexOutOfBoundsException
        log.info("causeError4: " + x2);
    }

    private void causeError5() {
        Object s = "hello";
        Integer i = (Integer) s; // ClassCastException
        log.info("causeError5: " + i);
    }
}
