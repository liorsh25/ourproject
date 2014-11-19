package helpers;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class UrlAnalyzer implements IUrlAnalyzer {

	private String urlStringToAnalayz = null;
	
	
	public UrlAnalyzer(String urlStringToAnalayz) {
		super();
		this.urlStringToAnalayz = urlStringToAnalayz;
	}

	public  static String extractDomainName(String url) throws MalformedURLException{
		String retDomainName = null;
		URL destinationUrl = new URL(url);
		retDomainName = destinationUrl.getHost();
		// TODO need to allow www. in first round of search
		// it will get off if url not found, on our next iteration on getUpperDomain
		retDomainName = retDomainName.replaceFirst("www.", "");//remove www is any
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

}
