package com.revature.data;

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

import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class UserDAOImpl {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	public void addUser(User u) {
		String query = "Insert into user (username, email, type, supervisor, department head) values (?, ?, ?, ?, ?, ?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getName(), u.getEmail(), u.getType().toString(), u.getDirectSupervisor(), u.getDepartmentHead());
		session.execute(bound);
	}

	public List<User> getUsers() {
		String query = "Select name, email, type, supervisor and department head from user";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setName(row.getString("username"));
			u.setEmail(row.getString("email"));
			u.setType(UserType.valueOf(row.getString("type")));
			u.setDirectSupervisor(row.getString("direct_supervisor"));
			u.setDepartmentHead(row.getString("department_head"));
			
			users.add(u);
		});
		return users;
	}

	public User getUser(String username) {

		String query = "Select username, email, type, supervisor, department head from user where username=?";
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

		return user;
	}

	public void updateUser(User u) {
		String query = "Update user set type = ?, email = ?, id = ? supervisor = ?, deaprtment head = ? where username = ?;";
		List<UUID> id = u.getId()
				.stream()
				.filter(id -> id!=null)
				.map(id -> id.getId())
				.collect(Collectors.toList());
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getType().toString(), u.getEmail(), id, u.getDirectSupervisor(), u.getDepartmentHead());
		session.execute(bound);
	}
	
	public List<UUID> getId(String username) {
		String query = "Select inbox from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		List<UUID> id = row.getList("id", UUID.class);
		return id;
	}
}
