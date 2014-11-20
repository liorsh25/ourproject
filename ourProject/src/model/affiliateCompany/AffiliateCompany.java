package model.affiliateCompany;

import logic.IRedirectResolver;
import model.ISubscriber;

public class AffiliateCompany implements IAffiliateCompany {

	private String name = null;
	protected String deepLinkUrl = null;

	private IRedirectResolver redirectResolver = null;

//TODO: maybe enum

	
	public AffiliateCompany(String name, String deepLinkUrl, IRedirectResolver redirectResolver) {
		super();
		this.name = name;
		this.deepLinkUrl = deepLinkUrl;
		this.redirectResolver = redirectResolver;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDeepLinkUrl() {
		return deepLinkUrl;
	}
	
	
	public void setDeepLinkUrl(String deepLinkUrl) {
		this.deepLinkUrl = deepLinkUrl;
	}
	
	
	public IRedirectResolver getRedirectResolver() {
		return redirectResolver;
	}


	public void setRedirectResolver(IRedirectResolver redirectResolver) {
		this.redirectResolver = redirectResolver;
	}


		

}
