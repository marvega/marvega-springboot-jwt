package com.example.tenpo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tenpo.entity.LogEntry;
import com.example.tenpo.payload.request.LogEntryRequest;
import com.example.tenpo.payload.response.LogEntryResponse;
import com.example.tenpo.security.services.UserDetailsImpl;
import com.example.tenpo.service.LogEntryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
public class LogEntryController {
		
	@Autowired
	LogEntryService logEntryService;

	@PostMapping("/pag")
	public ResponseEntity<?> multiplicar(@Valid @RequestBody LogEntryRequest logEntryRequest){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<LogEntry> entries = logEntryService.findAll(logEntryRequest.getPage(), logEntryRequest.getSize());
		LogEntryResponse response = new LogEntryResponse(entries);
		//no tiene sentido guardar toooooodos los logs devueltos por pagina por ahora
		logEntryService.saveLog("/api/history/pag", userDetails.getUsername(), logEntryRequest.toString(), "");

		return ResponseEntity.ok(response);
	}
	
}
