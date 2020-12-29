package com.example.tenpo.service;

import org.springframework.stereotype.Service;

@Service
public class OperationService {
	
	public Integer multiplicar(Integer x, Integer y) {
		return x*y;
	}
	
}
