package com.revature.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReimbursementRequest extends Serializable {
	Integer Max_Reimbursement_Amount = 1000;
	
	UUID getId();
	void setId(UUID id);
	
	String getName();
	void setName(String name);
	
	LocalDate getSubmittedDate();
	void setSubmittedDate(LocalDate submittedDate);
	
	LocalDate getApprovalDate();
	void setApprovalDate(LocalDate approvalDate);
	
	String getLocation();
	void setLocation(String location);
	
	String getDescription();
	void setDescription(String description);
	
	Double getCost();
	void setCost(Double cost);
	
	GradingFormat getFormat();
	void setFormat(GradingFormat format);
	
	ReimbursementType getType();
	void setType(ReimbursementType type);
	
	String getWorkTimeMissed();
	void setWorkTimeMissed(String workTimeMissed);
	
	Boolean getUrgent();
	
	List<String> getAttachment();
	void setAttachment(List<String> attachment);
	
	ReimbursementApproval getSupervisorApproval();
	void setSupervisorApproval(ReimbursementApproval supervisorApproval);
	
	ReimbursementApproval getDepartmentHeadApproval();
	void setDepartmentHeadApproval(ReimbursementApproval departmentHeadapproval);
	
	ReimbursementApproval getBenCoApproval();
	void setBenCoApproval(ReimbursementApproval benCoApproval);	
}
