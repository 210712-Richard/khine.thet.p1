package com.revature;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controller.ReimbursementController;
import com.revature.controller.ReimbursementControllerImpl;
import com.revature.controller.UserController;
import com.revature.controller.UserControllerImpl;
import com.revature.factory.BeanFactory;
import com.revature.util.CassandraUtil;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {

	public static void main(String[] args) {
		//DataBaseCreator.createTables();
		//DataBaseCreator.populateUserTable();
		//DataBaseCreator.dropTables();
		//instantiateDatabase();
		javalin();	
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
//		DataBaseCreator.populateUserTable();
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
		ReimbursementController rc = (ReimbursementController) BeanFactory.getFactory().get(ReimbursementController.class, ReimbursementControllerImpl.class);
		
		app.get("/", (ctx)->ctx.html("This is Project 1"));
		
		// login
		app.post("/users", uc::login);
				
		// logout
		app.delete("/users", uc::logout);
		
		//add reimbursement
		app.post("/requests/:employee", rc::addReimbursement);
		
		//get reimbursement
		app.get("/requests/:employee", rc::getReimbursement);
		
		//upload attachment
		app.put("/requests/attachment/:employee/:id", rc::uploadAttachment);
		
		//delete reimbursement
		app.delete("/requests/:employee/:id", rc::deleteForm);
		
		//approve form
		app.put("/requests/:employee/:id/formApproval", rc::updateApproval);
	}
	

}
