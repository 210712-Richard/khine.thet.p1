package com.revature.bean;

public enum GradingFormat {
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
