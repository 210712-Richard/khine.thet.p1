package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.bean.User;
import com.revature.bean.Notification;
import com.revature.bean.UserType;
import com.revature.data.UserDAO;
import com.revature.services.UserServiceImpl;

public class UserServiceTest {
	private static UserServiceImpl service;
	private static User u;
	private static UserDAO userDao;
	
	@BeforeAll
	public static void setUpClass() {
		u = new User();
		u.setName("Test");
		
	}
	
	@BeforeEach
	public void setUpTest() {
		u.setType(UserType.EMPLOYEE);
		//service = new UserServiceImpl(userDao);
		 
	}
	
	@Test
	public void testLogin() {
		String name = "Test";
		User u = new User();
		u.setName(name);
		
		Mockito.when(((UserDAO) service).getUser(name)).thenReturn(u);
		//User loginUser = ud.getUser(name);
		//assertEquals(u, loginUser, "This is the correct user");
	}
	
}
