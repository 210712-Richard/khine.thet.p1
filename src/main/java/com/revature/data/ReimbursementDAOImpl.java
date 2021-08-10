package com.revature.data;

import java.security.Timestamp;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.datastax.oss.driver.api.core.data.TupleValue;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.TupleType;
import com.esri.core.geometry.NonSimpleResult.Reason;
import com.revature.bean.Approval;
import com.revature.bean.Attachment;
import com.revature.bean.GradingFormat;
import com.revature.bean.ReimbursementApproval;
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
	
	private static final TupleType APPROVAL_TUPLE = DataTypes.tupleOf(DataTypes.TIMESTAMP, DataTypes.TEXT, DataTypes.TEXT);
	
	@Override
	public void addReimbursementForm(ReimbursementForm reimbursement) {
		String query = "Insert into reimbursementform (id, name, deptName, submittedDate, approvalDate, "
					 + "location, description, cost, gradeformat, type, timemissed, urgent, "
					 + "attachment, supervisorapproval, departmentheadapproval, bencoapproval) "
					 + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UUID id = UUID.randomUUID();
		TupleValue supervisorApproval = APPROVAL_TUPLE 
				.newValue(reimbursement.getSupervisorApproval().getDealine(), 
						  reimbursement.getSupervisorApproval().getStatus(), 
						  reimbursement.getSupervisorApproval().getReason());
		TupleValue departmentHeadApproval = APPROVAL_TUPLE
				.newValue(reimbursement.getDepartmentHeadApproval().getDealine(),
						   reimbursement.getDepartmentHeadApproval().getStatus(),
						   reimbursement.getDepartmentHeadApproval().getReason());
						   
		TupleValue benCoApproval = APPROVAL_TUPLE
				.newValue(reimbursement.getBenCoApproval().getDealine(),
						  reimbursement.getBenCoApproval().getStatus(),
						  reimbursement.getBenCoApproval().getReason());
		
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
							.build();
		
		BoundStatement bound = session.prepare(s).bind(id, reimbursement.getName(), reimbursement.getDeptName(),
								reimbursement.getSubmittedDate(), reimbursement.getApprovalDate(),
								reimbursement.getLocation(), reimbursement.getDescription(), reimbursement.getCost(),
								reimbursement.getFormat(), reimbursement.getType(), reimbursement.getWorkTimeMissed(),
								reimbursement.getUrgent(), reimbursement.getAttachment(), 
								supervisorApproval, departmentHeadApproval, benCoApproval);
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
			rf.setSupervisorApproval(new ReimbursementApproval(
					row.getTupleValue("supervisorApproval").get(0, LocalDateTime.class),
					row.getTupleValue("supervisorApproval").get(1, Approval.class),
					row.getTupleValue("supervisorApproval").get(2,  String.class)));
			rf.setDepartmentHeadApproval(new ReimbursementApproval(
					row.getTupleValue("departmentheadApproval").get(0, LocalDateTime.class),
					row.getTupleValue("departmentheadApproval").get(1, Approval.class),
					row.getTupleValue("departmentheadApproval").get(2, String.class)));
			rf.setBenCoApproval(new ReimbursementApproval(
					row.getTupleValue("bencoApproval").get(0, LocalDateTime.class),
					row.getTupleValue("bencoApproval").get(1, Approval.class),
					row.getTupleValue("bencoApproval").get(2, String.class)));
			reform.add(rf);
		
		});
		return reform;
	}
	
	@Override
	public ReimbursementForm getReimbursementFormByNameandId(UUID id, String name) {
		String query = "Select id, name, deptName, submittedDate, approvalDate, "
					 + "location, description, cost, format, type, timemissed, urgent, "
					 + "attachment, supervisorApproval, departmentheadApproval, bencoapproval where id = ? and name = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(id.toString(), name);
		
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
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
		rf.setSupervisorApproval(new ReimbursementApproval(
				row.getTupleValue("supervisorApproval").get(0, LocalDateTime.class),
				row.getTupleValue("supervisorApproval").get(1, Approval.class),
				row.getTupleValue("supervisorApproval").get(2,  String.class)));
		rf.setDepartmentHeadApproval(new ReimbursementApproval(
				row.getTupleValue("departmentheadApproval").get(0, LocalDateTime.class),
				row.getTupleValue("departmentheadApproval").get(1, Approval.class),
				row.getTupleValue("departmentheadApproval").get(2, String.class)));
		rf.setBenCoApproval(new ReimbursementApproval(
				row.getTupleValue("bencoApproval").get(0, LocalDateTime.class),
				row.getTupleValue("bencoApproval").get(1, Approval.class),
				row.getTupleValue("bencoApproval").get(2, String.class)));
		
		return rf;
	}
	
	@Override
	public void updateReimbursementForm(ReimbursementForm reForm) {
		String query = "Update reimbursementform set approvalDate = ?, urgent = ?, attachment = ?, "
					 + "supervisorApproval = ?, departmentheadApproval = ?, bencoApproval = ? where name = ? and id = ?";
		
		TupleValue supervisorApproval = APPROVAL_TUPLE 
				.newValue(reForm.getSupervisorApproval().getDealine(), 
						  reForm.getSupervisorApproval().getStatus(), 
						  reForm.getSupervisorApproval().getReason());
		TupleValue departmentHeadApproval = APPROVAL_TUPLE
				.newValue(reForm.getDepartmentHeadApproval().getDealine(),
						   reForm.getDepartmentHeadApproval().getStatus(),
						   reForm.getDepartmentHeadApproval().getReason());
						   
		TupleValue benCoApproval = APPROVAL_TUPLE
				.newValue(reForm.getBenCoApproval().getDealine(),
						  reForm.getBenCoApproval().getStatus(),
						  reForm.getBenCoApproval().getReason());
		
		SimpleStatement s = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
				
		BoundStatement bound = session.prepare(s).bind(
				reForm.getApprovalDate(),
				reForm.getUrgent(),
				reForm.getAttachment(),
				supervisorApproval,
				departmentHeadApproval,
				benCoApproval);
		session.execute(bound);
	}
	
	@Override
	public void deleteReimbursementForm(String name, UUID id) {
		String query = "Delete from reimbursementform where name = ?, id = ?";
		BoundStatement bound = session
				.prepare(new SimpleStatementBuilder(query)
						.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
						.build())
				.bind(name, id);
		session.execute(bound);
	}
	
}
