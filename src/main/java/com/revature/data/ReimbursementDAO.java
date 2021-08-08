package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.bean.ReimbursementForm;

public interface ReimbursementDAO {
	void addReimbursementForm(ReimbursementForm reimbursement);
	
	List<ReimbursementForm> getReimbursementForm();
	
	ReimbursementForm getReimbursementFormByNameandId(UUID id, String user);
	
	void updateReimbursementForm(ReimbursementForm reForm);
	
	void deleteReimbursementForm(String name, UUID id);	
}
