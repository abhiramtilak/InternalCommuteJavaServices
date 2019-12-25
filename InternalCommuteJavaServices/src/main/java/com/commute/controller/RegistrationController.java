package com.commute.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.commute.bean.User;
import com.commute.db.model.Users;
import com.commute.service.RegisterService;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {
	
	public static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	RegisterService registerService;
	
	@RequestMapping(value="/test/get", method = RequestMethod.GET)
	public ResponseEntity<User> sampleGet() {
		User u = new User();
		u.setEmail("testMail");
		u.setFirstName("firstName");
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> registeruser( @RequestBody User user ) {
		Users dbUser = registerService.findUser(user);
		if( dbUser != null && dbUser.getEmail() != null ) {
			logger.info("User with details already exists!!");
			return new ResponseEntity<String>("User already exists with email or mobile number!!", HttpStatus.CONFLICT);
		}
		Users users = registerService.createUser(user);
		logger.info("User created with userId:::"+users.getUserId());
		return new ResponseEntity<String>(users.getUserId()+"", HttpStatus.OK);
	}

}
