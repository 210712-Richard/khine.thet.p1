package com.revature.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ReimbursementForm implements Serializable {
	private static final long serialVersionUID = 102831973239L;
	private UUID id;
	private String name;
	private String deptName;
	private LocalDate submittedDate;
	private LocalDate approvalDate;
	private String location;
	private String description;
	private Long cost;
	private GradingFormat format;
	private ReimbursementType type;
	private Period workTimeMissed;
	private Boolean urgent;
	private List<Attachment> attachment;
	private ReimbursementApproval supervisorApproval;
	private ReimbursementApproval departmentHeadApproval;
	private ReimbursementApproval benCoApproval;
	
	public ReimbursementForm() {
		super();
	}
	
	public ReimbursementForm(String name, LocalDate submittedDate, LocalDate approvalDate,
			String location, String description, Long cost, GradingFormat format, ReimbursementType type, Period workTimeMissed, List<Attachment> attachment) {
		this.name = name;
		this.submittedDate = submittedDate;
		this.approvalDate = approvalDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.format = format;
		this.type = type;
		this.workTimeMissed = workTimeMissed;
		this.attachment = attachment;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public GradingFormat getFormat() {
		return format;
	}

	public void setFormat(GradingFormat format) {
		this.format = format;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public Period getWorkTimeMissed() {
		return workTimeMissed;
	}

	public void setWorkTimeMissed(Period workTimeMissed) {
		this.workTimeMissed = workTimeMissed;
	}
	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}	
	public Boolean getUrgent() {
		return urgent;
	}

	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}
	public ReimbursementApproval getSupervisorApproval() {
		return supervisorApproval;
	}

	public void setSupervisorApproval(ReimbursementApproval supervisorApproval) {
		this.supervisorApproval = supervisorApproval;
	}

	public ReimbursementApproval getDepartmentHeadApproval() {
		return departmentHeadApproval;
	}

	public void setDepartmentHeadApproval(ReimbursementApproval departmentHeadApproval) {
		this.departmentHeadApproval = departmentHeadApproval;
	}

	public ReimbursementApproval getBenCoApproval() {
		return benCoApproval;
	}

	public void setBenCoApproval(ReimbursementApproval benCoApproval) {
		this.benCoApproval = benCoApproval;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(approvalDate, attachment, benCoApproval, cost, departmentHeadApproval, deptName,
				description, format, id, location, name, submittedDate, supervisorApproval, type, urgent,
				workTimeMissed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ReimbursementForm other = (ReimbursementForm) obj;
		return Objects.equals(approvalDate, other.approvalDate) && Objects.equals(attachment, other.attachment)
				&& Objects.equals(benCoApproval, other.benCoApproval) && Objects.equals(cost, other.cost)
				&& Objects.equals(departmentHeadApproval, other.departmentHeadApproval)
				&& Objects.equals(deptName, other.deptName) && Objects.equals(description, other.description)
				&& Objects.equals(format, other.format) && Objects.equals(id, other.id)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(submittedDate, other.submittedDate)
				&& Objects.equals(supervisorApproval, other.supervisorApproval) && type == other.type
				&& Objects.equals(urgent, other.urgent) && Objects.equals(workTimeMissed, other.workTimeMissed);
	}
	
	@Override
	public String toString() {
		return "ReimbursementForm [id=" + id + ", name=" + name + ", deptName=" + deptName + ", submittedDate="
				+ submittedDate + ", approvalDate=" + approvalDate + ", location=" + location + ", description="
				+ description + ", cost=" + cost + ", format=" + format + ", type=" + type + ", workTimeMissed="
				+ workTimeMissed + ", urgent=" + urgent + ", attachment=" + attachment + "]";
	}	
}
