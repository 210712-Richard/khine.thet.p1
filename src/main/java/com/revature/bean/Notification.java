package com.revature.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Notification implements Serializable{
	
	private static final long serialVersionUID = 102831973239L;
	public String name;
	public UUID id;
	public String approvalStatus;
	public LocalDate approvalDate;
	public String reason;
	
	public Notification() {
		super();
	}
	
	public Notification(String name, UUID id, String approvalStatus, LocalDate approvalDate, String reason) {
		this();
		this.name = name;
		this.id = id;
		this.approvalStatus = approvalStatus;
		this.approvalDate = approvalDate;
		this.reason = reason;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public LocalDate getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(approvalDate, approvalStatus, id, name, reason);
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
		Notification other = (Notification) obj;
		return Objects.equals(approvalDate, other.approvalDate) && Objects.equals(approvalStatus, other.approvalStatus)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(reason, other.reason);
	}
	@Override
	public String toString() {
		return "Notification [name=" + name + ", id=" + id + ", approvalStatus=" + approvalStatus + ", approvalDate="
				+ approvalDate + ", reason=" + reason + "]";
	}

}
