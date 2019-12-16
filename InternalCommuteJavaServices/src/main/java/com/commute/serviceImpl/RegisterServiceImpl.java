package com.commute.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commute.DAO.RegisterDAO;
import com.commute.bean.User;
import com.commute.db.model.Users;
import com.commute.service.RegisterService;

@Component
public class RegisterServiceImpl implements RegisterService{

	
	@Autowired
	RegisterDAO registerDAO;
	
	public Users createUser(User user) {
		
		Users users = new Users();
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setEmail(user.getEmail());
		users.setMobileNumber(user.getMobileNumber());
		users.setRole(user.getRole());
		users.setOfficeAddress(user.getOfficeAddress());
		users.setHomeAddress(user.getHomeAddress());
		users.setPassword(user.getPassword());
		if( user.getRole().equalsIgnoreCase("Rider") ) {
			users.setVehicle(user.getVehicle());
			users.setAvailableSeats(user.getAvailableSeats());
		}
		Users users1 =registerDAO.createUser(users);
		return users1;
	}

	public Users findUser(User user) {
		
		Users users = registerDAO.findUser(user);
		return users;
	}

}
