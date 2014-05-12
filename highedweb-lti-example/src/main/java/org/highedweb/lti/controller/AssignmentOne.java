package org.highedweb.lti.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.highedweb.lti.dto.LtiParams;
import org.highedweb.lti.service.OauthValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AssignmentOne {
	
	Logger logger = Logger.getLogger(getClass());
	
	private OauthValidationImpl oauthValidationService;
	
	@Autowired
	AssignmentOne(OauthValidationImpl oauthValidationService){
		this.oauthValidationService = oauthValidationService;
	}	
	
	@RequestMapping(value="assignmentOne", method = RequestMethod.POST)
	public String assignmentOnePost(HttpServletRequest request,Model model){
		
		try{
			
			oauthValidationService.OauthValidator(request);
	
			//Get List of LTI Parameters
	        List <LtiParams> ltiParams = oauthValidationService.setLtiParams(request);

	        //Put parameters into model
	        model.addAttribute("ltiParams", ltiParams);
			
	        //Return to view
			return "assignmentOne/assignmentOne";
		
		}
		catch(Exception e){
			return "accessdenied";
		}
		
	}	

}
