package com.revature.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.revature.bean.Notification;
import com.revature.bean.ReimbursementForm;


public class User implements Serializable {
	private static final long serialVersionUID = 102831973239L;
	private String name;
	private String email;
	private UserType type;
	private String directSupervisor;
	private String departmentHead;
	private String benCo;
	private Double pendingAmount;
	private Double awardedAmount;
	private List<UUID> reForm;
	private List<Notification> notification;
	
	public User() {
		super();
		this.type = UserType.EMPLOYEE;
		this.reForm = new ArrayList<UUID>();
		this.pendingAmount = 0.00;
		this.awardedAmount = 0.00;
	}
	
	//employee
	public User(String name, String email, UserType type, String directSupervisor, String departmentHead, String BenCo) {
		this.name = name;
		this.email = email;
		this.type = type;
		this.directSupervisor = directSupervisor;
		this.departmentHead = departmentHead;
	}
	
	//Direct Supervisor
	public User(String name, String email, UserType type, String departmentHead, String benCo) {
		this.name = name;
		this.email = email;
		this.type = type;
		this.departmentHead = departmentHead;
		this.benCo = benCo;
	}
	
	//Department Head
	public User(String name, String email, UserType type, String benCo) {
		this.name = name;
		this.email = email;
		this.type = type;
		this.benCo = benCo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public String getDirectSupervisor() {
		return directSupervisor;
	}
	public void setDirectSupervisor(String directSupervisor) {
		this.directSupervisor = directSupervisor;
	}
	public String getDepartmentHead() {
		return departmentHead;
	}
	public void setDepartmentHead(String departmentHead) {
		this.departmentHead = departmentHead;
	}
	public String getBenCo() {
		return benCo;
	}
	public void setBenCo(String benCo) {
		benCo = benCo;
	}
	public Double getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public Double getAwardedAmount() {
		return awardedAmount;
	}
	public void setAwardedAmount(Double awardedAmount) {
		this.awardedAmount = awardedAmount;
	}
	public List<UUID> getRequest() {
		return reForm;
	}
	public void setRequest(List<UUID> request) {
		this.reForm = request;
	}
	public List<Notification> getNotification() {
		if(notification ==  null) {
			notification = new ArrayList<Notification>();
		}
		return notification;
	}
	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(awardedAmount, benCo, departmentHead, directSupervisor, email, name, notification,
				pendingAmount, reForm, type);
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
		User other = (User) obj;
		return Objects.equals(awardedAmount, other.awardedAmount) && Objects.equals(benCo, other.benCo)
				&& Objects.equals(departmentHead, other.departmentHead)
				&& Objects.equals(directSupervisor, other.directSupervisor) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(notification, other.notification)
				&& Objects.equals(pendingAmount, other.pendingAmount) && Objects.equals(reForm, other.reForm)
				&& type == other.type;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", type=" + type + ", directSupervisor=" + directSupervisor
				+ ", departmentHead=" + departmentHead + ", benCo=" + benCo + ", pendingAmount=" + pendingAmount
				+ ", awardedAmount=" + awardedAmount + ", reForm=" + reForm + ", notification=" + notification + "]";
	}

	
}
