package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.bean.Notification;
import com.revature.bean.User;

public interface UserDAO {
	public void addUser(User u);

	public List<User> getUsers();

	public User getUser(String username);

	public void updateUser(User user);
	
	public List<Notification> getNotification(String username);
}
