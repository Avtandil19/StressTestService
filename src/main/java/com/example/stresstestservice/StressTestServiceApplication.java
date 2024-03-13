package com.example.stresstestservice;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class StressTestServiceApplication implements CommandLineRunner {
    private final StressTest stressTest;

    public static void main(String[] args) {
        SpringApplication.run(StressTestServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stressTest.makeRequests();
    }
}
