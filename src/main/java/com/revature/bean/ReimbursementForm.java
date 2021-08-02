package com.revature.bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class ReimbursementForm {
	private UUID id;
	private String name;
	
	private String deptName;
	private LocalDate date;
	private LocalTime time;
	private String location;
	private String description;
	private Double cost;
	private GradingFormat format;
	private ReimbursementType type;
	
	
	
}
