package com.commute.service;


import org.springframework.stereotype.Component;

import com.commute.bean.User;
import com.commute.db.model.Users;

@Component
public interface RegisterService {

	Users createUser(User user);

	Users findUser(User user);

}
