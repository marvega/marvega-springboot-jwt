package com.example.tenpo.controller;

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

import com.example.tenpo.payload.request.MultiplicarRequest;
import com.example.tenpo.payload.response.ResultResponse;
import com.example.tenpo.security.services.UserDetailsImpl;
import com.example.tenpo.service.LogEntryService;
import com.example.tenpo.service.OperationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/operations")
public class OperationController {
	
	@Autowired
	OperationService operationService;
	
	@Autowired
	LogEntryService logEntryService;

	@PostMapping("/multiplicar")
	public ResponseEntity<?> multiplicar(@Valid @RequestBody MultiplicarRequest multiplicarRequest){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		
		Integer result = operationService.multiplicar(multiplicarRequest.getX(), multiplicarRequest.getY());
		ResultResponse resultResponse = new ResultResponse(result);
		logEntryService.saveLog("/api/operations/multiplicar", userDetails.getUsername(), multiplicarRequest.toString(), resultResponse.toString());

		return ResponseEntity.ok(resultResponse);
	}
	
}
