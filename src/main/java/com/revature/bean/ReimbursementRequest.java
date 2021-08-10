package com.revature.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReimbursementRequest extends Serializable {
	Integer Max_Reimbursement_Amount = 1000;
	
	//How to make the request limit to 1000?
	
	UUID getId();
	void setId(UUID id);
	
	String getName();
	void setName(String name);
	
	String getDeptName();
	void setDeptName(String deptName);
	
	LocalDate getSubmittedDate();
	void setSubmittedDate(LocalDate submittedDate);
	
	String getLocation();
	void setLocation(String location);
	
	String getDescription();
	void setDescription(String description);
	
	Long getCost();
	void setCost(Long cost);
	
	GradingFormat getFormat();
	void setFormat(GradingFormat format);
	
	ReimbursementType getType();
	void setType(ReimbursementType type);
	
	String getWorkTimeMissed();
	void setWorkTimeMissed(String workTimeMissed);
	
	Boolean getUrgent();
	
	List<Attachment> getAttachment();
	void setAttachment(List<Attachment> attachment);
	
	ReimbursementApproval getSupervisorApproval();
	void setSupervisorApproval(ReimbursementApproval supervisorApproval);
	
	ReimbursementApproval getDepartmentHeadApproval();
	void setDepartmentHeadApproval(ReimbursementApproval departmentHeadapproval);
	
	ReimbursementApproval getBenCoApproval();
	void setBenCoApproval(ReimbursementApproval benCoApproval);	
}
