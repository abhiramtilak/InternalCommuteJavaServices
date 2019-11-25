package com.commute.DAOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.commute.DAO.RegisterDAO;
import com.commute.bean.User;
import com.commute.db.model.Users;
import com.commute.db.repository.UsersRepository;

@Component
@Transactional
public class RegisterDAOImpl implements RegisterDAO{
	
	@Autowired
	UsersRepository usersRepository;

	public Users createUser(Users user) {
			
		Users users = usersRepository.save(user);
		
		return users;
	}

	public Users findUser(User user) {
		
		Users users = usersRepository.findByemailormobileNumber(user.getEmail(), user.getMobileNumber());
		return users;
	}

}
