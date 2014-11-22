package logic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import model.IShoppingSite;
import model.ISubscriber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TemplatePlaceHolderResolver implements IRedirectResolver {
	static final Logger logger = LogManager.getLogger(TemplatePlaceHolderResolver.class);

	public final static String SUBSCRIBER_KEY_PLACE_HOLDER = "[SUBSCRIBER_KEY]";
	public final static String DESTINATION_URL_PLACE_HOLDER = "[DESTINATION_URL]";

	// resolvers should be stateless and can be retrieved from RedirectResolvers
	// to prevent accidental wrong creation of resolvers we require in the c'tor to get a param that users will not send
	// the parameter that we get here is actually not needed, but there is no cost in runtime for that
	TemplatePlaceHolderResolver(Class<RedirectResolver> r) {}
	
	@Override
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite) {
		String retUrl =  destinationUrl;
		String templateUrl = shoppingSite.getAffiliateCompany().getDeepLinkUrl();
		retUrl = buildUrlFromTemplate(destinationUrl, subscriber, templateUrl);	
		return retUrl;
	}
	
	//This method is package friendly for NoShop resolver
	String buildUrlFromTemplate(String destinationUrl, ISubscriber subscriber, String templateUrl) {
		String retUrl =  destinationUrl;
		logger.debug("templateUrl="+ templateUrl);
		
		try {
			// TODO need to check what happens if the destination url is already encoded
			String encodedUrl = URLEncoder.encode(destinationUrl, "UTF-8");
			//retUrl = deepLinkUrl +"?key="+subscriberId+"&out="+destinationUrl+"&loc=hufshatleida.co.il&format=text";
			//retUrl = deepLinkUrl +"?key="+subscriber.getAffiliationSId()+"&out="+destinationUrl;
			retUrl = templateUrl.replace(SUBSCRIBER_KEY_PLACE_HOLDER, subscriber.getAffiliationSId());
			retUrl = retUrl.replace(DESTINATION_URL_PLACE_HOLDER, encodedUrl);
		} catch (UnsupportedEncodingException e) {
			logger.error("Problem in encoding url:"+ destinationUrl , e);
		}
		
		return retUrl;

	}

}
