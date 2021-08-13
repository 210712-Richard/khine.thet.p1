package com.revature.controller;

import java.io.InputStream;
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
		
		reService.addReimbursementForm(reRequest.getName(), 
				reRequest.getSubmittedDate(), reRequest.getLocation(), reRequest.getDescription(),
				reRequest.getCost(), reRequest.getFormat(), reRequest.getType(),
				reRequest.getWorkTimeMissed(), reRequest.getUrgent());
		
			ctx.json(reRequest);
	}

	@Override
	public void uploadAttachment(Context ctx) {
		log.trace("uploadAttachment method called");
		String attachmentType = ctx.header("attachmentType");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("employee");
		UUID id = UUID.fromString(ctx.pathParam("id"));
		
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
		
		
		ReimbursementRequest reForm = reService.getReimbursementFormByNameandId(id, username);
		//check if form exists
		if(reForm == null) {
			ctx.status(404);
			return;
		}
		
		String filetype = ctx.header("extension");
		if(filetype == null && filetype != attachmentType) {
			ctx.status(400);
			return;
		}
		
		String key =  id + "/attachment/" + reForm.getAttachment().size() + filetype;
		S3Util.getInstance().uploadToBucket(key, ctx.bodyAsBytes());
		reForm.getAttachment().add(key);
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
	public void getReimbursement(Context ctx) {
		log.trace("getReimbursement method called");
		log.debug("Reimbursement Form(s) for " + ctx.pathParam("employee"));
		
		String username = ctx.pathParam("employee");
		User loggedUser = ctx.sessionAttribute("loggedUser");
		//login check
		if(loggedUser == null || !loggedUser.getName().equals(username)) {
			ctx.status(401);
			return;
		}
		
		String loggedName = loggedUser.getName();
		List<ReimbursementForm> reRequest = reService.getReimbursementForm(username);
		
		if(loggedName.equals(username)) {
			ctx.json(reRequest);
		} else {
			ctx.status(403);
		}
		
		
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
		
		UUID id = UUID.fromString(ctx.pathParam("id"));
		Integer index = Integer.parseInt(ctx.pathParam("index"));
		log.debug("The index from the path: " + index);
		if(id == null) {
			ctx.status(400);
			return;
		}
		String username = ctx.pathParam("employee");
		ReimbursementRequest reForm =  (ReimbursementRequest) reService.getReimbursementForm(username);
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
		String key = reForm.getAttachment().get(index);
		try {
			InputStream picture = S3Util.getInstance().getObject(key);
			ctx.result(picture);
		} catch (Exception e) {
			ctx.status(500);
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
		//login user need to be supervisor, departmenthead or benco to continue
		log.trace("updateApproval method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("employee");
		UUID id = UUID.fromString("id");
		//login check
		if(loggedUser == null) {
			ctx.status(401);
			return;
		}
		
		if (loggedUser.getType().equals(UserType.DIRECT_SUPERVISOR) ||
			loggedUser.getType().equals(UserType.DEPARTMENT_HEAD) ||
			loggedUser.getType().equals(UserType.BENCO)) {					
			reService.approveForm(loggedUser, username, id);
		} else {
			ctx.status(403);
			return;
		}	
	}

	@Override
	public void changeReimbursementAmount(Context ctx) {
		log.trace("changeReimbursementAmount method called");
		log.debug(ctx.body());
		
		User loggedUser = ctx.sessionAttribute("log gedUser");
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

		String username = ctx.pathParam("employee");
		UUID id = UUID.fromString(ctx.pathParam("id"));
		
		if(username.equals(loggedUser.getName())) {
			reService.deleteReimbursementForm(username, id);
		} else {
			ctx.status(403);
		}
		
	}
	
}
