package org.highedweb.lti.dao;

import org.apache.log4j.Logger;
import org.highedweb.lti.domain.LtiOauth;

public class LtiOauthDAOImpl implements LtiOauthDAO{

	Logger logger = Logger.getLogger(getClass());
	private LtiOauth ltiOauth;
	
	public void setLtiOauth(LtiOauth ltiOauth){
		this.ltiOauth = ltiOauth;
	}
	
	@Override
	public LtiOauth getLtiOath(String oauthKey) {
		
		logger.info("Getting Oauth Secret for: " + oauthKey);
		return ltiOauth;
	}

}
