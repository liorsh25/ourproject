package helpers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RestLikeUrlAnalyzer extends UrlAnalyzer {

	static final Logger logger = LogManager.getLogger(RestLikeUrlAnalyzer.class);

	private String[] restArr = null;
	private String destinationUrl = "";

	public RestLikeUrlAnalyzer(String urlStringToAnalayz) {
		restArr = urlStringToAnalayz.split("/");
		String[] destinationUrlArr = urlStringToAnalayz.split("/@u=");
		if(destinationUrlArr.length == 2){
			destinationUrl = destinationUrlArr[1];
			logger.debug("destinationUrl="+ destinationUrl);
			if(destinationUrl.startsWith("http")){
				//for some reason remove one "/" from the http://
				destinationUrl = destinationUrl.replace(":/", "://");
			}else{
				destinationUrl = "http://"+destinationUrl;
			}
		}else{
			logger.error("not valid destination url:"+ 	urlStringToAnalayz );
		}

		logger.debug("destinationUrl in end of constructor:"+ destinationUrl);
	}

	@Override
	public String getDestinationUrl() {
		//for example: monlinks.com/r/hufsha001/ci/up/14102014/ct/@u=http://il.nextdirect.com/en/g67144s8
		return destinationUrl;
	}

	@Override
	public String getSubcriberKey() {
		return restArr[1];
	}

}
