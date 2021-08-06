package com.revature;

import com.revature.data.*;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAOImpl();
	private static ReimbursementDAO rd = new ReimbursementDAOImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXISTS ReForm");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXISTS Notification");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}

	public static void createTables() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, email text, type text, ")
				.append("directsupervisor text, departmenthead text, benco text, reform list<uuid> );");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS Reimbursementform (")
				.append("id uuid, name text, deptName text, submitteddate date, approvaldate date, ")
				.append("location text, description text, cost bigInt, gradeFormat text, ")
				.append("type text, timemissed text, urgent boolean, attachment list<uuid>, ")
				.append("primary key(id, name, submitteddate));");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS Notification (")
				.append("name text, id uuid, approvalstatus text, approvaldate date, reason text, ")
				.append("primary key(name, id));");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}

//	public static void populateUserTable() {
//		ud.addUser("Jelly", "jellybean@bean.com", "employee", "Dulce", "Cherry", "Reese");
//		
//	}

	public static void populateReimbursementTable() {
		// TODO Auto-generated method stub
		
	}

}
