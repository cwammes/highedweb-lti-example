package org.highedweb.lti.service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.highedweb.lti.dao.LtiOauthDAO;
import org.highedweb.lti.dao.LtiOauthDAOImpl;
import org.highedweb.lti.domain.Assignment;
import org.highedweb.lti.dto.LtiParams;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthUtil;
import com.google.gdata.util.common.util.Base64;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthMessage;
import net.oauth.OAuthValidator;
import net.oauth.SimpleOAuthValidator;
import net.oauth.server.OAuthServlet;

public class LtiServiceImpl implements LtiService{

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
	
	public void LtiOutcomeService(String key, Assignment assignment){
		try {
			
			String oauth_consumer_key = key;
			String oauth_secret = ltiOauthDAO.getLtiOath(oauth_consumer_key).getOauthSecret();

			
			String body = setOutcomeBody(assignment);
			logger.info("\nBody:\n" + body);
			
			HttpURLConnection con = null;
			
			OAuthHmacSigner hmacSigner = new OAuthHmacSigner();
			OAuthParameters params = new OAuthParameters();
			params.setOAuthConsumerKey(oauth_consumer_key);
			params.setOAuthConsumerSecret(oauth_secret);
			params.setOAuthNonce(OAuthUtil.getNonce());
			params.setOAuthTimestamp(OAuthUtil.getTimestamp());
			params.setOAuthSignatureMethod("HMAC-SHA1");
			params.addCustomBaseParameter("oauth_version", "1.0");
			params.addCustomBaseParameter("realm", "");
			
			String method = "GET";
			if (body != null) {
				method = "POST";
				
				MessageDigest digest;
				digest = MessageDigest.getInstance("SHA-1");
	
				digest.reset();
				byte[] hash = digest.digest(body.getBytes("UTF-8"));
				String encodedHash = Base64.encode(hash);
				
				params.addCustomBaseParameter("oauth_body_hash", encodedHash);
			}
			
			String baseString = OAuthUtil.getSignatureBaseString(assignment.getLisOutcomeServiceUrl(), method, params.getBaseParameters());
			logger.info("\nBase String\n" + baseString + "\n");
			
			//String signature = rsaSigner.getSignature(baseString, params);
			String signature = hmacSigner.computeSignature(baseString);

			params.addCustomBaseParameter("oauth_signature", signature);
			
			URL url = new URL(assignment.getLisOutcomeServiceUrl());
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.addRequestProperty("Authorization",	buildAuthHeaderString(params));
			con.addRequestProperty("Host", "localhost:8080");
			
			logger.info("\nbuildAuthHeaderString(params)\n" + buildAuthHeaderString(params) + "\n");

			if (body != null) {
				//con.addRequestProperty("host", "localhost");
				con.addRequestProperty("Content-type", "application/xml");
				con.addRequestProperty("Content-Length", Integer.toString(body.length()));
			}
			con.connect();

			if (body != null) {
				OutputStreamWriter request = new OutputStreamWriter(con.getOutputStream());
				request.append(body);
				request.flush();
				request.close();
			}
						
			String response = con.getResponseMessage();
			logger.info("Response Code: " + con.getResponseCode());
			logger.info("Response:   \n" + response);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			

	}
	
	private String buildAuthHeaderString(OAuthParameters params) {
		StringBuffer buffer = new StringBuffer();
		int cnt = 0;
		buffer.append("OAuth ");
		Map<String, String> paramMap = params.getBaseParameters();
		Object[] paramNames = paramMap.keySet().toArray();
		for (Object paramName : paramNames) {
			String value = paramMap.get((String) paramName);
			buffer.append(paramName + "=\"" + OAuthUtil.encode(value) + "\"");
			cnt++;
			if (paramNames.length > cnt) {
				buffer.append(",");
			}

		}
		return buffer.toString();
	}
	
	private String setOutcomeBody(Assignment assignment){
		
		String uniqueID = UUID.randomUUID().toString();

		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><imsx_POXEnvelopeRequest xmlns=\"http://www.imsglobal.org/services/ltiv1p1/xsd/imsoms_v1p0\">"
				+ "<imsx_POXHeader><imsx_POXRequestHeaderInfo><imsx_version>V1.0</imsx_version><imsx_messageIdentifier>UNIQUEID</imsx_messageIdentifier>"
				+ "</imsx_POXRequestHeaderInfo></imsx_POXHeader><imsx_POXBody><replaceResultRequest><resultRecord><sourcedGUID>"
				+ "<sourcedId>SOURCEID</sourcedId></sourcedGUID><result><resultScore><language>en</language><textString>RESULT</textString></resultScore></result>"
				+ "</resultRecord></replaceResultRequest></imsx_POXBody></imsx_POXEnvelopeRequest>";
		
		Double grade = Double.parseDouble(assignment.getAssignmentGrade())/100;
				
		body = body.replace("SOURCEID", assignment.getLisResultSourcedid());
		body = body.replace("RESULT", grade.toString() );
		body = body.replace("UNIQUEID", uniqueID);
				
		return body;
		
	}
	
}
