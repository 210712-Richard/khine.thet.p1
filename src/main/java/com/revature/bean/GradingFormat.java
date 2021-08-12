package com.revature.bean;

import java.io.Serializable;

public enum GradingFormat implements Serializable {
	PERCENTAGE("80"), LETTER_GRADE("C"), PASS_FAIL("PASS"), PRESENTATION("true");

	private String grade; 
	
	public String passingGrade;
	
	public String passingGrade() {
		return passingGrade;
	}
	
	GradingFormat(String passingGrade) {
		this.passingGrade = passingGrade;
	} 
}
