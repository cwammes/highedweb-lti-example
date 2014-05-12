package org.highedweb.lti.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/accessdenied")
public class AccessDeniedController {
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model){

        logger.info("Access denied");
    	
    	return "accessdenied-tile";
    }    
    
}
