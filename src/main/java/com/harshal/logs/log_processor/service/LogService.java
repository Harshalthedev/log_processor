package com.harshal.logs.log_processor.service;

import org.springframework.stereotype.Service;

import com.harshal.logs.log_processor.model.LogEntry;
import com.harshal.logs.log_processor.repository.LogEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LogService {
    private final LogEntryRepository repository;

    public LogService(LogEntryRepository repository) {
        this.repository = repository;
    }

    public LogEntry saveLog(LogEntry log) {
        return repository.save(log);
    }

    public List<LogEntry> getAllLogs() {
        return repository.findAll();
    }
    
    public long countErrors() {
        return repository.findAll().stream()
                .filter(log -> "ERROR".equalsIgnoreCase(log.getLevel()))
                .count();
    }
    
    public double errorRate() {
        long total = repository.count();
        if (total == 0) return 0.0;
        return (countErrors() * 100.0) / total;
    }
    

    public double averageLatency() {
        return repository.findAll().stream()
                .mapToLong(LogEntry::getLatency)
                .average()
                .orElse(0.0);
    }
    

    public Map<String, Long> topEndpoints() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(LogEntry::getEndpoint, Collectors.counting()));
    }

    public Map<String, Long> logLevelsCount() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));
    }

    
    public List<LogEntry> getLogsAfterMinutes(int minutes) {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(minutes);
        return repository.findByTimestampAfter(cutoff);
    }

    public Map<String, Object> errorStatsInLastMinutes(int minutes) {
        List<LogEntry> recentLogs = getLogsAfterMinutes(minutes);
        if (recentLogs.isEmpty()) {
            return Map.of("minutes", minutes, "totalRequests", 0, "errorCount", 0, "errorRate", 0.0);
        }

        long errors = recentLogs.stream()
                .filter(log -> "ERROR".equalsIgnoreCase(log.getLevel()))
                .count();

        return Map.of(
                "minutes", minutes,
                "totalRequests", recentLogs.size(),
                "errorCount", errors,
                "errorRate", (errors * 100.0) / recentLogs.size()
        );
    }
    
    public Map<String, Object> latencyStatsInLastMinutes(int minutes) {
        List<LogEntry> recentLogs = getLogsAfterMinutes(minutes);
        if (recentLogs.isEmpty()) {
            return Map.of("minutes", minutes, "averageLatency", 0.0);
        }

        double avgLatency = recentLogs.stream()
                .mapToLong(LogEntry::getLatency)
                .average()
                .orElse(0.0);

        return Map.of(
                "minutes", minutes,
                "averageLatency", avgLatency
        );
    }

    public Map<String, Long> endpointsInLastMinutes(int minutes) {
        List<LogEntry> recentLogs = getLogsAfterMinutes(minutes);
        return recentLogs.stream()
                .collect(Collectors.groupingBy(LogEntry::getEndpoint, Collectors.counting()));
    }

}