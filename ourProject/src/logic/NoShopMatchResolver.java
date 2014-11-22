package logic;

import model.IShoppingSite;
import model.ISubscriber;
import model.ShoppingSite;
import model.affiliateCompany.IAffiliateCompany;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AffiliatorsProvider;

public class NoShopMatchResolver implements IRedirectResolver {

	static final Logger logger = LogManager.getLogger(NoShopMatchResolver.class);
	
	private IAffiliateCompany defaultAffilator;
	private IShoppingSite defaultShoppingSite;
	
	// resolvers should be stateless and can be retrieved from RedirectResolvers
	// to prevent accidental wrong creation of resolvers we require in the c'tor to get a param that users will not send
	// the parameter that we get here is actually not needed, but there is no cost in runtime for that
	NoShopMatchResolver(Class<RedirectResolver> r) {}
	
	private void setDefaults() {
		// by default if we cannot find a matching resolver we try viglink
		defaultAffilator = AffiliatorsProvider.getProvider().getAffiliator("viglinks");
		defaultShoppingSite = new ShoppingSite("no matching shop", "no domain", "no desc", "no logo", defaultAffilator, 100, 0);		
	}


	@Override
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite) {
		// due to order of initialization the below cannot be done in c'tor or in initialization of the class
		if(defaultAffilator == null) {
			setDefaults();
		}
		
		String retUrl = destinationUrl;
		// we got here because shop was not found (or for some fault, shop is found but doesn't have affiliator - this is a bug in our db!)
		if(shoppingSite != null) {
			logger.error("ShopingSite found, check a problem with shoppingSite: " + shoppingSite.getName() + " cannot affiliate - trying with NoShopMatchResolver");			
		}
		
		// we didn't find any match to any shop, thus we need to redirect to the exact requested url
		logger.warn("we didn't find any match to any shop, thus we will go viglinks redirect url anyhow");
		
		// build the url with the defaultShoppingSite
		retUrl = defaultShoppingSite.getRedirectResolver().buildUrl(destinationUrl, subscriber, defaultShoppingSite);

		return retUrl;
	}

}
