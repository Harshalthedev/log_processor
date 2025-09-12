package com.harshal.logs.log_processor.ingest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.harshal.logs.log_processor.model.LogEntry;
import com.harshal.logs.log_processor.service.LogService;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class LogGenerator {
    private final LogService service;
    private final Random random = new Random();

    public LogGenerator(LogService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void generateLog() {
        LogEntry log = new LogEntry();
        log.setLevel(random.nextBoolean() ? "INFO" : "ERROR");
        log.setEndpoint(random.nextBoolean() ? "/api/login" : "/api/data");
        log.setLatency(50 + random.nextInt(500));
        log.setMessage("Simulated log entry");
        log.setTimestamp(LocalDateTime.now());

        service.saveLog(log);
        System.out.println("Generated log: " + log);
    }
}
