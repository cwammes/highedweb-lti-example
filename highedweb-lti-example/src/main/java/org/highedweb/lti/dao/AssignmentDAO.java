package org.highedweb.lti.dao;

import java.util.List;

import org.highedweb.lti.domain.Assignment;

public interface AssignmentDAO {
	
	public Assignment addAssignment(Assignment assignment);
	
	public Assignment updateAssignment(Assignment assignment);
	
	public Assignment getAssignmentByContextAndUserId(String contextId, String userID);
	
	public Assignment getAssignmentByContextUserIdResourceLinkId(String contextId, String userId, String resourceLinkId);
	
	public List<Assignment> getAssignmentsByContext(String contextId);
	
	public List<Assignment> getAssignmentsByContextResourceLinkId(String contextId, String resourceLinkId);
	
	public Assignment getAssignmentById(int id);

}
