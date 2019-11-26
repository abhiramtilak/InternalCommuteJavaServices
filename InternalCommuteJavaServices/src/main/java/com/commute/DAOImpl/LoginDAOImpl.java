package com.commute.DAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.commute.DAO.LoginDAO;
import com.commute.db.model.Users;
import com.commute.db.repository.UsersRepository;

@Component
@Transactional
public class LoginDAOImpl implements LoginDAO{
	
	@Autowired
	UsersRepository usersRepository;

	public Users validateUser(Users users) {
		
		Users dbUser = usersRepository.findByEmailandPassword(users.getEmail(), users.getPassword());
		return dbUser;
	}
}
