package com.revature.controller;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bean.User;
import com.revature.bean.UserType;
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
		
		reService.addReimbursementForm(loggedUser.getName(), 
				reRequest.getSubmittedDate(), reRequest.getLocation(), reRequest.getDescription(),
				reRequest.getCost(), reRequest.getFormat(), reRequest.getType(),
				reRequest.getWorkTimeMissed(), reRequest.getUrgent());
		
			ctx.json(reRequest);
	}

	@Override
	public void uploadAttachment(Context ctx) {
		log.trace("addAttachment method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		//check if we are the EMPLOYEE
		if(!loggedUser.getType().equals(UserType.EMPLOYEE)) {
			ctx.status(403);
			return;
		}
		
		UUID id = UUID.fromString(ctx.pathParam("id"));
		if(id == null) {
			ctx.status(400);
			return;
		}
		String username = ctx.pathParam("name");
		ReimbursementRequest reForm =  (ReimbursementRequest) reService.getReimbursementForm(id, username);
		//check if form exists
		if(reForm == null) {
			ctx.status(404);
			return;
		}
		
		String filetype = ctx.header("extension");
		if(filetype == null) {
			ctx.status(400);
			return;
		}
		
		String key =  id +"." + filetype;
		S3Util.getInstance().uploadToBucket(key, ctx.bodyAsBytes());
		reForm.getAttachment().add(key);
		reService.updateReimbursementForm(reForm);
		ctx.json(reForm);	
	}

	@Override
	public void uploadPresentation(Context ctx) {
		log.trace("addPresentation method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void getStatus(Context ctx) {
		log.trace("getStatus method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void getNotification(Context ctx) {
		log.trace("getNotification method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void getReason(Context ctx) {
		log.trace("getReason method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void deleteForm(Context ctx) {
		log.trace("deleteForm method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void getReimbursement(Context ctx) {
//		log.trace("getReimbursement method called");
//		log.debug("Reimbursement Form for" + ctx.pathParam("reimbursementId"));
//		
//		User loggedUser = ctx.sessionAttribute("loggedUser");
//		//login check
//		if(loggedUser == null) {
//			ctx.status(401);
//			return;
//		}
//		
//		String employee = ctx.pathParam("employee");
//		String loggedName = loggedUser.getName();
//	
//		ReimbursementRequest reRequest = reService.getReimbursementForm(employee);
//		
//		if(loggedName.equals(employee)) {
//			ctx.json(reRequest);
//		} else {
//			ctx.status(403);
//		}
//		
		
	}

	@Override
	public void getAttachment(Context ctx) {
		log.trace("getAttachment method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void getPresentation(Context ctx) {
		log.trace("getPresentation method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void updateApproval(Context ctx) {
		log.trace("updateApproval method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	@Override
	public void changeReimbursementAmount(Context ctx) {
		log.trace("changeReimbursementAmount method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
	}

	
}
