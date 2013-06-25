package com.yast.lib.user;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YastResponseFolders extends YastResponse {
	protected ArrayList<YastFolder> folders;
	
	public YastResponseFolders(){
		folders = new ArrayList<YastFolder>();
	}
	
	public ArrayList<YastFolder> getFolders(){
		return folders;
	}

	@Override
	public void processResponse(Element response) {
		NodeList items = response.getElementsByTagName("folder");
		
		for (int i = 0; i < items.getLength(); i++){
			Node item = items.item(i);
			YastFolder folder = new YastFolder(item);
			
			folders.add(folder);
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
	    				if (parser.getName().equals("folder")){
	    					Utilities.i("Found folder");
	    					
	    					YastFolder f = new YastFolder();
	    					f.fromXml(parser);
	    					
	    					folders.add(f);
	    				}
	    				break;
	    			case XmlPullParser.END_TAG:
	    				if (parser.getName().equals("objects")){
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
    		e.printStackTrace();
    	}
	}
	*/

}
