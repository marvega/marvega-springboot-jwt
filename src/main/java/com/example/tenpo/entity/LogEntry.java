package com.example.tenpo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.*;

@Entity
@Table(	name = "registry")

public class LogEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String endpoint;

	@NotBlank
	private String username;
	
	@NotBlank
	private String request;

	@NotBlank
	private String response;

	@Basic
	private Instant timestamp;

	public LogEntry() {
	}

	public LogEntry(String endpoint, String username, @NotBlank String request,
			@NotBlank String response) {
		super();
		this.endpoint = endpoint;
		this.username = username;
		this.request = request;
		this.response = response;
		this.timestamp = Instant.now();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}


	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

}
