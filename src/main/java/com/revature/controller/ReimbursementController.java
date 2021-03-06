package com.revature.controller;

import io.javalin.http.Context;

public interface ReimbursementController {
	//As a Employee, I can add the form.(done)
	void addReimbursement(Context ctx);
	//As a Employee, I can add attachment.(done)
	void uploadAttachment(Context ctx);
	//As a Employee, I can add presentation.
	void uploadPresentation(Context ctx);
	//As a Employee, I can see the approval status.
	void getStatus(Context ctx);
	//As an Employee, I can get notification.
	void getNotification(Context ctx);
	//As a Employee, I can see the approval/ rejection reason.
	void getReason(Context ctx);
	//As an Employee, I can delete the reimbursement form.
	void deleteForm(Context ctx);
	
	
	//As a Supervisor, deptHead and benCo I can view the reimbursement form.(done)
	void getReimbursement(Context ctx);
	//As a supervisor, deptHead and benCo, I can view the attachment.(done)
	void getAttachment(Context ctx);
	//As a supervisor, deptHead, benCo I can view the presentation
	void getPresentation(Context ctx);
	//As a supervisor, deptHead, benco, I can approve the form.
	void updateApproval(Context ctx);
	
	//As a Benco, I can change the reimbursement amount.
	void changeReimbursementAmount(Context ctx);
}
