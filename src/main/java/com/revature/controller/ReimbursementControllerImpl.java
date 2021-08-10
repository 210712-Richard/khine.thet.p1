package com.revature.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bean.User;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementRequest;
import com.revature.data.UserDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.util.S3Util;

import io.javalin.http.Context;

@Log
public class ReimbursementControllerImpl implements ReimbursementController{
	private static final Logger log = LogManager.getLogger(ReimbursementControllerImpl.class);
	private ReimbursementService reService = (ReimbursementService) BeanFactory.getFactory()
							.get(ReimbursementService.class, ReimbursementServiceImpl.class);
	private UserService userService = (UserService) BeanFactory.getFactory()
			.get(UserService.class, UserServiceImpl.class);
	
	@Override
	public void addReimbursement(Context ctx) {
		log.trace("addReimbursement method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
		//Get the form info from the body
		ReimbursementRequest reRequest = ctx.bodyAsClass(ReimbursementForm.class);
		log.debug(reRequest);
		
		reRequest = reService.addReimbursementForm(loggedUser.getName(), reRequest.getDeptName(), 
				reRequest.getSubmittedDate(), reRequest.getLocation(), reRequest.getDescription(),
				reRequest.getCost(), reRequest.getFormat(), reRequest.getType(),
				reRequest.getWorkTimeMissed(), reRequest.getUrgent());
		
		ctx.json(reRequest);
	}

	@Override
	public void addAttachment(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPresentation(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getStatus(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNotification(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getReason(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteForm(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getReimbursement(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAttachment(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPresentation(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateApproval(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeReimbursementAmount(Context ctx) {
		// TODO Auto-generated method stub
		
	}

	
}
