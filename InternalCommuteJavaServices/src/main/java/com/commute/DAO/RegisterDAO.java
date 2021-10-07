package com.commute.DAO;


import org.springframework.stereotype.Component;

import com.commute.bean.User;
import com.commute.db.model.Users;

@Component
public interface RegisterDAO {

	Users createUser(Users user);

	Users findUser(User user);

}
