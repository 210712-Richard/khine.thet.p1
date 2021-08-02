package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

@Log
public class UserControllerImpl {
	private static Logger log = LogManager.getLogger(UserControllerImpl.class);
	private UserService us = (UserService) BeanFactory.getFactory().get(UserService.class, UserServiceImpl.class);
}
