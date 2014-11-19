package logic;

import model.IShoppingSite;
import model.ISubscriber;

public interface IRedirectResolver {
	
	public String buildUrl(String destinationUrl, ISubscriber subscriber, IShoppingSite shoppingSite);

}
