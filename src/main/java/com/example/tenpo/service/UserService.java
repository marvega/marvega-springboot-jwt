package com.example.tenpo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tenpo.entity.User;
import com.example.tenpo.repository.LogEntryRepository;
import com.example.tenpo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	PasswordEncoder encoder;

	
	@Autowired
	public LogEntryRepository logEntryRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	public void saveUser(String username, String email, String password) {
		User user = new User(username, 
							email,
							encoder.encode(password));

		userRepository.save(user);
	}
	
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

}
