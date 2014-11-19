package servlets;

import helpers.IUrlAnalyzer;
import helpers.RestLikeUrlAnalyzer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.IRedirectResolver;
import logic.RedirectResolver;
import model.IShoppingSite;
import model.ISubscriber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import validators.IParametersValidator;
import validators.ParametersValidator;
import dao.DatabaseManager;

/**
 * Servlet implementation class redirectServlet
 */
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger(RedirectServlet.class);
		
	private IParametersValidator validator = new ParametersValidator();
       //TODO add log4j logging
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request,response);
	}

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		
		//---------------------------------------------------------------------
		// TODO configure log4j to add this session id to all logs for trace purposes
		String uniqueSessionId = java.util.UUID.randomUUID().toString();
		// TODO put the session id into the Mapped Diagnostic Context of log4j 
		// see: https://blog.oio.de/2010/11/09/logging-additional-information-like-sessionid-in-every-log4j-message/
		// note that MDC is thread safe as it is being ThreadLocal, so no need to worry
		MDC.put("sessionId", uniqueSessionId);
		//---------------------------------------------------------------------
		
		String fullPath = request.getPathInfo();
		String qString = request.getQueryString();//uu= destination url , kk = subscriber key
		if(qString != null && qString!=""){
			fullPath = fullPath + "?" + qString;
		}
		logger.debug("IN: fullPath="+fullPath);
		
		//validate parameters
		if(validator.validateParameters(fullPath)){
			
			IUrlAnalyzer urlAnalayzer = new RestLikeUrlAnalyzer(fullPath);
			
			//get monlinks user data
			//String subcriberKey = request.getParameter("kk");
			String subcriberKey = urlAnalayzer.getSubcriberKey();
			logger.debug("subcriberKey="+subcriberKey);
			
			// fectch the subscriber
			// (in case of no match, the method returns a default subscriber and logs, so we do not need to check for that case)
			ISubscriber subscriber = DatabaseManager.findSubscriberObject(subcriberKey);
						
			//get shopping site data
			//String destinationUrl = request.getParameter("uu");
			String destinationUrl = urlAnalayzer.getDestinationUrl();
			logger.debug("destinationUrl="+destinationUrl);
			
			IShoppingSite  shoppingSite = DatabaseManager.findShoppingSiteObject(destinationUrl);
			//TODO in case of no shopping site try to use viglinks API or other APIs

			
			//----------------------------------
			// build the redirect url
			//----------------------------------
			// [1] get the appropriate RedirectResolver
			IRedirectResolver redirectResolver = RedirectResolver.getRedirectResolver(shoppingSite);
			// [2] get the redirect url
			String redirectUrl = redirectResolver.buildUrl(destinationUrl,subscriber,shoppingSite);
			
			logger.debug("OUT: redirectUrl="+redirectUrl);
			try {
				
				response.sendRedirect(redirectUrl);
				return;
			} catch (IOException e) {
				// TODO need to handle this error
				e.printStackTrace();
			}
			
		}else{
			logger.debug("The call is not valid");

		}

	}

//EXAMPLES:
//http://monlinks.com/r?kk=hufsha001&uu=http://il.nextdirect.com/en/g67144s8
//http://localhost:8080/monlinks/r?kk=hufsha001&uu=http://il.nextdirect.com/en/g67144s8
//http://ec2-54-173-113-48.compute-1.amazonaws.com:8080/monlinks/r?kk=hufsha001&uu=http://aliexpress.com
//http://ec2-54-173-113-48.compute-1.amazonaws.com:8080/monlinks/r?kk=hufsha001&uu=http://il.nextdirect.com/en/g672s1
	
//AiExpress
//http://www.aliexpress.com/item/0-12M-Animal-Cartoon-Thick-Winter-Baby-Rompers-Newborn-Baby-Clothing-Baby-Boy-Girl-Clothes-Jumpsuit/32222601535.html?s=p
	
	
//http://localhost:8080/monlinks/r/hufsha001/ci/up/14102014/ct/@u=http:/il.nextdirect.com/en/g67144s8
}
