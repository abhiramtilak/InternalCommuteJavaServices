package com.commute.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commute.DAO.LoginDAO;
import com.commute.bean.User;
import com.commute.db.model.Users;
import com.commute.service.LoginService;

@Component
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDAO loginDAO;

	public Users validateUser(User user) {
		Users users = new Users();
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		Users dbUser = loginDAO.validateUser(users);
		return dbUser;
	}

}
