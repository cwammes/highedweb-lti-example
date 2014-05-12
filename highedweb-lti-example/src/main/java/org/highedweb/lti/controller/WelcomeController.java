package org.highedweb.lti.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.highedweb.lti.dto.LtiParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

	Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value="welcome", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request,Model model){

		//Return to welcome view
		return "welcome";
	}
	
	@RequestMapping(value="assignments", method = RequestMethod.GET)
	public String assignmentsGet(HttpServletRequest request,Model model){

		return "assignments";
	}		
	
	@RequestMapping(value="assignments", method = RequestMethod.POST)
	public String assignmentsPost(HttpServletRequest request,Model model){

        Enumeration<String> parameterNames = request.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                logger.info(paramName + "<-->" + paramValue);
            }
 
        }
			
		return "assignments";
	}	
	

	
}

