package com.revature.bean;

import java.util.Objects;

public class ReimbursementApproval {
	
	private String status;
	private String reason;
	
	public ReimbursementApproval() {
		super();
		this.status = Approval.PENDING.toString();
		this.reason = "N/A";
	}
	
	public ReimbursementApproval(String reason) {
		this();
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	@Override
	public int hashCode() {
		return Objects.hash(reason, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementApproval other = (ReimbursementApproval) obj;
		return Objects.equals(reason, other.reason) && status == other.status;
	}
	
	@Override
	public String toString() {
		return "ReimbursementApproval [status=" + status + ", reason=" + reason + "]";
	}
}
