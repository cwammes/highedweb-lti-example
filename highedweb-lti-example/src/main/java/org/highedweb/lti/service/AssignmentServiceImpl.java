package org.highedweb.lti.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.highedweb.lti.dao.AssignmentDAO;
import org.highedweb.lti.domain.Assignment;
import org.highedweb.lti.domain.LtiOauth;
import org.highedweb.lti.dto.AssignmentOutcome;
import org.highedweb.lti.dto.LtiParams;

public class AssignmentServiceImpl implements AssignmentService{

	Logger logger = Logger.getLogger(getClass());
	private AssignmentDAO assignmentDAO;
	private OauthValidation oauthValidationService;
	
	public void setAssignmentDAO(AssignmentDAO assignmentDAO){
		this.assignmentDAO = assignmentDAO;
	}
	
	public void setOauthValidationService(OauthValidation oauthValidationService){
		this.oauthValidationService = oauthValidationService;
	}
	
	@Override
	public Assignment addAssignment(Assignment assignment) {
		assignment.setAssignmentSubmissionDate(new Date());

		
		return assignmentDAO.addAssignment(assignment);
	}

	@Override
	public Assignment initializeAssignmentEntry(List<LtiParams> ltiParams) {
		
		Assignment assignment = new Assignment();
		
		for(int x = 0; x < ltiParams.size(); x++){
			if(ltiParams.get(x).getParamName().matches("context_id"))
				assignment.setContextId(ltiParams.get(x).getParamValue());
			
			else if(ltiParams.get(x).getParamName().matches("user_id"))
				assignment.setUserId(ltiParams.get(x).getParamValue());
			
			else if(ltiParams.get(x).getParamName().matches("lis_person_name_family"))
				assignment.setLisPersonNameFamily(ltiParams.get(x).getParamValue());	
			
			else if(ltiParams.get(x).getParamName().matches("lis_person_name_given"))
				assignment.setLisPersonNameGiven(ltiParams.get(x).getParamValue());		
			
			else if(ltiParams.get(x).getParamName().matches("lis_person_name_full"))
				assignment.setLisPersonNameFull(ltiParams.get(x).getParamValue());	
			
			else if(ltiParams.get(x).getParamName().matches(""))
				assignment.setLisPersonContactEmailPrimary(ltiParams.get(x).getParamValue());
			
			else if(ltiParams.get(x).getParamName().matches("lis_outcome_service_url"))
				assignment.setLisOutcomeServiceUrl(ltiParams.get(x).getParamValue());		
			
			else if(ltiParams.get(x).getParamName().matches("lis_result_sourcedid"))
				assignment.setLisResultSourcedid(ltiParams.get(x).getParamValue());			
			
			else if(ltiParams.get(x).getParamName().matches("resource_link_id"))
				assignment.setResourceLinkId(ltiParams.get(x).getParamValue());			
		
		}
		
		return assignment;
	}

	@Override
	public Assignment updateAssignment(Assignment assignment) {
		assignment.setAssignmentSubmissionDate(new Date());
		
		return assignmentDAO.updateAssignment(assignment);
	}

	@Override
	public Assignment getAssignmentByContextAndUserId(Assignment assignment) {
		
		Assignment assignmentDomain = assignmentDAO.getAssignmentByContextUserIdResourceLinkId(assignment.getContextId(), assignment.getUserId(), assignment.getResourceLinkId());
		
		if(assignmentDomain != null && assignmentDomain.getUserId().matches(assignment.getUserId()))
			return assignmentDomain;
		else
			return assignment;
	}

	@Override
	public List<Assignment> getAssignmentsByContext(String contextId) {
		// TODO Auto-generated method stub
		return assignmentDAO.getAssignmentsByContext(contextId);
	}

	public List<Assignment> getAssignmentsByContextResourceLinkId(String contextId, String resourceLinkId) {
		// TODO Auto-generated method stub
		return assignmentDAO.getAssignmentsByContextResourceLinkId(contextId, resourceLinkId);
	}	
	
	@Override
	public Assignment setAssignmentOutomce(AssignmentOutcome assignmentOutcome) {
		Assignment assignment = assignmentDAO.getAssignmentById(Integer.parseInt(assignmentOutcome.getOutcomeId()));
		
		assignment.setAssignmentGrade(assignmentOutcome.getOutcomeGrade());
		assignment.setAssignmentGradeDate(new Date());
		
		assignment = assignmentDAO.updateAssignment(assignment);
		
		//Need to POST back to LMS
		
		return assignment;
	}

}
