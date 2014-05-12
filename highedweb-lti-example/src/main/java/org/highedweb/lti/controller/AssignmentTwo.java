package org.highedweb.lti.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.highedweb.lti.dto.AssignmentTwoSubmission;
import org.highedweb.lti.dto.LtiParams;
import org.highedweb.lti.service.OauthValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AssignmentTwo {

	Logger logger = Logger.getLogger(getClass());
	
	private OauthValidationImpl oauthValidationService;
	
	@Autowired
	AssignmentTwo(OauthValidationImpl oauthValidationService){
		this.oauthValidationService = oauthValidationService;
	}		
	
	@RequestMapping(value="assignmentTwo", method = RequestMethod.POST)
	public String assignmentTwoPost(HttpServletRequest request,Model model){
			
			//Check if they are authorized to see this
			try{
				oauthValidationService.OauthValidator(request);
				
				//Get List of LTI Parameters and put into session
		        List <LtiParams> ltiParams = oauthValidationService.setLtiParams(request);
				
				//Return the instructor view
				if(request.getParameter("roles").toUpperCase().indexOf("INSTRUCTOR") != -1){
					logger.info("Role has Instructor");
					
					//Get assignments for this course
					
					//Add assignments to the model
					
					//Return to the view
					return "assignmentTwo/instructor";
				}
				
				//Return the student view
				else{
					AssignmentTwoSubmission assignmentTwoSubmission = new AssignmentTwoSubmission();
					
					model.addAttribute("assignmentTwoSubmission", assignmentTwoSubmission);
					
					logger.info("Returning student view");
					return "assignmentTwo/student";
				}
				
			}
			
			//Access denied
			catch(Exception e){
				return "accessdenied";
			}
		
	}
	
	@RequestMapping(value="assignmentTwoPost", method = RequestMethod.POST)
	public String assignmentTwoPostAssignment(HttpServletRequest request, @ModelAttribute("assignmentTwoSubmission") AssignmentTwoSubmission assignmentTwoSubmission,Model model){
		
		logger.info("Student Assignment Submitted");
		model.addAttribute("assignmentTwoSubmission", assignmentTwoSubmission);
		
		return "assignmentTwo/student";
	}
	
}
