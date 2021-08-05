package com.revature.services;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.bean.User;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

import com.revature.controller.UserControllerImpl;
import com.revature.services.UserService;

@Log
public class UserServiceImpl {
	private Logger log = LogManager.getLogger(UserServiceImpl.class);
	private UserDAO ud = (UserDAO) BeanFactory.getFactory().get(UserDAO.class, UserDAOImpl.class);
	
	public User login(String name) {
		User u = ud.getUser(name);
		List<UUID> id = ud.getId(name);
		
		return u;		
	}
}
