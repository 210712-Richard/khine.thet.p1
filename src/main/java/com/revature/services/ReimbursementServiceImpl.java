package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bean.GradingFormat;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementRequest;
import com.revature.bean.ReimbursementType;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class ReimbursementServiceImpl implements ReimbursementService {
	Logger log = LogManager.getLogger(ReimbursementServiceImpl.class);
	public ReimbursementDAO reDAO = (ReimbursementDAO) BeanFactory.getFactory().get(ReimbursementDAO.class, ReimbursementDAOImpl.class);
	
	@Override
	public ReimbursementForm addReimbursementForm(String username, LocalDate submittedDate,
			String location, String description, Double cost, GradingFormat format, ReimbursementType type,
			String workTimeMissed, Boolean urgent) {
		ReimbursementForm reForm = new ReimbursementForm();
		reForm.setName(username);
		reForm.setSubmittedDate(submittedDate);
		reForm.setLocation(location);
		reForm.setDescription(description);
		reForm.setCost(cost);
		reForm.setFormat(format);
		reForm.setType(type);
		reForm.setWorkTimeMissed(workTimeMissed);
		reForm.setUrgent(urgent);
		
		reDAO.addReimbursementForm(reForm);
		
		return reForm;
	}

	@Override
	public void updateReimbursementForm(ReimbursementRequest reRequest) {
		reDAO.updateReimbursementForm(reRequest);
	}

	@Override
	public List<ReimbursementForm> getReimbursementForm(UUID id, String name) {
		List<ReimbursementForm> reForm = reDAO.getReimbursementForm(id, name);
		return reForm;
	}

	@Override
	public void deleteReimbursementForm(String name, UUID id) {
		reDAO.deleteReimbursementForm(name, id);		
	}

	@Override
	public void getApprovalDate(LocalDate submittedDate) {
		this.getApprovalDate(submittedDate);	
	}

}
