package helpers;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class UrlAnalyzer implements IUrlAnalyzer {

	public static String extractDomainName(String url) throws MalformedURLException{
		String retDomainName = null;
		URL destinationUrl = new URL(url);
		retDomainName = destinationUrl.getHost();
		return retDomainName;		
	}
	
	public static String getUpperDomain(String url) {
		// to avoid complexities, we keep searching even if we reach co.uk
		// we stop only when remaining url has no "."
		// for a more exact approach, see: http://stackoverflow.com/a/14688913/2085626
		int indexOfDot = url.indexOf(".");
		if(indexOfDot < 0) {
			return null;
		}
		else {
			return url.substring(indexOfDot + 1);
		}
	}

	public static String getDomainWithoutTld(String url) {
		int lastIndexOfDot = url.lastIndexOf(".");
		if(lastIndexOfDot < 0) {
			return null;
		}
		else {
			return url.substring(0, lastIndexOfDot);
		}
	}

}
