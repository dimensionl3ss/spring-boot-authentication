package com.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping("/sayhello")
	ResponseEntity<String> seyHello() {
		return ResponseEntity.ok("Hello World");
	}
}
