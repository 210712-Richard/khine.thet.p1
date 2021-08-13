package com.revature.bean;

import java.io.Serializable;

public enum ReimbursementType {
	UNIVERSITY(0.8),
	SEMINAR(0.6),
	PREP_CLASS(0.75),
	CERTIFICATION(1.0),
	TECH_TRAINING(0.9),
	OTHERS(0.3);
	
	private Double approvePercent;
	
	ReimbursementType(double approvePercent) {
		this.approvePercent = approvePercent; 
	}
	
	public Double getApprovePercent() {
		return approvePercent;
	}
}
