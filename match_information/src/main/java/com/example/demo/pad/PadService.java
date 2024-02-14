package com.example.demo.pad;

import com.example.demo.entity.RunningNumber;
import com.example.demo.models.pad.PadRequest;
import com.example.demo.repo.RunningNumberRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class PadService {

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String TIMESTAMP_FORMAT = "HHmmssSSS";
    public static final String DATE_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
    private static final String FORMATTER_SID_PID_DATE = "%1s%2$s";
    private static final String FORMATTER_KTB_AUTH_UID = "%1s%2$s";
    private static final String FORMATTER_KBANK_CREDIT = "%1s_%2$s";
    private static final String FORMATTER_TTB_UID = "%1s%2$s%3$s";
    private static final long MAX_VALUE_SID_PID_DATE = 46655L;
    private static final long MAX_VALUE_KTB_AUTH_UID = 99999L;
    private static final long MAX_VALUE_KBANK_CREDIT_TID = 999999999999999L;
    private final ConcurrentHashMap<String, RunningNumber> keys = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtomicLong> maps = new ConcurrentHashMap<>();
    @Autowired
    private RunningNumberRepository repo;

    @PostConstruct
    public void init() {
        repo.findAll().forEach(e -> keys.put(e.getRunningKey(), e));

        maps.put("kbank_credit_tid", new AtomicLong(0));
        maps.put("ktb_auth_uid", new AtomicLong(0));
        maps.put("ktb_uid", new AtomicLong(0));
        maps.put("prt_reference_code", new AtomicLong(0));
        maps.put("scbv2_uid", new AtomicLong(0));
        maps.put("ttb_uid", new AtomicLong(0));

        log.info("Initial running numbers hash map: " + keys);
        log.info("Initial maps: " + maps);
    }

    public synchronized String getRunningNumberV0(PadRequest req) {
        synchronized (keys) {
            try {
                RunningNumber number = keys.get(req.getKey());
                number.setRunningValue(number.getRunningValue() >= MAX_VALUE_SID_PID_DATE ? 1 : number.getRunningValue() + 1);
                log.info("RunningNumber: " + number);
                repo.save(number);
                String result = generateRunningNumber(number);
                log.info("Current running number36:{}({})", number.toString(), result);
                return result;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public synchronized String getKTBAuthRequestUIDV0(PadRequest req) {
        synchronized (keys) {
            try {
                RunningNumber number = keys.get(req.getKey());
                number.setRunningValue(number.getRunningValue() >= MAX_VALUE_KTB_AUTH_UID ? 1 : number.getRunningValue() + 1);
                log.info("RunningNumber: " + number);
                repo.save(number);
                String result = generateKTBAuthRequestUID(number);
                log.info("Current ktb auth req uid running number36:{}({})", number.toString(), result);
                return result;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public synchronized String getKBANKCreditRunningNumberV0(PadRequest req) {
        synchronized (keys) {
            try {
                RunningNumber number = keys.get(req.getKey());
                number.setRunningValue(number.getRunningValue() >= MAX_VALUE_KBANK_CREDIT_TID ? 1 : number.getRunningValue() + 1);
                log.info("RunningNumber: " + number);
                repo.save(number);
                String result = generateKBANKCreditRunningNumber(number);
                log.info("Current kbank credit running number:{}({})", number.toString(), result);
                return result;
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public synchronized String getRunningNumberV1(PadRequest req) {
        synchronized (keys) {
            RunningNumber number = keys.get(req.getKey());
            number.setRunningValue(number.getRunningValue() >= MAX_VALUE_SID_PID_DATE ? 1 : number.getRunningValue() + 1);
            log.info("RunningNumber for General: " + number.getRunningValue());
            String result = generateRunningNumber(number);
            log.info("Current running number36: {}", result);
            return result;
        }
    }

    public synchronized String getKTBAuthRequestUIDV1(PadRequest req) {
        synchronized (keys) {
            RunningNumber number = keys.get(req.getKey());
            number.setRunningValue(number.getRunningValue() >= MAX_VALUE_KTB_AUTH_UID ? 1 : number.getRunningValue() + 1);
            log.info("RunningNumber for KTB Auth: " + number.getRunningValue());
            String result = generateKTBAuthRequestUID(number);
            log.info("Current ktb auth req uid running number: {}", result);
            return result;
        }
    }

    public synchronized String getKBANKCreditRunningNumberV1(PadRequest req) {
        synchronized (keys) {
            RunningNumber number = keys.get(req.getKey());
            number.setRunningValue(number.getRunningValue() >= MAX_VALUE_KBANK_CREDIT_TID ? 1 : number.getRunningValue() + 1);
            log.info("RunningNumber for KBANK credit: " + number.getRunningValue());
            String result = generateKBANKCreditRunningNumber(number);
            log.info("Current kbank credit running number: {}", result);
            return result;
        }
    }

    public String getRunningNumberV2(PadRequest req) {
        long number = maps.get(req.getKey()).getAndIncrement();
        log.info("RunningNumber: " + number);
        String result = generateRunningNumber(number);
        log.info("Current running number: {}", result);
        return result;
    }

    public String getKTBAuthRequestUIDV2(PadRequest req) {
        long number = maps.get(req.getKey()).getAndIncrement();
        log.info("RunningNumber for KTB Auth: " + number);
        String result = generateKTBAuthRequestUID(number);
        log.info("Current ktb auth req uid running number: {}", result);
        return result;
    }

    public String getKBANKCreditRunningNumberV2(PadRequest req) {
        long number = maps.get(req.getKey()).getAndIncrement();
        log.info("RunningNumber for KBANK credit: " + number);
        String result = generateKBANKCreditRunningNumber(number);
        log.info("Current kbank credit running number: {}", result);
        return result;
    }

    public String getTTBRunningNumberV0(PadRequest req) {
        long number = maps.get(req.getKey()).getAndIncrement();
        log.info("RunningNumber for TTB uid: " + number);
        String result = generateTTBRunningNumber(number);
        log.info("Current ttb running number: {}", result);
        return result;
    }

    public void getRunningNumberWithThreads(PadRequest req) throws InterruptedException {
        List<Long> list = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 100000; i++) {
            executor.execute(() -> {
                        RunningNumber number = keys.get(req.getKey());
                        number.setRunningValue(number.getRunningValue() >= MAX_VALUE_SID_PID_DATE ? 1 : number.getRunningValue() + 1);
                        list.add(number.getRunningValue());
                        log.info("RunningNumber: " + number);
                    }
            );
        }

        executor.shutdown();

        boolean tasksCompleted = executor.awaitTermination(1, TimeUnit.MINUTES);
        if (tasksCompleted) {
            for (int j = 0; j < list.size(); j++) {
                for (int i = j + 1; i < list.size(); i++) {
                    if (list.get(j).equals(list.get(i))) {
                        System.out.println("j list: " + list.get(j));
                        System.out.println("i list: " + list.get(i));
                        System.out.println("Duplicated");
                    }
                }
            }

            System.out.println("All tasks have completed.");
        } else {
            System.out.println("Timeout occurred while waiting for tasks to complete.");
        }
    }

    private String generateRunningNumber(long number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        String partiDateTimeBase36 = new BigInteger(getDateTimeStampNow(), 10).toString(36);
        String nLpad = new BigInteger(String.valueOf(number), 10).toString(36);
        fmt.format(FORMATTER_SID_PID_DATE, padLeftZeros(partiDateTimeBase36, 13), padLeftZeros(nLpad, 3));
        return builder.toString();
    }

    private String generateKTBAuthRequestUID(long number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        String dateTimeBase36 = new BigInteger(getDateTimeStampNow(), 10).toString(36);
        String nLpad = String.valueOf(number);
        fmt.format(FORMATTER_KTB_AUTH_UID, dateTimeBase36, padLeftZeros(nLpad, 5));
        return builder.toString();
    }

    private String generateKBANKCreditRunningNumber(long number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String time = now.format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
        String nLpad = String.valueOf(number);
        fmt.format(FORMATTER_KBANK_CREDIT, date, time + padLeftZeros(nLpad, 15));
        return builder.toString();
    }

    private String generateRunningNumber(RunningNumber number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        String partiDateTimeBase36 = new BigInteger(getDateTimeStampNow(), 10).toString(36);
        String nLpad = new BigInteger(String.valueOf(number.getRunningValue()), 10).toString(36);
        fmt.format(FORMATTER_SID_PID_DATE, padLeftZeros(partiDateTimeBase36, 13), padLeftZeros(nLpad, 3));
        return builder.toString();
    }

    private String generateKTBAuthRequestUID(RunningNumber number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        String dateTimeBase36 = new BigInteger(getDateTimeStampNow(), 10).toString(36);
        String nLpad = String.valueOf(number.getRunningValue());
        fmt.format(FORMATTER_KTB_AUTH_UID, dateTimeBase36, padLeftZeros(nLpad, 5));
        return builder.toString();
    }

    private String generateKBANKCreditRunningNumber(RunningNumber number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String time = now.format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
        String nLpad = String.valueOf(number.getRunningValue());
        fmt.format(FORMATTER_KBANK_CREDIT, date, time + padLeftZeros(nLpad, 15));
        return builder.toString();
    }

    private String generateTTBRunningNumber(long number) {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String nLpad = String.valueOf(number);
        fmt.format(FORMATTER_TTB_UID, "FIN", now, padLeftZeros(nLpad, 3));
        return builder.toString();
    }

    public String padLeftZeros(String str, int length) {
        return String.format("%1$" + length + "s", str).replace(' ', '0');
    }

    public String getDateTimeStampNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIMESTAMP_FORMAT));
    }
}
