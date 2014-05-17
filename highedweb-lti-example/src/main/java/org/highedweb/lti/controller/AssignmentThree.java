package org.highedweb.lti.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.highedweb.lti.domain.Assignment;
import org.highedweb.lti.dto.AssignmentOutcome;
import org.highedweb.lti.dto.AssignmentTwoSubmission;
import org.highedweb.lti.dto.LtiParams;
import org.highedweb.lti.service.AssignmentServiceImpl;
import org.highedweb.lti.service.OauthValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AssignmentThree {

	Logger logger = Logger.getLogger(getClass());
	
	private OauthValidationImpl oauthValidationService;
	private AssignmentServiceImpl assignmentService;
	
	@Autowired
	AssignmentThree(OauthValidationImpl oauthValidationService, AssignmentServiceImpl assignmentService){
		this.oauthValidationService = oauthValidationService;
		this.assignmentService = assignmentService;
	}
	
	@RequestMapping(value="assignmentThree", method = RequestMethod.POST)
	public String assignmentThreePost(HttpServletRequest request,Model model){
			
			//Check if they are authorized to see this
			try{
				oauthValidationService.OauthValidator(request);
				
				//Get List of LTI Parameters and put into session
		        List <LtiParams> ltiParams = oauthValidationService.setLtiParams(request);
		        HttpSession session = request.getSession();
		        Assignment assignment = assignmentService.initializeAssignmentEntry(ltiParams);
				
				//Return the instructor view
				if(request.getParameter("roles").toUpperCase().indexOf("INSTRUCTOR") != -1){
					logger.info("Role has Instructor");
					
					//Add assignment to the session
					session.setAttribute("assignment", assignment);
					
					//Get assignments for this course
					List <Assignment> assignmentList = assignmentService.getAssignmentsByContextResourceLinkId(assignment.getContextId(), assignment.getResourceLinkId());
					
					//Add assignments to the model
					model.addAttribute("assignmentList", assignmentList);
					
					//Create assignment outcome and add it to the model
					AssignmentOutcome assignmentOutcome = new AssignmentOutcome();
					model.addAttribute("assignmentOutcome", assignmentOutcome);
					
					//Return to the view
					return "assignmentThree/instructor";
				}
				
				//Return the student view
				else{
					//Check if assignment exists
					assignment = assignmentService.getAssignmentByContextAndUserId(assignment);
			        session.setAttribute("assignment", assignment);
					
					AssignmentTwoSubmission assignmentTwoSubmission = new AssignmentTwoSubmission();
					assignmentTwoSubmission.setTitle(assignment.getAssignmentTitle());
					assignmentTwoSubmission.setBody(assignment.getAssignmentBody());
					
					model.addAttribute("assignmentTwoSubmission", assignmentTwoSubmission);
					
					logger.info("Returning student view");
					return "assignmentTwo/student";
				}
				
			}
			
			//Access denied
			catch(Exception e){
				logger.error(e.getMessage());
				e.printStackTrace();
				return "accessdenied";
			}
		
	}
	
	@RequestMapping(value="assignmentThreeInstructorPost", method = RequestMethod.POST)
	public String assignmentThreePostStudent(HttpServletRequest request, @ModelAttribute("assignmentOutcome") AssignmentOutcome assignmentOutcome,Model model){
		
		logger.info("Instructor Outcome Submitted:  Id=" + assignmentOutcome.getOutcomeId() + "    Grade=" + assignmentOutcome.getOutcomeGrade());
		
        HttpSession session = request.getSession();
        Assignment assignment = (Assignment) session.getAttribute("assignment");
        
        //Set new assignment information
        assignment = assignmentService.setAssignmentOutomce(assignmentOutcome);
        
        oauthValidationService.LtiOutcomeService("key", assignment);

		//Get assignments for this course
		List <Assignment> assignmentList = assignmentService.getAssignmentsByContextResourceLinkId(assignment.getContextId(), assignment.getResourceLinkId());
		
		for(int x = 0; x < assignmentList.size(); x++)
			logger.info("Outcome Details:  StudentName=" + assignmentList.get(x).getLisPersonNameFull() + "&Outcome=" + assignmentList.get(x).getAssignmentGrade());
		
		//Add assignments to the model
		model.addAttribute("assignmentList", assignmentList);
		
		return "assignmentThree/instructor";
	}	
}
