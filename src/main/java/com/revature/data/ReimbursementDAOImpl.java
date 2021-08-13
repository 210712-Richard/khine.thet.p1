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
import com.revature.bean.Approval;
import com.revature.bean.GradingFormat;
import com.revature.bean.ReimbursementApproval;
import com.revature.bean.ReimbursementForm;
import com.revature.bean.ReimbursementRequest;
import com.revature.bean.ReimbursementType;
import com.revature.bean.User;
import com.revature.bean.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class ReimbursementDAOImpl implements ReimbursementDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static final Logger log = LogManager.getLogger(ReimbursementDAOImpl.class);
	
	private static final TupleType APPROVAL_TUPLE = DataTypes.tupleOf(DataTypes.TEXT, DataTypes.TEXT);
	
	@Override
	public void addReimbursementForm(ReimbursementForm reimbursement) {
		log.trace("addReimbursementForm method called");
		String query = "Insert into reimbursementform (id, name, submittedDate, approvalDate, "
					 + "location, description, cost, gradeformat, type, timemissed, urgent, "
					 + "attachment, supervisorapproval, departmentheadapproval, bencoapproval) "
					 + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UUID id = UUID.randomUUID();
		TupleValue supervisorApproval = APPROVAL_TUPLE 
				.newValue(reimbursement.getSupervisorApproval().getStatus(), 
						  reimbursement.getSupervisorApproval().getReason());
		TupleValue departmentHeadApproval = APPROVAL_TUPLE
				.newValue(reimbursement.getDepartmentHeadApproval().getStatus(),
						  reimbursement.getDepartmentHeadApproval().getReason());	   
		TupleValue benCoApproval = APPROVAL_TUPLE
				.newValue(reimbursement.getBenCoApproval().getStatus(),
						  reimbursement.getBenCoApproval().getReason());

		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
							.build();
		
		BoundStatement bound = session.prepare(s).bind(id, reimbursement.getName(),
								reimbursement.getSubmittedDate(), reimbursement.getApprovalDate(),
								reimbursement.getLocation(), reimbursement.getDescription(), reimbursement.getCost(),
								reimbursement.getFormat().toString(), reimbursement.getType().toString(), reimbursement.getWorkTimeMissed(),
								reimbursement.getUrgent(), reimbursement.getAttachment(), 
								supervisorApproval, departmentHeadApproval, benCoApproval);
		session.execute(bound);
	}
	
	@Override
	public List<ReimbursementForm> getReimbursementForm(String name) {
		log.trace("getReimbursementForm method called");
		List<ReimbursementForm> reform = new ArrayList<ReimbursementForm>();
		String query = "Select id, name, submittedDate, approvalDate, "
					 + "location, description, cost, gradeformat, type, timemissed, urgent, "
					 + "attachment, supervisorapproval, departmentheadapproval, bencoapproval from reimbursementform where name = ? ALLOW FILTERING";
		
		if(name == null) {
			return null;
		}
		SimpleStatement s = new SimpleStatementBuilder(query.toString()).build();
		BoundStatement bound = session.prepare(s).bind(name);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		
			ReimbursementForm rf = new ReimbursementForm();
			rf.setId(row.getUuid("id"));
			rf.setName(row.getString("name"));
			rf.setSubmittedDate(row.getLocalDate("submittedDate"));
			rf.setLocation(row.getString("location"));
			rf.setDescription(row.getString("description"));
			rf.setCost(row.getDouble("cost"));
			rf.setFormat(GradingFormat.valueOf(row.getString("gradeformat")));
			rf.setType(ReimbursementType.valueOf(row.getString("type")));
			rf.setWorkTimeMissed(row.getString("timemissed"));
			rf.setUrgent(row.getBoolean("urgent"));
			rf.setAttachment((row.getList("attachment", String.class)));
			rf.setSupervisorApproval(new ReimbursementApproval(
					row.getTupleValue("supervisorApproval").get(0,  String.class)));
			rf.setDepartmentHeadApproval(new ReimbursementApproval(
					row.getTupleValue("departmentheadApproval").get(0, String.class)));
			rf.setBenCoApproval(new ReimbursementApproval(
					row.getTupleValue("bencoApproval").get(0, String.class)));
			reform.add(rf);
		
		return reform;
	}
	
	@Override
	public ReimbursementForm getReimbursementFormByNameandId(UUID id, String name) {
		log.trace("getReimbursementFormByIdAndName method called");
//		String query = "Select id, name, submittedDate, approvalDate, "
//					 + "location, description, cost, gradeformat, type, timemissed, urgent, "
//					 + "attachment, supervisorApproval, departmentheadApproval, bencoapproval from reimbursementform where name = ? and id = ?";
//		
//		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
//		BoundStatement bound = session.prepare(s).bind(id, name);
//		ResultSet rs = session.execute(bound);
//		Row row = rs.one();
//		if(row == null) {
//			return null;
//		}
//		ReimbursementForm rf = new ReimbursementForm();
//		rf.setId(row.getUuid("id"));
//		rf.setName(row.getString("name"));
//		rf.setSubmittedDate(row.getLocalDate("submittedDate"));
//		rf.setLocation(row.getString("location"));
//		rf.setDescription(row.getString("description"));
//		rf.setCost(row.getDouble("cost"));
//		rf.setFormat(GradingFormat.valueOf(row.getString("gradeformat")));
//		rf.setType(ReimbursementType.valueOf(row.getString("type")));
//		rf.setWorkTimeMissed(row.getString("timemissed"));
//		rf.setUrgent(row.getBoolean("urgent"));
//		rf.setAttachment(row.getList("attachment", String.class));
//		rf.setSupervisorApproval(new ReimbursementApproval(
//				row.getTupleValue("supervisorApproval").get(0,  String.class)));
//		rf.setDepartmentHeadApproval(new ReimbursementApproval(
//				row.getTupleValue("departmentheadApproval").get(0, String.class)));
//		rf.setBenCoApproval(new ReimbursementApproval(
//				row.getTupleValue("bencoApproval").get(0, String.class)));
//		
//		return rf;
		List<ReimbursementForm> forms = getReimbursementForm(name);
		ReimbursementForm form = forms.stream().filter((f) -> f.getId().equals(id)).findFirst().orElse(null);
		return form;
	}
	
	@Override
	public void updateReimbursementForm(ReimbursementRequest reRequest) {
		log.trace("updateReimbursementForm method called");
		String query = "Update reimbursementform set approvalDate = ?, urgent = ?, attachment = ?, "
					 + "supervisorApproval = ?, departmentheadApproval = ?, bencoApproval = ? where name = ? and id = ?";
		
		TupleValue supervisorApproval = APPROVAL_TUPLE 
				.newValue(reRequest.getSupervisorApproval().getStatus(), 
						  reRequest.getSupervisorApproval().getReason());
		TupleValue departmentHeadApproval = APPROVAL_TUPLE
				.newValue(reRequest.getDepartmentHeadApproval().getStatus(),
						   reRequest.getDepartmentHeadApproval().getReason());
						   
		TupleValue benCoApproval = APPROVAL_TUPLE
				.newValue(reRequest.getBenCoApproval().getStatus(),
						  reRequest.getBenCoApproval().getReason());
		
		SimpleStatement s = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
				
		BoundStatement bound = session.prepare(s).bind(
				reRequest.getApprovalDate(),
				reRequest.getUrgent(),
				reRequest.getAttachment(),
				supervisorApproval,
				departmentHeadApproval,
				benCoApproval);
		session.execute(bound);
	}
	
	@Override
	public void deleteReimbursementForm(String name, UUID id) {
		log.trace("deleteReimbursementForm method called");
		String query = "Delete from reimbursementform where name = ? and id = ?";
		BoundStatement bound = session
				.prepare(new SimpleStatementBuilder(query)
						.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
						.build())
				.bind(name, id);
		session.execute(bound);
	}
	
}
