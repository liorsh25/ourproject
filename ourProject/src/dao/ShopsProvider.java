package dao;

import helpers.UrlAnalyzer;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.IShoppingSite;
import model.ShoppingSite;
import model.affiliateCompany.IAffiliateCompany;

public class ShopsProvider {
	
	static final Logger logger = LogManager.getLogger(ShopsProvider.class);

	private static ShopsProvider instance = new ShopsProvider();
	
	public static ShopsProvider getProvider() {
		return instance;
	}

	private Map<String,IShoppingSite> shoppingSites;
	
	private ShopsProvider() {
		// create the shops list
		shoppingSites = new HashMap<String,IShoppingSite>();
		
		// populate the shops
		IAffiliateCompany viglinks = AffiliatorsProvider.getProvider().getAffiliator("viglinks");
		shoppingSites.put("www.nextdirect.com", new ShoppingSite("Next Direct",".nextdirect.com","Next direct description","next.gif",viglinks,2,2));//in viglinks it written 8% (-25%)
		shoppingSites.put("il.nextdirect.com", new ShoppingSite("Next Direct",".nextdirect.com","Next direct description","next.gif",viglinks,2,2));//in viglinks it written 8% (-25%)
		shoppingSites.put("nextdirect.com", new ShoppingSite("Next Direct",".nextdirect.com","Next direct description","next.gif",viglinks,2,2));//in viglinks it written 8% (-25%)
		
		shoppingSites.put("alexandalexa.com", new ShoppingSite("alexandalexa",".alexandalexa.com","alexandalexa description","alexandalexa.gif",viglinks,2,2));//in viglinks it written 8% (-25%)
		shoppingSites.put("gap.com", new ShoppingSite("gap",".gap.com","gap description","gap.gif",viglinks,2,2));//in viglinks it written 8% (-25%)
		shoppingSites.put("ebay.com", new ShoppingSite("ebay",".ebay.com","ebayt description","ebay.gif",viglinks,2,2));//in viglinks it written 8% (-25%)
				
		// for testing
		shoppingSites.put("google.com", new ShoppingSite("Google com",".google.com","...description","google.gif",null,2,2));//in viglinks it written 8% (-25%)
		shoppingSites.put("google.co.il", new ShoppingSite("Google co il",".google.co.il","...description","google.gif",null,2,2));//in viglinks it written 8% (-25%)
		shoppingSites.put("google", new ShoppingSite("Google com",".google.com","...description","google.gif",null,2,2));//in viglinks it written 8% (-25%)
		
		shoppingSites.put("aliexpress.com", new ShoppingSite("Ali Express",".aliexpress.com","aliexpress description","ali.gif",viglinks,1,1));

	}

	public IShoppingSite getShoppingSite(String url) {
		IShoppingSite shop = null;
		String extractedDomainName = null;
		String manipulatedUrl = null;
		try {
			extractedDomainName = UrlAnalyzer.extractDomainName(url);
			logger.debug("extractedDomainName="+extractedDomainName);
		} catch (MalformedURLException e) {
			logger.error("MalformedURL: " + url + " - cannot match shopping site");
			return null;
		}
		
		// try to fetch by the exact full url
		shop = shoppingSites.get(extractedDomainName);
		
		// if not found - try to remove prefix subdomains
		// this is in order to allow shop mapped domain to be "il.someshop" or "fashion.someshop"
		manipulatedUrl = extractedDomainName;
		while(shop == null && (manipulatedUrl = UrlAnalyzer.getUpperDomain(manipulatedUrl)) != null) {
			logger.debug("manipulatedUrl="+manipulatedUrl);
			shop = shoppingSites.get(manipulatedUrl);			
		}

		// if still not found - remove www if exists and try to remove suffix Top Level Domain part
		// this is in order to allow shop mapped domain to be "google.co.il" or just "google"
		manipulatedUrl = extractedDomainName.replace("www.", "");
		while(shop == null && (manipulatedUrl = UrlAnalyzer.getDomainWithoutTld(manipulatedUrl)) != null) {
			logger.debug("manipulatedUrl="+manipulatedUrl);
			shop = shoppingSites.get(manipulatedUrl);			
		}

		if(shop == null) {
			logger.warn("cannot find matching shop for url: " + url);
		}
		
		
		return shop;

	}

	
}

// old havascript implementation
/*
 * var next_obj ={
		"name":  {DF:"next"},
		"siteDomain": {DF:".nextdirect.com"},
        "urlToGo" : {DF:"http://www.nextdirect.com",IL:"http://il.nextdirect.com/iw/"},//show this as the url, and build the event when click
        "imageUrl":  "images/next.gif",
        "percentage":  {DF:"2%"},
        "goDirectly": true, //true=>goToSite OR false=>goToSiteInLocation
        "deepAffiliateLink": "http://redirect.viglink.com?key=10a309a920033f8b2c751717059df529&u=[[CURRENT_URL]]",
		"itemURLAsParam": true,
		"countries": SHOW_IN_ALL_COUNTRIES
	};
	var ali_obj = {
"name":  {DF:"aliexpress" , IL:"aliexpress"},
"siteDomain": {DF:".aliexpress.com" , IL:".aliexpress.com"},
"urlToGo" : {DF:"http://www.aliexpress.com" , IL:"http://www.aliexpress.com"},//show this as the url, and build the event when click
"imageUrl":  "images/ali.jpg",
"percentage":  {DF:"0.5%" , IL:"0.5%"},
"goDirectly": true, //true=>goToSite OR false=>goToSiteInLocation
"deepAffiliateLink": "http://www.anrdoezrs.net/links/7567644/type/dlg/[[CURRENT_URL]]",
"itemURLAsParam": false,
"countries": SHOW_IN_ALL_COUNTRIES
};
var groupon_obj ={
"name":  {DF:"groupon" , IL:"groupon",NL:"groupon.nl"},
"siteDomain": {DF:".groupon.co.il" , IL:".groupon.co.il"},
"urlToGo" : {DF:"http://shopping.groupon.co.il/" , IL:"http://shopping.groupon.co.il/",NL:"http://www.groupon.nl"},//show this as the url, and build the event when click
"imageUrl":  "images/groupon.jpg",
"percentage":  {DF:"2%" , IL:"2%"},
"goDirectly": true, //true=>goToSite OR false=>goToSiteInLocation
"deepAffiliateLink": "http://t.groupon.com/r?tsToken=IL_AFF_0_202128_214281_0&url=[[CURRENT_URL]]%2F%3FCID%3DIL_AFF_5600_225_5383_1%26nlp%26utm_source%3DGPN%26utm_medium%3Dafl%26utm_campaign%3D202128&wid=http://tormim.com",
"itemURLAsParam": true,
"countries": SHOW_IN_ALL_COUNTRIES
};
*/
