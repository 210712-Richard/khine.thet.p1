package com.revature.services;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bean.Notification;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.data.NotificationDAO;
import com.revature.data.NotificationDAOImpl;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

import com.revature.controller.UserControllerImpl;
import com.revature.services.UserService;

@Log
public class UserServiceImpl implements UserService {
	private Logger log = LogManager.getLogger(UserServiceImpl.class);
	public UserDAO ud = (UserDAO) BeanFactory.getFactory().get(UserDAO.class, UserDAOImpl.class);
	public NotificationDAO nd = (NotificationDAO) BeanFactory.getFactory().get(NotificationDAO.class, NotificationDAOImpl.class);
	public ReimbursementDAO rd = (ReimbursementDAO) BeanFactory.getFactory().get(ReimbursementDAO.class, ReimbursementDAOImpl.class);
	ReimbursementForm rf = new ReimbursementForm();
	
	@Override
	public User login(String name) {
		User u = ud.getUser(name);
		return u;		
	}

	@Override
	public List<Notification> notification(String username) {
		List<Notification> notification = ud.getNotification(username);
		return notification;
	}
	
	@Override
	public void updateUser(User user) {
		ud.updateUser(user);		
	}

	@Override
	public User checkUser(User user) {
		if(user.getType() == UserType.DIRECT_SUPERVISOR) {
			user.getDirectSupervisor();
		} else if(user.getType() == UserType.DEPARTMENT_HEAD ) {
			user.getDepartmentHead();
		} else if(user.getType() == UserType.BENCO) {
			user.getBenCo();
		}
		return user;	
	}
	
}
