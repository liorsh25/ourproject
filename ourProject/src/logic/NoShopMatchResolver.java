package logic;

import model.IShoppingSite;
import model.ISubscriber;

public class NoShopMatchResolver implements IRedirectResolver {

	@Override
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite) {
		// we didn't find any match to any shop, thus we need to redirect to the exact requested url
		//TODO in case of no shopping site try to use viglinks API or other APIs
		return destinationUrl;
	}

}
