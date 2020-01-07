package com.commute.service;

import org.springframework.stereotype.Component;

import com.commute.bean.PasswordUpdate;
import com.commute.bean.User;
import com.commute.db.model.Users;

@Component
public interface LoginService {

	public Users validateUser(User user);

	public int updateProfile(User user);

	public String updatePassword(PasswordUpdate passwordUpdate);
}
