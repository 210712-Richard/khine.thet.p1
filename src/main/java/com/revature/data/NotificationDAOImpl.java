package com.revature.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.bean.Notification;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class NotificationDAOImpl implements NotificationDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static final Logger log = LogManager.getLogger(NotificationDAOImpl.class);
	
	@Override
	public void createNotification(Notification noti) {
		log.trace("createNotification method called");
		String query = "Insert into notification "
				+ "(name, id, approvalStatus, approvalDate, reason) "
				+ "values (?, ?, ?, ?, ?);";
		UUID id = UUID.randomUUID();
		
		SimpleStatement s = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		
		BoundStatement bound = session.prepare(s).bind(noti.getName(), id,
							   noti.getApprovalStatus(), noti.getApprovalDate(),
							   noti.getReason());
		session.execute(bound);
		
	}

	@Override
	public Notification getNotificationById(String username) {
		log.trace("getNotificationById method called");
		String query = "Select name, id, approvalStatus, approvalDate, reason where name = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(username);
		
		ResultSet rs = (ResultSet) session.execute(bound);
		Row row = ((PagingIterable<Row>) rs).one();
		if(row == null) {
			return null;
		}
		Notification noti = new Notification();
		noti.setName(row.getString("name"));
		noti.setId(row.getUuid("id"));
		noti.setApprovalStatus(row.getString("approvalStatus"));
		noti.setApprovalDate(row.getLocalDate("approvalDate"));
		noti.setReason(row.getString("reason"));
		
		return noti;
	}

}
