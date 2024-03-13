package com.example.stresstestservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Service
@RequiredArgsConstructor
public class StressTest {

    private final RestTemplate restTemplate;
    private Integer numThreads = 220;
    private String url = "http://localhost:8080/students/1";
    private Integer port = 8080;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(220);

    private class Task implements Runnable {


        @Override
        public void run() {
            try {
                System.out.println(cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            int id  =1;
            System.out.println("Beforeee-------------------------");
            Student student = restTemplate.getForObject(url, Student.class);
//            ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.GET, Student.class);
            System.out.println(student);
        }
    }

    public void makeRequests() {
        Task task = new Task();

        for (int i = 0; i < numThreads; i++) {
            new Thread(task).start();
        }
    }
}
