package com.revature.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.bean.Approval;
import com.revature.bean.GradingFormat;
import com.revature.bean.ReimbursementApproval;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementRequest;
import com.revature.bean.ReimbursementType;
import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class ReimbursementServiceImpl implements ReimbursementService {
	Logger log = LogManager.getLogger(ReimbursementServiceImpl.class);
	public ReimbursementDAO reDAO = (ReimbursementDAO) BeanFactory.getFactory().get(ReimbursementDAO.class, ReimbursementDAOImpl.class);
	public UserDAO uDAO = (UserDAO) BeanFactory.getFactory().get(UserDAO.class, UserDAOImpl.class);
	
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
	public List<ReimbursementForm> getReimbursementForm(String name) {
		List<ReimbursementForm> reForm = reDAO.getReimbursementForm(name);
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

	@Override
	public void approveForm(User name, String username, UUID id) {
		ReimbursementForm reForm = new ReimbursementForm();
		ReimbursementApproval reApproval = new ReimbursementApproval();
		User u = (User) uDAO.getUsers().stream().filter((n -> n.getName().equals(username)));
		
		if(name.getType() == u.getType().DIRECT_SUPERVISOR) {
			if(u.getDirectSupervisor().equals(name.getName())) {
				reForm.setSupervisorApproval(reApproval);
			}
		} else if(name.getType() == u.getType().DEPARTMENT_HEAD) {
			if(u.getDepartmentHead().equals(name.getName())) {
				reForm.setDepartmentHeadApproval(reApproval);
			}		
		} else if(name.getType() == u.getType().BENCO) {
			if(u.getBenCo().equals(name.getName())) {
				reForm.setBenCoApproval(reApproval);
			}	
		} else {
			return;
		}	
	}

	@Override
	public ReimbursementForm getReimbursementFormByNameandId(UUID id, String name) {
		return reDAO.getReimbursementFormByNameandId(id, name);
	}


}
