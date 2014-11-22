package helpers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RestLikeUrlAnalyzer extends UrlAnalyzer {

	static final Logger logger = LogManager.getLogger(RestLikeUrlAnalyzer.class);

	private String[] restArr = {};
	private String destinationUrl = "";

	public RestLikeUrlAnalyzer(String urlStringToAnalayze) {
		String[] destinationUrlArr;
		// we must have a valid url in order to analyze it
		if(urlStringToAnalayze != null && ((destinationUrlArr = urlStringToAnalayze.split("/@u=")).length == 2)){
			destinationUrl = destinationUrlArr[1];
			logger.debug("destinationUrl="+ destinationUrl);
			if(destinationUrl.startsWith("http")){
				// for some reason remove one "/" from the http://
				// TODO make sure this is consistent, maybe better check that we do not get :///
				destinationUrl = destinationUrl.replace(":/", "://");
			}else{
				destinationUrl = "http://"+destinationUrl;
			}
			// the first part is the rest of params
			restArr = destinationUrlArr[0].split("/");
		}else{
			logger.error("not valid destination url:"+ 	urlStringToAnalayze );
		}

		logger.debug("destinationUrl in end of constructor:"+ destinationUrl);
	}

	@Override
	public String getDestinationUrl() {
		//for example: monlinks.com/r/hufsha001/ci/up/14102014/ct/@u=http://il.nextdirect.com/en/g67144s8
		return destinationUrl;
	}

	// helper method
	private String getRestParam(int paramIndex) {
		// for index 1 needs 2 parameters and so on, thus >
		if(restArr.length > paramIndex) {
			return restArr[paramIndex];
		}
		else return null;		
	}
	
	final static private int SUBSCRIBER_KEY_POSITION = 1;
	final static private int CAMPAIGN_ID_POSITION 	 = 2;
	final static private int USER_PARAM_POSITION 	 = 3;
	final static private int LINK_DATE_POSITION		 = 4;
	final static private int CUSTOMER_CODE_POSITION	 = 5;
	
	
	@Override
	public String getSubcriberKey() {
		return getRestParam(SUBSCRIBER_KEY_POSITION);
	}

	@Override
	public String getCampaingId() {
		return getRestParam(CAMPAIGN_ID_POSITION);
	}

	@Override
	public String getUserParam() {
		return getRestParam(USER_PARAM_POSITION);
	}
	
	@Override
	public String getLinkDate() {
		return getRestParam(LINK_DATE_POSITION);
	}
	
	@Override
	public String getCustomerCode() {
		return getRestParam(CUSTOMER_CODE_POSITION);
	}
	
}
