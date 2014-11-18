package logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.IShoppingSite;
import model.ISubscriber;

public class UrlBuilder {
	static final Logger logger = LogManager.getLogger(UrlBuilder.class);

	public static String buildRedirectUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite){
		String redirectUrl = destinationUrl;
		//TODO: go to affiliation url with our key and referal url
		if (subscriber == null || shoppingSite == null){
			logger.error("subscriber or shoppingSite is null");
		}else{
			redirectUrl = shoppingSite.getAffiliateCompany().calcAffiliatedUrl(subscriber.getAffiliationSId(), destinationUrl);
		}
		return redirectUrl;
	}
}
