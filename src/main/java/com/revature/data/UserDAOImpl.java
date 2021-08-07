package com.revature.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.bean.Notification;
import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class UserDAOImpl implements UserDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static final Logger log = LogManager.getLogger(UserDAOImpl.class);
	
	public void addUser(User u) {
		log.trace("addUser method called");
		
		String query = "Insert into user (username, email, type, directSupervisor, departmentHead, benCo, reForm) values (?, ?, ?, ?, ?, ?, ?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getName(), u.getEmail(), u.getType().toString(), u.getDirectSupervisor(), u.getDepartmentHead(), u.getBenCo(), u.getRequest());
		session.execute(bound);
	}

	public List<User> getUsers() {
		log.trace("getUsers method called");
		
		String query = "Select name, email, type, directSupervisor and departmentHead, benCo from user";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setName(row.getString("username"));
			u.setEmail(row.getString("email"));
			u.setType(UserType.valueOf(row.getString("type")));
			u.setDirectSupervisor(row.getString("directSupervisor"));
			u.setDepartmentHead(row.getString("departmentHead"));
			u.setBenCo(row.getString("benCo"));
			
			users.add(u);
		});
		return users;
	}

	public User getUser(String username) {
		log.trace("getUser method called");
		
		String query = "Select username, email, type, supervisor, department head, benCo from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);

		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		User user = new User();
		user.setName(row.getString("username"));
		user.setEmail(row.getString("email"));
		user.setType(UserType.valueOf(row.getString("type")));
		user.setDirectSupervisor(row.getString("direct_supervisor"));
		user.setDepartmentHead(row.getString("department_head"));
		user.setBenCo(row.getString("benCo"));

		return user;
	}  

	public void updateUser(User user) {
		log.trace("updateUser method called");
		
		String query = "Update user set type = ?, email = ?, id = ?, supervisor = ?, departmenthead = ?, benco = ? where username = ?;";
		List<Notification> notification = user.getNotification();
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(user.getType().toString(), user.getEmail(), notification, user.getDirectSupervisor(), user.getDepartmentHead(), user.getBenCo());
		session.execute(bound);
	}
	
	public List<Notification> getNotification(String username) {
		log.trace("getNotification method called");
		
		List<Notification> notifications = new ArrayList<Notification>();
		String query = "Select name, id, approvalStatus, approvalDate, reason from user where username = ?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		ResultSet rs = session.execute(bound);
		
		rs.forEach(row -> {
			Notification notification = new Notification();
			notification.setName(row.getString("name"));
			notification.setId(row.getUuid("id"));
			notification.setApprovalStatus(row.getString("approvalStatus"));
			notification.setApprovalDate(row.getLocalDate("approvalDate"));
			notification.setReason(row.getString("reason"));
			
			notifications.add(notification);
		});
		return notifications;
	}
}
