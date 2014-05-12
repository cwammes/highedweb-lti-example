package org.highedweb.lti.dao;

import java.util.List;

import org.highedweb.lti.domain.Assignment;

public interface AssignmentDAO {
	
	public Assignment addAssignment(Assignment assignment);
	
	public Assignment updateAssignment(Assignment assignment);
	
	public Assignment getAssignmentByContextAndUserId(String contextId, String userID);
	
	public List<Assignment> getAssignmentsByContext(String contextId);

}
