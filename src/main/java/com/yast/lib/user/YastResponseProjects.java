package com.yast.lib.user;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YastResponseProjects extends YastResponse{
	protected ArrayList<YastProject> projects;
	
	public YastResponseProjects(){
		projects = new ArrayList<YastProject>();
	}
	
	public ArrayList<YastProject> getProjects(){
		return projects;
	}

	@Override
	public void processResponse(Element response) {
		NodeList items = response.getElementsByTagName("project");
		
		for (int i = 0; i < items.getLength(); i++){
			Node item = items.item(i);
			YastProject project = new YastProject(item);
			
			projects.add(project);
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
	    				if (parser.getName().equals("project")){
	    					YastProject p = new YastProject();
	    					p.fromXml(parser);
	    					
	    					projects.add(p);
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
    	
    	}
    	
	}
	*/

}
