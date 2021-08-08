package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.bean.ReimbursementForm;

public interface ReimbursementDAO {
	void addReimbursementForm(ReimbursementForm reimbursement);
	
	List<ReimbursementForm> getReimbursementForm();
	
	ReimbursementForm getReimbursementFormByNameandId(String user, UUID id);
	
	void updateReimbursementForm(String name, UUID id);
	
	void deleteReimbursementForm(String name, UUID id);	
}
