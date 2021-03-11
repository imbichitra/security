package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
	
	@GetMapping("/user")
	public String hellowUser() {
		return "Hello User";
	}
	
	@GetMapping("/admin")
	public String hellowAdmin() {
		return "Hello Admin";
	}
}
