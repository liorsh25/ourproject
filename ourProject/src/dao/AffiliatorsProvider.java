package dao;

import logic.TemplatePlaceHolderResolver;
import model.affiliateCompany.AffiliateCompany;
import model.affiliateCompany.IAffiliateCompany;

public class AffiliatorsProvider {

	private static AffiliatorsProvider instance = new AffiliatorsProvider();
	
	public static AffiliatorsProvider getProvider() {
		return instance;
	}

	// TODO we now support only one, but we will need more...
	// private Map<String,IAffiliateCompany> affiliators;
	// The one affiliateCompany for now will be viglinks
	private TemplatePlaceHolderResolver templatePlaceHolderResolver = TemplatePlaceHolderResolver.getResolver();
	//private IAffiliateCompany viglinks = new AffiliateCompany("vigLinks","http://redirect.viglink.com?key=9cdda73eac7364f4f34c7e210984893b&cuid="+ TemplatePlaceHolderResolver.SUBSCRIBER_KEY_PLACE_HOLDER +"&out="+ TemplatePlaceHolderResolver.DESTINATION_URL_PLACE_HOLDER,templatePlaceHolderResolver);
	private IAffiliateCompany viglinks = new AffiliateCompany("vigLinks","http://redirect.viglink.com?key="+ TemplatePlaceHolderResolver.SUBSCRIBER_KEY_PLACE_HOLDER +"&out="+ TemplatePlaceHolderResolver.DESTINATION_URL_PLACE_HOLDER,templatePlaceHolderResolver);
	
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
