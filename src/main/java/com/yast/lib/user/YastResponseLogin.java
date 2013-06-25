package com.yast.lib.user;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.yast.lib.user.exceptions.YastLibBadResponseException;

public class YastResponseLogin extends YastResponse {
	private String hash;
	
	public String getHash(){
		return hash;
	}

	@Override
	public void processResponse(Element response) throws YastLibBadResponseException {
		NodeList hashItems = response.getElementsByTagName("hash");
		
		if (hashItems.getLength() > 0){
			Node hashNode = hashItems.item(0);
			
			hash = hashNode.getFirstChild().getNodeValue();
		} else {
			throw new YastLibBadResponseException("Hash node not found in login response");
		}
	}

	/*
	@Override
	public void processResponse(XmlPullParser parser) {
		try {	
			boolean done = false;
    		int eventType = parser.getEventType();
    		while(eventType != XmlPullParser.END_DOCUMENT){
    			switch (eventType){
	    			case XmlPullParser.START_TAG:
	    				if (parser.getName().equals("hash")){
	    					this.hash = parser.nextText();
	    				}
	    				break;
	    			case XmlPullParser.END_TAG:
	    				if (parser.getName().equals("hash")){
	    					done = true;
	    				}
	    				break;
    			}
    			if (!done){
    				eventType = parser.next();
    			} else {
    				break;
    			}
    		}
    	} catch(Exception e){
    	
    	}
	}
	*/
}
