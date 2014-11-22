package helpers;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class UrlAnalyzer implements IUrlAnalyzer {

	/**
	 * @param url as string
	 * @return the host part of the url
	 * @throws MalformedURLException
	 */
	public static String extractDomainName(String url) throws MalformedURLException{
		String retDomainName = null;
		URL destinationUrl = new URL(url);
		retDomainName = destinationUrl.getHost();
		return retDomainName;		
	}

	/**
	 * @param url
	 * @return url stripped from first part.<br/>
	 *  examples:
	 *  - www.google.co.uk will return google.co.uk <br/>
	 *  - google.co.uk will return co.uk <br/>
	 *  - co.uk will return uk <br/>
	 *  - uk will return null <br/>
	 */
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

	/**
	 * @param url
	 * @return url stripped from last part.<br/>
	 *  examples:
	 *  - google.co.uk will return google.co <br/>
	 *  - google.co will return google <br/>
	 *  - google will return null <br/>
	 */
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
