package com.example.tenpo.repository;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.tenpo.entity.LogEntry;

import java.util.Date;

@Repository
public interface LogEntryRepository extends PagingAndSortingRepository<LogEntry, Long> {
	Optional<LogEntry> findByTimestamp(Date date);

//	Boolean existsByUsername(String username);
//
//	Boolean existsByEmail(String email);
}
