package com.example.demo.atomic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class AtomicService {
    static int count = 0;
    static int kbank_credit_tid = 0;
    static int ktb_auth_uid = 0;
    static int ktb_uid = 0;
    static int prt_reference_code = 0;
    static int scbv2_uid = 0;
    static AtomicInteger counter = new AtomicInteger(0);

    private final ConcurrentHashMap<String, AtomicInteger> stringAtomicIntegerConcurrentHashMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> stringIntegerConcurrentHashMap = new ConcurrentHashMap<>();

    synchronized static void counter() {
        count++;
    }

    synchronized int synchronizedStringIntegerConcurrentHashMap(String key) {
        int count = stringIntegerConcurrentHashMap.get("kbank_credit_tid");
        count++;
        return count;
    }

    public void getAtomicV0() throws InterruptedException {
        Instant start = Instant.now();
        long duration = 0;
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> count++);
        }

        executor.shutdown();

        boolean tasksCompleted = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (tasksCompleted) {
            duration = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Total processing time: " + duration);
            System.out.println("All tasks have completed.");
        } else {
            System.out.println("Timeout occurred while waiting for tasks to complete.");
        }

        System.out.println(count);
    }

    public void getAtomicV1() throws InterruptedException {
        Instant start = Instant.now();
        long duration = 0;
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> count++);
        }

        executor.shutdown();

        boolean tasksCompleted = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (tasksCompleted) {
            duration = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Total processing time: " + duration);
            System.out.println("All tasks have completed.");
        } else {
            System.out.println("Timeout occurred while waiting for tasks to complete.");
        }

        System.out.println(count);
    }

    public void getAtomicV2() throws InterruptedException {
        Instant start = Instant.now();
        long duration = 0;
        count = 0;
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> counter());
        }

        executor.shutdown();

        boolean tasksCompleted = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (tasksCompleted) {
            duration = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Total processing time: " + duration);
            System.out.println("All tasks have completed.");
        } else {
            System.out.println("Timeout occurred while waiting for tasks to complete.");
        }

        System.out.println(count);
    }

    public void getAtomicV3() throws InterruptedException {
        Instant start = Instant.now();
        long duration = 0;
        counter.set(0);
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> counter.getAndIncrement());
        }

        executor.shutdown();

        boolean tasksCompleted = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (tasksCompleted) {
            duration = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Total processing time: " + duration);
            System.out.println("All tasks have completed.");
        } else {
            System.out.println("Timeout occurred while waiting for tasks to complete.");
        }

        System.out.println(counter);
    }

    public void getAtomicV4() throws InterruptedException {
        Instant start = Instant.now();
        long duration = 0;
        List<Integer> list = new ArrayList<>();
        stringAtomicIntegerConcurrentHashMap.put("kbank_credit_tid", new AtomicInteger(0));

        ExecutorService executor = Executors.newFixedThreadPool(1000);


        for (int i = 0; i < 100000; i++) {
            executor.execute(() -> {
                int n = stringAtomicIntegerConcurrentHashMap.get("kbank_credit_tid").getAndIncrement();
                list.add(n);
            });
        }

        executor.shutdown();

        boolean tasksCompleted = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (tasksCompleted) {
            System.out.println("list: " + list.toString());
            duration = Duration.between(start, Instant.now()).toMillis();
            System.out.println("Total processing time: " + duration);
            System.out.println("All tasks have completed.");

            for (int j = 0; j < list.size(); j++) {
                for (int i = j + 1; i < list.size(); i++) {
                    if (list.get(j).equals(list.get(i))) {
                        System.out.println("j list: " + list.get(j));
                        System.out.println("i list: " + list.get(i));
                        System.out.println("Duplicated");
                    }
                }
            }
        } else {
            System.out.println("Timeout occurred while waiting for tasks to complete.");
        }

        System.out.println("kbank_credit_tid: " + stringAtomicIntegerConcurrentHashMap.get("kbank_credit_tid").get());
    }
}
