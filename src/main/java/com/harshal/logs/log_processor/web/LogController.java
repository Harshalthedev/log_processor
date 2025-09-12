package com.harshal.logs.log_processor.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.logs.log_processor.model.LogEntry;
import com.harshal.logs.log_processor.service.LogService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @PostMapping
    public LogEntry createLog(@RequestBody LogEntry log) {
        return service.saveLog(log);
    }

    @GetMapping
    public List<LogEntry> getLogs() {
        return service.getAllLogs();
    }
}
