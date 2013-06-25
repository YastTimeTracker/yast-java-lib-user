package com.yast.lib.user;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YastResponseRecords extends YastResponse {
	private ArrayList<YastRecord> records;
	
	public YastResponseRecords(){
		records = new ArrayList<YastRecord>();
	}
	
	public ArrayList<YastRecord> getRecords(){
		return records;
	}
	
	@Override
	public void processResponse(Element response) {
		NodeList recordItems = response.getElementsByTagName("record");
		
		for(int j = 0; j < recordItems.getLength(); j++){
			Node item = recordItems.item(j);
			
			YastRecord record = YastRecord.createRecord(item);
			if (record != null){
				records.add(record);
			}
		}
	}

}
