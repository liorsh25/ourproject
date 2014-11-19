package logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.AffiliatorsProvider;
import servlets.RedirectServlet;
import model.IShoppingSite;
import model.ISubscriber;
import model.affiliateCompany.VigLinksAffiliateCompany;

public class NoShopMatchResolver implements IRedirectResolver {

	static final Logger logger = LogManager.getLogger(NoShopMatchResolver.class);
	VigLinksAffiliateCompany viglinks = (VigLinksAffiliateCompany) AffiliatorsProvider.getProvider().getAffiliator("viglinks");
	
	@Override
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite) {
		String retUrl = destinationUrl;
		// we didn't find any match to any shop, thus we need to redirect to the exact requested url
		logger.warn("we didn't find any match to any shop, thus we will go viglinks redirect url anyhow");
		retUrl = viglinks.buildUrl(destinationUrl, subscriber, shoppingSite);
		return retUrl;
	}

}
