package com.example.tenpo.payload.response;

import java.util.List;

import com.example.tenpo.entity.LogEntry;

public class LogEntryResponse {
	
	List<LogEntry> entries;

	public LogEntryResponse(List<LogEntry> entries) {
		this.entries = entries;
	}

	public List<LogEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<LogEntry> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		return "LogEntryResponse [entries=" + entries + "]";
	}

}