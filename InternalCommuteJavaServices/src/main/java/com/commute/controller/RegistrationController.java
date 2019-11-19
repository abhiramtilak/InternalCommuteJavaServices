package com.commute.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.commute.bean.User;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@RequestMapping(value="/test/get", method = RequestMethod.GET)
	public ResponseEntity<User> sampleGet() {
		User u = new User();
		u.setEmail("testMail");
		u.setFirstName("firstName");
		
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/test/post", method = RequestMethod.POST)
	public ResponseEntity<String> samplePost( @RequestBody User user ) {
		
		return new ResponseEntity<String>(user.getEmail(), HttpStatus.CREATED);
		
	}

}
