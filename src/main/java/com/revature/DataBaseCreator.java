package com.revature;

import com.revature.data.*;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAOImpl();
	private static ReimbursementDAO rd = new ReimbursementDAOImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP THE TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXIST Form");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
	}

	public static void createTables() {
//		StringBuilder sb = new StringBuilder("CREATE TABLE OF NOT EXISTS User (")
//				.append("username text PRIMARY KEY, type test, ")
//				.append(")
		
	}

	public static void populateUserTable() {
		// TODO Auto-generated method stub
		
	}

	public static void populateReimbursementTable() {
		// TODO Auto-generated method stub
		
	}

}
