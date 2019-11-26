package com.commute.service;

import org.springframework.stereotype.Component;

import com.commute.bean.User;
import com.commute.db.model.Users;

@Component
public interface LoginService {

	public Users validateUser(User user);
}
