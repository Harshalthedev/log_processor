package com.harshal.logs.log_processor.ingest;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.harshal.logs.log_processor.model.LogEntry;
import com.harshal.logs.log_processor.service.LogService;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LogFileReader {
    private final LogService service;
    private long lastKnownPosition = 0; // keep track of where we left off
    private static final String LOG_FILE_PATH = "logs/app.log";

    public LogFileReader(LogService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 5000) // check every 5 sec
    public void readNewLogs() {
        try {
            Path path = Paths.get(LOG_FILE_PATH);
            if (!Files.exists(path)) {
                System.out.println("Log file not found: " + LOG_FILE_PATH);
                return;
            }

            // Read the file as bytes
            byte[] fileBytes = Files.readAllBytes(path);
            String content = new String(fileBytes);

            // Split lines
            String[] lines = content.split("\\R");

            // Process only new lines
            for (int i = (int) lastKnownPosition; i < lines.length; i++) {
                parseAndSave(lines[i]);
            }

            lastKnownPosition = lines.length; // update pointer

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseAndSave(String line) {
        try {
            // Format: ERROR 2025-09-10 10:12:35 /api/data Timeout after 5000ms
            String[] parts = line.split(" ", 5);
            if (parts.length < 5) return;

            String level = parts[0];
            String timestampStr = parts[1] + " " + parts[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime timestamp = LocalDateTime.parse(timestampStr, formatter);

            String endpoint = parts[3];
            String message = parts[4];

            long latency = 0;
            if (message.contains("processed in")) {
                latency = Long.parseLong(message.replaceAll("[^0-9]", ""));
            }

            LogEntry log = new LogEntry();
            log.setLevel(level);
            log.setTimestamp(timestamp);
            log.setEndpoint(endpoint);
            log.setMessage(message);
            log.setLatency(latency);

            service.saveLog(log);

        } catch (Exception e) {
            System.out.println("Failed to parse line: " + line);
        }
    }
}