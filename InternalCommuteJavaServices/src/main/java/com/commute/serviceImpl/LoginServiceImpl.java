package com.commute.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.commute.DAO.LoginDAO;
import com.commute.bean.PasswordUpdate;
import com.commute.bean.User;
import com.commute.db.model.Users;
import com.commute.service.LoginService;

@Component
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDAO loginDAO;
	
	public static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	public Users validateUser(User user) {
		Users users = new Users();
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		Users dbUser = loginDAO.validateUser(users);
		return dbUser;
	}

	public Users updateProfile(User user) {
		Users users = new Users();
		users.setUserId(user.getUserId());
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setEmail(user.getEmail());
		users.setMobileNumber(user.getMobileNumber());
		users.setRole(user.getRole());
		users.setOfficeAddress(user.getOfficeAddress());
		users.setHomeAddress(user.getHomeAddress());
		if( user.getRole().equalsIgnoreCase("Rider") ) {
			users.setAvailableSeats(user.getAvailableSeats());
			users.setVehicle(user.getVehicle());
		}else {
			users.setAvailableSeats(null);
			users.setVehicle(null);
		}
		Users dbuser = loginDAO.updateProfile(users);
		return dbuser;
		
	}

	public String updatePassword(PasswordUpdate passwordUpdate) {
		logger.info("checking old password with database password");
		Users user = loginDAO.validatePassword(passwordUpdate.getUserId());
		if( user.getPassword().equals(passwordUpdate.getOldPassword()) ){
			if( passwordUpdate.getNewPassword().equalsIgnoreCase(passwordUpdate.getOldPassword()) ) {
				logger.info("Old Password and New Password Cannot be same");
				return "Old Password and New Password Cannot be same";
			}else {
				loginDAO.updatePassword(passwordUpdate.getUserId(), passwordUpdate.getNewPassword());
				return null;
			}
			
		}else {
			return "Please Eneter correct Password!!";
		}
	}

}
