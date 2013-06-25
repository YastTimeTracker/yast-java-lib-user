package com.yast.lib.user;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YastUserInfo {
	private int id;
	private String name;
	private int timeCreated;
	private boolean subscription;
	
	// Getters
	public int getId(){return id;}
	public String getName(){return name;}
	public int getTimeCreated(){return timeCreated;}
	public boolean hasSubscription(){return subscription;}
	
	public YastUserInfo(Node node){
		NodeList properties = node.getChildNodes();
		for(int i = 0; i < properties.getLength(); i++){
			Node property = properties.item(i);
			
			String nodeName = property.getNodeName();
			if(nodeName.equals("id")){
				id = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if(nodeName.equals("name")){
				name = property.getFirstChild().getNodeValue();
			} else if (nodeName.equals("timeCreated")){
				timeCreated = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("subscription")){
				subscription = property.getFirstChild().getNodeValue().equals("1");
			}
		}
	}
}
