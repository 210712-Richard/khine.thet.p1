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

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	
}
