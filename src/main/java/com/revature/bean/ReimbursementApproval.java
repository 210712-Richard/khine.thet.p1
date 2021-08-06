package com.revature.bean;

import java.time.LocalDateTime;

public class ReimbursementApproval {
	
	private LocalDateTime deadline;
	private Approval status;
	private String reason;
	
	public ReimbursementApproval() {
		super();
		this.status = Approval.PENDING;
	}
	
	public ReimbursementApproval(Approval status, String reason) {
		this.status = status;
		this.reason = reason;
	}
	
	public LocalDateTime getDealine() {
		return deadline;
	}

	public void setDealine(LocalDateTime dealine) {
		this.deadline = dealine;
	}

	public Approval getStatus() {
		return status;
	}

	public void setStatus(Approval status) {
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
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
		ReimbursementApproval other = (ReimbursementApproval) obj;
		if (deadline == null) {
			if (other.deadline != null) {
				return false;
			}
		} else if (!deadline.equals(other.deadline)) {
			return false;
		}
		if (reason == null) {
			if (other.reason != null) {
				return false;
			}
		} else if (!reason.equals(other.reason)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "ReimbursementApproval [dealine=" + deadline + ", status=" + status + ", reason=" + reason + "]";
	}
}
