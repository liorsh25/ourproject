package logic;

import model.IShoppingSite;
import model.ISubscriber;
import model.affiliateCompany.AffiliateCompany;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AffiliatorsProvider;

public class NoShopMatchResolver implements IRedirectResolver {

	static final Logger logger = LogManager.getLogger(NoShopMatchResolver.class);
	AffiliateCompany viglinks = (AffiliateCompany) AffiliatorsProvider.getProvider().getAffiliator("viglinks");
	
	@Override
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite) {
		String retUrl = destinationUrl;
		//in this case shoppingSite will alwayes be null
		
		// we didn't find any match to any shop, thus we need to redirect to the exact requested url
		logger.warn("we didn't find any match to any shop, thus we will go viglinks redirect url anyhow");
		TemplatePlaceHolderResolver templateResolver = (TemplatePlaceHolderResolver) viglinks.getRedirectResolver();
		retUrl = templateResolver.buildUrlFromTemplate(destinationUrl, subscriber, viglinks.getDeepLinkUrl());
		return retUrl;
	}

}
