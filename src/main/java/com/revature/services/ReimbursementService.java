package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.revature.bean.*;

public interface ReimbursementService {
	
	ReimbursementForm addReimbursementForm(String username, LocalDate submittedDate, 
			String location, String description, Double cost, GradingFormat format,
			ReimbursementType type, String workTimeMissed, Boolean urgent);
	
	void updateReimbursementForm(ReimbursementRequest reRequest);
	
	List<ReimbursementForm> getReimbursementForm(UUID id, String name);
	
	void deleteReimbursementForm(String name, UUID id);
}
