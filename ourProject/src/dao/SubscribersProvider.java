package dao;

import java.util.HashMap;
import java.util.Map;

import model.ISubscriber;
import model.Subscriber;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SubscribersProvider {

	static final Logger logger = LogManager.getLogger(SubscribersProvider.class);

	// TODO populate this default subscriber with relevant data
	private static final ISubscriber defaultSubscriber = 
			new Subscriber("DEFAULT","BLOG","DEFAULT_SUB","DEFAULT");	
	
	private static SubscribersProvider instance = new SubscribersProvider();
	
	public static SubscribersProvider getProvider() {
		return instance;
	}

	private Map<String, ISubscriber> subscribers;
	
	private SubscribersProvider() {
		subscribers = new HashMap<String,ISubscriber>();
		//The subscriber will be hufshatLeida
		subscribers.put("hufsha001", new Subscriber("Hufshat Leida","BLOG","3a0aad395b02941d447b234383bed775","hufsha001"));		
	}
	
	public ISubscriber getSubscriber(String subcriberKey) {
		ISubscriber subscriber = null;
		if(subcriberKey == null || subcriberKey.isEmpty()) {
			logger.error("subcriberKey not provided, using default subcriberKey, user will not track this redirect");
		}
		else {
			subscriber = subscribers.get(subcriberKey);
			if(subscriber == null) {
				logger.error("subcriber not foind for subcriberKey = " + subcriberKey + " - using default subcriberKey, user will not track this redirect");				
			}
		}
		if(subscriber == null) {
			subscriber = defaultSubscriber;
		}
		return subscriber;
	}


}
