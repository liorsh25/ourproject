package logic;

import model.IShoppingSite;
import model.ISubscriber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This enum is used for two purposes:
 * (a) as a factory for getting appropriate RedirectResolver according to shop
 *     - this is done using the static method "getRedirectResolver"
 * (b) as a list for all supported Redirect Resolvers
 * @author amirk
 */
public enum RedirectResolver implements IRedirectResolver {

	//--------------------------------------------------------------
	// list of all the supported redirect resolvers
	// to prevent creation of redirect resolvers outside of this enum, the c'tor of these classes is set package friendly and requires to get RedirectResolver.class as its param
	TemplatePlaceHolders(new TemplatePlaceHolderResolver(RedirectResolver.class)),
	NoShopMatch(new NoShopMatchResolver(RedirectResolver.class));
	
	//--------------------------------------------------------------
	
	static final Logger logger = LogManager.getLogger(RedirectResolver.class);

	private IRedirectResolver resolver;
	private RedirectResolver(IRedirectResolver resolver) {
		this.resolver = resolver;
	}

	/**
	 * The enum represents a redirect resolver and as such it needs to be able to build and return a redirect url
	 */
	@Override
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite) {
		return resolver.buildUrl(destinationUrl, subscriber, shoppingSite);
	}
	
	/**
	 * A factory static method for getting appropriate redirect resolver per given shop
	 */
	public static IRedirectResolver getRedirectResolver(IShoppingSite shoppingSite) {
		IRedirectResolver redirectResolver = null;
		if(shoppingSite != null) {
			redirectResolver = shoppingSite.getRedirectResolver();
		}
		if(redirectResolver == null) {
			logger.warn("Using NoShopMatch RedirectResolver");
			redirectResolver = RedirectResolver.NoShopMatch;
		}
		return redirectResolver;
	}

			
}
