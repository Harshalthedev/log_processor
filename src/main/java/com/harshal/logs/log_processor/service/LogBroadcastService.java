package com.harshal.logs.log_processor.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.harshal.logs.log_processor.model.LogEntry;

@Service
public class LogBroadcastService {
    private final SimpMessagingTemplate messagingTemplate;

    public LogBroadcastService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendLogUpdate(LogEntry log) {
        messagingTemplate.convertAndSend("/topic/logs", log);
    }
}
