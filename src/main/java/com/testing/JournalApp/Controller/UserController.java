package com.testing.JournalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testing.JournalApp.Entity.User;
import com.testing.JournalApp.Service.UserService;



@RestController
@RequestMapping(value = "/User",method = RequestMethod.GET)
public class UserController {
	
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/getAll")
	public List<User> getAllUsers(){
		return userService.getAllUser();
	}
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		//User userInDb = userService.findByUsername(user.getUsername());
		User userInDb = userService.findByUsername(userName);
		if(userInDb!=null) {
			userInDb.setUsername(user.getUsername());
			userInDb.setPassword(user.getPassword());
			userService.saveUser(userInDb);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestBody User user){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		//User userInDb = userService.findByUsername(user.getUsername());
		userService.deleteByUserName(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
