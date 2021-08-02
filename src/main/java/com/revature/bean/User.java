package com.revature.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.bean.Notification;
import com.revature.bean.Request;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private UUID id;
	private String email;
	private UserType type;
	private String directSupervisor;
	private String departmentHead;
	private Double pendingAmount;
	private Double awardedAmount;
	private List<Request> request;
	private List<Notification> notification;
	
	public User() {
		super();
		this.type = UserType.EMPLOYEE;
		this.request = new ArrayList<Request>();
		this.pendingAmount = 0.00;
		this.awardedAmount = 0.00;
	}
	
	//employee
	public User(String name, UUID id, String email, String directSupervisor, String departmentHead) {
		this.name = name;
		this.id = id;
		this.email = email;
		this.directSupervisor = directSupervisor;
		this.departmentHead = departmentHead;
	}
	
	//Direct Supervisor
	public User(String name, UUID id, String email, String departmentHead) {
		this.name = name;
		this.id = id;
		this.email = email;
		this.departmentHead = departmentHead;
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
	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awardedAmount == null) ? 0 : awardedAmount.hashCode());
		result = prime * result + ((departmentHead == null) ? 0 : departmentHead.hashCode());
		result = prime * result + ((directSupervisor == null) ? 0 : directSupervisor.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notification == null) ? 0 : notification.hashCode());
		result = prime * result + ((pendingAmount == null) ? 0 : pendingAmount.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (awardedAmount == null) {
			if (other.awardedAmount != null) {
				return false;
			}
		} else if (!awardedAmount.equals(other.awardedAmount)) {
			return false;
		}
		if (departmentHead == null) {
			if (other.departmentHead != null) {
				return false;
			}
		} else if (!departmentHead.equals(other.departmentHead)) {
			return false;
		}
		if (directSupervisor == null) {
			if (other.directSupervisor != null) {
				return false;
			}
		} else if (!directSupervisor.equals(other.directSupervisor)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (notification == null) {
			if (other.notification != null) {
				return false;
			}
		} else if (!notification.equals(other.notification)) {
			return false;
		}
		if (pendingAmount == null) {
			if (other.pendingAmount != null) {
				return false;
			}
		} else if (!pendingAmount.equals(other.pendingAmount)) {
			return false;
		}
		if (request == null) {
			if (other.request != null) {
				return false;
			}
		} else if (!request.equals(other.request)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", email=" + email + ", type=" + type + ", directSupervisor="
				+ directSupervisor + ", departmentHead=" + departmentHead + ", pendingAmount=" + pendingAmount
				+ ", awardedAmount=" + awardedAmount + ", request=" + request + ", notification=" + notification + "]";
	}
}
