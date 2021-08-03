package com.revature.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

public class ReimbursementForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String name;
	private String deptName;
	private LocalDate date;
	private LocalTime time;
	private String location;
	private String description;
	private Long cost;
	private GradingFormat format;
	private ReimbursementType type;
	private Period workTimeMissed;
	
	public ReimbursementForm() {
		super();
	}
	
	public ReimbursementForm(String name, LocalDate date, LocalTime time, 
			String location, String description, Long cost, GradingFormat format, ReimbursementType type, Period workTimeMissed) {
		this.name = name;
		this.date = date;
		this.time = time;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.format = format;
		this.type = type;
		this.workTimeMissed = workTimeMissed;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
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
	@Override
	public int hashCode() {
		return Objects.hash(cost, date, deptName, description, format, id, location, name, time, type, workTimeMissed);
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
		return Objects.equals(cost, other.cost) && Objects.equals(date, other.date)
				&& Objects.equals(deptName, other.deptName) && Objects.equals(description, other.description)
				&& Objects.equals(format, other.format) && Objects.equals(id, other.id)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& Objects.equals(time, other.time) && type == other.type
				&& Objects.equals(workTimeMissed, other.workTimeMissed);
	}
	
	@Override
	public String toString() {
		return "ReimbursementForm [id=" + id + ", name=" + name + ", deptName=" + deptName + ", date=" + date
				+ ", time=" + time + ", location=" + location + ", description=" + description + ", cost=" + cost
				+ ", format=" + format + ", type=" + type + ", workTimeMissed=" + workTimeMissed + "]";
	}	
}
