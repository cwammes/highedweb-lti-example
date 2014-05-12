package org.highedweb.lti.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.highedweb.lti.dao.LtiOauthDAO;
import org.highedweb.lti.dao.LtiOauthDAOImpl;
import org.highedweb.lti.dto.LtiParams;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthValidator;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;

public class OauthValidationImpl implements OauthValidation{

	Logger logger = Logger.getLogger(getClass());
	private LtiOauthDAO ltiOauthDAO;
	
	public void setLtiOauthDAO(LtiOauthDAO ltiOauthDAO){
		this.ltiOauthDAO = ltiOauthDAO;
	}
	
	public void OauthValidator(HttpServletRequest request) throws Exception{
		
		String oauth_consumer_key = request.getParameter("oauth_consumer_key");
		String oauth_secret;
		
		//Get Secret
		oauth_secret = ltiOauthDAO.getLtiOath(oauth_consumer_key).getOauthSecret();
		
		OAuthMessage oam = OAuthServlet.getMessage(request, null);
		OAuthValidator oav = new SimpleOAuthValidator();
		OAuthConsumer cons = new OAuthConsumer("about:blank#OAuth+CallBack+NotUsed", 
			oauth_consumer_key, oauth_secret, null);

		OAuthAccessor acc = new OAuthAccessor(cons);	
		
		try{
			logger.info("Validating Oauth Message");
			oav.validateMessage(oam,acc);
		}
		catch(Exception e){
			logger.warn(e.getMessage());
			throw new Exception();
		}
		
	}

	@Override
	public List<LtiParams> setLtiParams(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        List <LtiParams> ltiParams = new ArrayList();
        
        //Get list of parameters
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                logger.info(paramName + "<-->" + paramValue);
                LtiParams ltiParam = new LtiParams();
                ltiParam.setParamName(paramName);
                ltiParam.setParamValue(paramValue);
                ltiParams.add(ltiParam);
            }
 
        }		
        
        return ltiParams;
	}
	
}
