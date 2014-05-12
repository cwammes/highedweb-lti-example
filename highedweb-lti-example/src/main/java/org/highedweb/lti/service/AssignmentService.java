package org.highedweb.lti.service;

import java.util.List;

import org.highedweb.lti.domain.Assignment;
import org.highedweb.lti.dto.LtiParams;

public interface AssignmentService {

	public Assignment addAssignment(Assignment assignment);
	
	public Assignment setAssignmentEntry(List<LtiParams> ltiParams);
	
}
