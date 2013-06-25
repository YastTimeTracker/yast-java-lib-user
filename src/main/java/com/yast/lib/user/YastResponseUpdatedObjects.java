package com.yast.lib.user;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YastResponseUpdatedObjects extends YastResponse {
	private ArrayList<YastDataObject> objects;
	
	public YastResponseUpdatedObjects(){
		objects = new ArrayList<YastDataObject>();
	}
	
	public ArrayList<YastDataObject> getObjects(){
		return objects;
	}

	@Override
	public void processResponse(Element response) {
		NodeList objectNodes = response.getElementsByTagName("objects");
		
		if (objectNodes.getLength() > 0){
			NodeList objectsList = objectNodes.item(0).getChildNodes();
			
			for (int i = 0; i < objectsList.getLength(); i++){
				Node objectNode = objectsList.item(i);
				
				String nodeName = objectNode.getNodeName();
				
				if (nodeName.equals("record")){
					YastRecord record = YastRecord.createRecord(objectNode);
					if (record != null){
						objects.add(record);
					} else {
						Utilities.e("Unable to create record");
					}
				} else if (nodeName.equals("project")){
					objects.add(new YastProject(objectNode));
				} else if (nodeName.equals("folder")){
					objects.add(new YastFolder(objectNode));
				}
			}
		}
	}

}
