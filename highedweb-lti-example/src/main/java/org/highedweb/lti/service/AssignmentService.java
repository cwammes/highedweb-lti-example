package org.highedweb.lti.service;

import java.util.List;

import org.highedweb.lti.domain.Assignment;
import org.highedweb.lti.dto.AssignmentOutcome;
import org.highedweb.lti.dto.LtiParams;

public interface AssignmentService {

	public Assignment addAssignment(Assignment assignment);
	
	public Assignment initializeAssignmentEntry(List<LtiParams> ltiParams);
	
	public Assignment updateAssignment(Assignment assignment);
	
	public Assignment getAssignmentByContextAndUserId(Assignment assignment);
	
	public List<Assignment> getAssignmentsByContext(String contextId);
	
	public Assignment setAssignmentOutomce(AssignmentOutcome assignmentOutcome);
	
}
