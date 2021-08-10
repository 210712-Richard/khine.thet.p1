package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.revature.bean.*;

public interface ReimbursementService {
	
	ReimbursementForm addReimbursementForm(String username, String deptName, 
			LocalDate submittedDate, String location, String description,
			Long cost, GradingFormat format, ReimbursementType type, String workTimeMissed, Boolean urgent);
	
	void updateReimbursementForm(ReimbursementForm reForm);
	
	List<ReimbursementForm> getReimbursementForm(String name);
	
	void deleteReimbursementForm(String name, UUID id);
}
