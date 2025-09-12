package com.harshal.logs.log_processor.web;

import org.springframework.web.bind.annotation.*;

import com.harshal.logs.log_processor.model.LogEntry;
import com.harshal.logs.log_processor.service.LogService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class LogStatsController {
    private final LogService service;

    public LogStatsController(LogService service) {
        this.service = service;
    }

    @GetMapping("/errors")
    public Map<String, Object> getErrorStats() {
        return Map.of(
                "errorCount", service.countErrors(),
                "errorRate", service.errorRate()
        );
    }

    @GetMapping("/latency")
    public Map<String, Object> getLatencyStats() {
        return Map.of("averageLatency", service.averageLatency());
    }
    
    @GetMapping("/endpoints")
    public Map<String, Long> getTopEndpoints() {
        return service.topEndpoints();
    }

    @GetMapping("/levels")
    public Map<String, Long> getLogLevels() {
        return service.logLevelsCount();
    }
    
    @GetMapping("/errors/recent")
    public Map<String, Object> getRecentErrorStats(@RequestParam(defaultValue = "1") int minutes) {
        return service.errorStatsInLastMinutes(minutes);
    }


    @GetMapping("/logs/recent")
    public List<LogEntry> getRecentLogs(@RequestParam(defaultValue = "1") int minutes) {
        return service.getLogsAfterMinutes(minutes);
    }
    
    @GetMapping("/latency/recent")
    public Map<String, Object> getRecentLatency(@RequestParam(defaultValue = "1") int minutes) {
        return service.latencyStatsInLastMinutes(minutes);
    }

    @GetMapping("/endpoints/recent")
    public Map<String, Long> getRecentEndpoints(@RequestParam(defaultValue = "1") int minutes) {
        return service.endpointsInLastMinutes(minutes);
    }


}
