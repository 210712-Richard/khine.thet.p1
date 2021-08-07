package com.revature.bean;

import java.io.Serializable;

public enum ReimbursementType {
	UNIVERSITY(0.8),
	SEMINAR(0.6),
	PREP_CLASS(0.75),
	CERTIFICATION(1.0),
	TECH_TRAINING(0.9),
	OTHERS(0.3);

	ReimbursementType(double d) {
		// TODO Auto-generated constructor stub
	}
}
