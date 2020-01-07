package com.commute.DAO;

import org.springframework.stereotype.Component;

import com.commute.db.model.Users;

@Component
public interface LoginDAO {

	public Users validateUser(Users users);

	public int updateProfile(Users users);

	public Users validatePassword(String userId);

	public void updatePassword(String userId, String newPassword);

}
