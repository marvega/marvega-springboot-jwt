package com.example.tenpo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tenpo.payload.request.LoginRequest;
import com.example.tenpo.payload.request.SignupRequest;
import com.example.tenpo.payload.response.JwtResponse;
import com.example.tenpo.payload.response.MessageResponse;
import com.example.tenpo.security.jwt.JwtUtils;
import com.example.tenpo.security.services.UserDetailsImpl;
import com.example.tenpo.service.LogEntryService;
import com.example.tenpo.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	LogEntryService logEntryService;

	@Autowired
	UserService userService;


	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = null;
		try {
		authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		}catch(BadCredentialsException e) {
			logEntryService.saveLog("/api/auth/signin", loginRequest.getUsername(), loginRequest.toString(), "Bad credentials");
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Bad credentials"));		
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		JwtResponse response = new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail());

		logEntryService.saveLog("/api/auth/signin", userDetails.getUsername(), loginRequest.toString(), response.toString());
		
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			logEntryService.saveLog("/api/auth/signup", signUpRequest.getUsername(), signUpRequest.toString(), "Error: Username is already taken!");
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsByEmail(signUpRequest.getEmail())) {
			logEntryService.saveLog("/api/auth/signup", signUpRequest.getUsername(), signUpRequest.toString(), "Error: Email is already in use!");
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		
		userService.saveUser(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
		MessageResponse msj = new MessageResponse("User registered successfully!");
		logEntryService.saveLog("/api/auth/signup", signUpRequest.getUsername(), signUpRequest.toString(), msj.toString());

		return ResponseEntity.ok(msj);
	}
}
