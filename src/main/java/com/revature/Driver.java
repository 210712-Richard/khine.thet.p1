package com.revature;

import com.datastax.oss.driver.api.core.CqlSession;
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
		DataBaseCreator.createTables();
		//javalin();
		//dbtest();
		//instantiateDatabase();
	}
	
	public static void dbtest() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, email text, type text, ")
				.append("directsupervisor text, departmenthead text, benco text, reform list<uuid> );");
		CqlSession session = CassandraUtil.getInstance().getSession();
		session.execute(sb.toString()); //You have to actually execute the statement.
	}
	
//	public static void instantiateDatabase() {
//		DataBaseCreator.dropTables();
//		try {
//			Thread.sleep(20000);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		DataBaseCreator.createTables();
//		try {
//			Thread.sleep(10000);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		//DataBaseCreator.populateUserTable();
//		//DataBaseCreator.populateReimbursementTable();
//		System.exit(0);
//	}
	
	public static void javalin() {
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		// Starting the Javalin Framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
	}

}
