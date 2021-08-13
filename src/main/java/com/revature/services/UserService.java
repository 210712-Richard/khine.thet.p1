package com.revature.services;

import java.util.List;

import com.revature.bean.Notification;
import com.revature.bean.User;

public interface UserService {

	public User login(String name);
	
	public List<Notification> notification(String username);
	
	public void updateUser(User user);
	
	public User checkUser(User user);
	
	//public void autoApprove();
}

