package com.example.tenpo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.tenpo.entity.LogEntry;
import com.example.tenpo.repository.LogEntryRepository;
import com.example.tenpo.repository.UserRepository;

@Service
public class LogEntryService {
	
	@Autowired
	public LogEntryRepository logEntryRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	public void saveLog(String endpoint, String username, String request, String response) {
		logEntryRepository.save(new LogEntry(endpoint, username, request, response));
	}
	
	public List<LogEntry> findAll(Integer page, Integer size) {
		Page<LogEntry> logEntries = logEntryRepository.findAll(PageRequest.of(page, size));
		return logEntries.toList();
	}
}
