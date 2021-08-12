package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bean.User;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

@Log
public class UserControllerImpl implements UserController {
	private static Logger log = LogManager.getLogger(UserControllerImpl.class);
	private UserService us = (UserService) BeanFactory.getFactory().get(UserService.class, UserServiceImpl.class);
	
	@Override
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		
		u = us.login(u.getName());
		log.debug(u);
	
		if(u != null) {
			ctx.sessionAttribute("loggedUser", u);
			ctx.json(u);
			return;
		}
		ctx.status(401);
	}
	
	@Override
	public void logout(Context ctx) {
		log.trace("Logout method called");
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
}
