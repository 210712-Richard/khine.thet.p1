package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementRequest;

public interface ReimbursementDAO {
	void addReimbursementForm(ReimbursementForm reimbursement);
	
	ReimbursementForm getReimbursementFormByNameandId(UUID id, String user);
	
	void updateReimbursementForm(ReimbursementRequest reRequest);
	
	void deleteReimbursementForm(String name, UUID id);

	List<ReimbursementForm> getReimbursementForm(String name);
}
