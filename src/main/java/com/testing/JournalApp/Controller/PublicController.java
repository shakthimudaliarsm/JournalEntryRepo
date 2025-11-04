package com.testing.JournalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.JournalApp.Entity.User;
import com.testing.JournalApp.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	

	@Autowired
	private UserService userService; 
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "OK";
	}
	
	@PostMapping("/Create")
	public void createUser(@RequestBody User user) {
		userService.saveUser(user);
	}
	
}
