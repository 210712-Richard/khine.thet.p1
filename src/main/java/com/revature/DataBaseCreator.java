package com.revature;

import com.revature.data.*;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAOImpl();
	private static ReimbursementDAO rd = new ReimbursementDAOImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXISTS Form");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
	}

	public static void createTables() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS User (")
				.append("username text PRIMARY KEY, email text, type test, ")
				.append("directSupervisor text, departmentHead text, benCo text, reForm list<uuid> );");
		
//		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS reForm (")
//				.append("id uuid, name text, deptName text, submitteDate date, approvalDate date, ")
//				.append("location text, description text, cost bigInt, gradeFormat text, ")
//				.append("type text, timeMissed text, urgent boolean, attachment list<uuid> ")
//				.append("primary key(id, name, submittedDate));");
//		
//		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS notification (")
//				.append("name text, id uuid, approvalStatus boolean, approvalDate date, reason text, ")
//				.append("primary key(name, id));");
	}

//	public static void populateUserTable() {
//		ud.addUser("Jelly", "jellybean@bean.com", "employee", "Dulce", "Cherry", "Reese");
//		
//	}

	public static void populateReimbursementTable() {
		// TODO Auto-generated method stub
		
	}

}
