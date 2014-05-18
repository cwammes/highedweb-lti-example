package org.highedweb.lti.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.highedweb.lti.dto.LtiParams;

public interface LtiService {
	 
	public void OauthValidator(HttpServletRequest request) throws Exception;
	
	public List<LtiParams> setLtiParams(HttpServletRequest request);

}
