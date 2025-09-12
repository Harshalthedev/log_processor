package com.harshal.logs.log_processor.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harshal.logs.log_processor.model.LogEntry;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    // Later we can add custom queries like:
    // List<LogEntry> findByLevel(String level);
    List<LogEntry> findByTimestampAfter(LocalDateTime after);

}