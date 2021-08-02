package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controller.UserController;
import com.revature.controller.UserControllerImpl;
import com.revature.factory.BeanFactory;
import com.revature.util.CassandraUtil;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {

	public static void main(String[] args) {
		dbtest();
		//javalin();

	}
	
	private static void dbtest() {
		CassandraUtil.getInstance().getSession();		
	}

	public static void javalin() {
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		// Starts the Javalin Framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
	}
	
	public static void instantiateDatabase() {
		DataBaseCreator.dropTables();
		try {
			Thread.sleep(30000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.createTables();
		try {
			Thread.sleep(20000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.populateUserTable();
		DataBaseCreator.populateReimbursementTable();
		System.exit(0);
	}

}
