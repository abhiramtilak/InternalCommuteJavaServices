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

import com.commute.bean.PasswordUpdate;
import com.commute.bean.ResponseBody;
import com.commute.bean.User;
import com.commute.db.model.Users;
import com.commute.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Users> userDetails( @RequestBody User user ) {
		logger.info("email is ::"+user.getEmail());
		Users dbUser = loginService.validateUser(user);
		if( dbUser != null && dbUser.getEmail() != null ) {
			return new ResponseEntity<Users>(dbUser, HttpStatus.OK);
		}else {
			return new ResponseEntity<Users>(dbUser, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ResponseEntity<ResponseBody> updateProfile( @RequestBody User user ) {
		
		//update user profile
		int status = loginService.updateProfile(user);
		ResponseBody response = new ResponseBody();
		if( status > 0 ) {
			response.setResponseCode(HttpStatus.OK);
			response.setResponseMessage("Profileupdated succssfylly");
		}else {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ResponseBody>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<String> updatePassword( @RequestBody PasswordUpdate passwordUpdate ) {
		
		//update user profile
		String message = loginService.updatePassword(passwordUpdate);
		if( message != null ) {
			return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
