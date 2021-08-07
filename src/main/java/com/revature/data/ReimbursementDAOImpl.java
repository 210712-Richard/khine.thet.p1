package com.revature.data;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.bean.GradingFormat;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementType;
import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class ReimbursementDAOImpl implements ReimbursementDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static final Logger log = LogManager.getLogger(ReimbursementDAOImpl.class);
	
	@Override
	public void addReimbursementForm(ReimbursementForm reimbursement) {
		String query = "Insert into reimbursementform (id, name, deptName, submittedDate, approvalDate, "
					 + "location, description, cost, gradeformat, type, timemissed, urgent, "
					 + "attachment, supervisorapproval, departmentheadapproval, bencoapproval) "
					 + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UUID id = UUID.randomUUID();
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
							.build();
		
		BoundStatement bound = session.prepare(s).bind(id, reimbursement.getName(), reimbursement.getDeptName(),
								reimbursement.getSubmittedDate(), reimbursement.getApprovalDate(),
								reimbursement.getLocation(), reimbursement.getDescription(), reimbursement.getCost(),
								reimbursement.getFormat(), reimbursement.getType(), reimbursement.getWorkTimeMissed(),
								reimbursement.getUrgent(), reimbursement.getAttachment(),reimbursement.getSupervisorApproval(),
								reimbursement.getDepartmentHeadApproval(), reimbursement.getBenCoApproval());
		session.execute(bound);
	}
	
	@Override
	public List<ReimbursementForm> getReimbursementForm(String name) {
		List<ReimbursementForm> reform = new ArrayList<ReimbursementForm>();
		String query = "Select id, name, deptName, submittedDate, approvalDate, "
					 + "location, description, cost, gradeformat, type, timemissed, urgent, "
					 + "attachment, supervisorapproval, departmentheadapproval, bencoapproval where username = ?";
		
		ResultSet rs = session.execute(new SimpleStatementBuilder(query).build());
		rs.forEach(row -> {
			ReimbursementForm rf = new ReimbursementForm();
			rf.setId(row.getUuid("id"));
			rf.setName(row.getString("name"));
			rf.setDeptName(row.getString("deptName"));
			rf.setSubmittedDate(row.getLocalDate("submittedDate"));
			rf.setApprovalDate(row.getLocalDate("approvalDate"));
			rf.setLocation(row.getString("location"));
			rf.setDescription(row.getString("description"));
			rf.setCost(row.getLong("cost"));
			rf.setFormat(GradingFormat.valueOf(row.getString("format")));
			rf.setType(ReimbursementType.valueOf(row.getString("type")));
			rf.setWorkTimeMissed(row.getString("timemissed"));
			rf.setUrgent(row.getBoolean("urgent"));
			rf.setAttachment(row.getList("attachment", Attachment.class));
			
		
		});
		return null;
	}
	@Override
	public ReimbursementForm getReimbursementFormByName(String user, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateReimbursementForm(String name, UUID id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteReimbursementForm(String name, UUID id) {
		// TODO Auto-generated method stub
		
	}
	
}
