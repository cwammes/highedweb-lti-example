package org.highedweb.lti.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.highedweb.lti.dao.AssignmentDAO;
import org.highedweb.lti.domain.Assignment;
import org.highedweb.lti.domain.LtiOauth;
import org.highedweb.lti.dto.LtiParams;

public class AssignmentServiceImpl implements AssignmentService{

	Logger logger = Logger.getLogger(getClass());
	private AssignmentDAO assignmentDAO;
	
	public void setAssignmentDAO(AssignmentDAO assignmentDAO){
		this.assignmentDAO = assignmentDAO;
	}	
	
	@Override
	public Assignment addAssignment(Assignment assignment) {
		return assignmentDAO.addAssignment(assignment);
	}

	@Override
	public Assignment setAssignmentEntry(List<LtiParams> ltiParams) {
		
		Assignment assignment = new Assignment();
		
		for(int x = 0; x < ltiParams.size(); x++){
			if(ltiParams.get(x).getParamName().matches(""))
				assignment.setContextId("");
		}
		
		return assignment;
	}

}
