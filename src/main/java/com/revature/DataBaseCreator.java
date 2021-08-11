package com.revature;

import java.time.LocalDate;

import com.revature.bean.GradingFormat;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementType;
import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.data.*;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAOImpl();
	private static ReimbursementDAO rd = new ReimbursementDAOImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("DROP TABLE IF EXISTS Reimbursementform");
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
				.append("id uuid, name text, submitteddate date, approvaldate date, ")
				.append("location text, description text, cost bigInt, gradeFormat text, ")
				.append("type text, timemissed text, urgent boolean, attachment list<uuid>, ")
				.append("supervisorapproval tuple<timestamp, text, text>, ")
				.append("departmentheadapproval tuple<timestamp, text, text>, ")
				.append("bencoapproval tuple<timestamp, text, text>, ")
				.append("primary key(id, name, submitteddate));");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS Notification (")
				.append("name text, id uuid, approvalstatus text, approvaldate date, reason text, ")
				.append("primary key(name, id));");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}

	public static void populateUserTable() {
		//Adding Supervisor
		User  u = new User("Jelly", "jellybean@bean.com", "Cherry", "Reese");
		u.setType(UserType.DIRECT_SUPERVISOR);
		ud.addUser(u);
		User  u1 = new User("Dulce", "dulce@candy.com", "Basil", "Ferrero");
		u1.setType(UserType.DIRECT_SUPERVISOR);
		ud.addUser(u1);
		
		//Adding Department Head
		User u2 = new User("Cherry", "Cherie@hill.com", "Ferrero");
		u2.setType(UserType.DEPARTMENT_HEAD);
		ud.addUser(u2);
		User u3 = new User("Basil", "basil@leef.com", "Reese");
		u3.setType(UserType.DEPARTMENT_HEAD);
		ud.addUser(u3);
		
		//Adding BenCo
		User u4 = new User("Reese", "reesecup@mini.com");
		u4.setType(UserType.BENCO);
		ud.addUser(u4);
		User u5 = new User("Ferrero", "rocherf@fero.com");
		u5.setType(UserType.BENCO);
		ud.addUser(u5);
		
		//Adding EMPLOYEE
		ud.addUser(new User("Iris", "Iris@irie.com", "Dulce", "Basil", "Reese"));
		ud.addUser(new User("Florence", "florence@gmail.com", "Jelly", "Cherry", "Ferrero"));
		ud.addUser(new User("Sunny", "sunny@shiny.com", "Jelly", "Cherry", "Ferrero"));
		ud.addUser(new User("Henry", "hennie@re.com", "Jelly", "Cherry", "Ferrero"));
		ud.addUser(new User("William", "liam@wii.com", "Dulce", "Basil", "Reese"));
	}

	public static void populateReimbursementTable() {
		rd.addReimbursementForm(new ReimbursementForm("Sunny", LocalDate.of(2021, 07, 14), "123 Flower Road, Brooklyn NY 12345",
								"8 weeks university course for Organic Chemistry.", 400.75, GradingFormat.LETTER_GRADE, ReimbursementType.UNIVERSITY,
								"Once a week", true));
		
	}

}
