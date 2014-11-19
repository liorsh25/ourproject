package model.affiliateCompany;

import logic.IRedirectResolver;

public interface IAffiliateCompany {

	// this method is replaced with the Resolver approach
	// TODO delete
//	public abstract String calcAffiliatedUrl(String subscriberId,String destinationUrl);

	// TODO: add here abstruct API methods (get affiliate data by
	// dates/revenue...

	public String getName();

	public void setName(String name);

	public String getDeepLinkUrl();

	public void setDeepLinkUrl(String deepLinkUrl);

	public IRedirectResolver getRedirectResolver();

}