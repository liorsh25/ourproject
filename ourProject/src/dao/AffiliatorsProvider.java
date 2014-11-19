package dao;

import model.affiliateCompany.IAffiliateCompany;
import model.affiliateCompany.VigLinksAffiliateCompany;

public class AffiliatorsProvider {

	private static AffiliatorsProvider instance = new AffiliatorsProvider();
	
	public static AffiliatorsProvider getProvider() {
		return instance;
	}

	// TODO we now support only one, but we will need more...
	// private Map<String,IShoppingSite> affiliators;
	// The one affiliateCompany for now will be viglinks
	private IAffiliateCompany viglinks = new VigLinksAffiliateCompany("vigLinks","http://redirect.viglink.com");
	
	private AffiliatorsProvider() {
		//	IAffiliateCompany groupon = new GrouponAffiliateCompany("groupon","http://t.groupon.com/r?tsToken=IL_AFF_0_202128_214281_0&url=[[CURRENT_URL]]%2F%3FCID%3DIL_AFF_5600_225_5383_1%26nlp%26utm_source%3DGPN%26utm_medium%3Dafl%26utm_campaign%3D202128&wid=http://tormim.com");
		//	IAffiliateCompany cj = new CJAffiliateCompany("cj","http://www.anrdoezrs.net/links");
	}
	
	public IAffiliateCompany getAffiliator(String name) {
		if(name.equalsIgnoreCase("vigLinks")) {
			return viglinks;
		}
		return null;
	}

}
