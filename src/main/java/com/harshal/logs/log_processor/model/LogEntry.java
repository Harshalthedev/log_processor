package com.harshal.logs.log_processor.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "logs")

public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;     // e.g., ERROR, INFO
    private String endpoint;  // e.g., /api/login
    private long latency;     // request time in ms
    private String message;   // log message
    private LocalDateTime timestamp; // 👈 use LocalDateTime instead of String
	public Long getId() {
		return id;
	}
	
	
	
	public LogEntry() {
		super();
	}

	public LogEntry(Long id, String level, String endpoint, long latency, String message, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.level = level;
		this.endpoint = endpoint;
		this.latency = latency;
		this.message = message;
		this.timestamp = timestamp;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public long getLatency() {
		return latency;
	}
	public void setLatency(long latency) {
		this.latency = latency;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
