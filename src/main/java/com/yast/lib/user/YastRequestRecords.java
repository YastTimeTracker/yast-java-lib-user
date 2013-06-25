package com.yast.lib.user;

import java.util.ArrayList;

public class YastRequestRecords extends YastRequest {
	public YastRequestRecords(String username, String hash){
		this(username, hash, null, null, null, 0, 0);
	}
	
	public YastRequestRecords(String username, String hash, int timeFrom){
		this(username, hash, null, null, null, timeFrom, 0);
	}
	
	public YastRequestRecords(String username, String hash, int timeFrom, int timeTo){
		this(username, hash, null, null, null, timeFrom, timeTo);
	}
	
	public YastRequestRecords(String username, String hash, ArrayList<Integer> typeIds, ArrayList<Integer> ids, ArrayList<Integer> parentIds, int timeFrom, int timeTo){
		super("data.getRecords");
	
		content = "<user>" + username + "</user><hash>" + hash + "</hash>";
		if (typeIds != null){
			int count = typeIds.size();
			if (count > 0){
				content += "<typeId>";
				for (int i = 0; i < count; i++){
					content += typeIds.get(i);
					if (i < (count -1)){
						content += ",";
					}
				}
				content += "</typeId>";
			}
		}
		if (ids != null){
			int count = ids.size();
			if (count > 0){
				content += "<id>";
				for (int i = 0; i < count; i++){
					content += ids.get(i);
					if (i < (count -1)){
						content += ",";
					}
				}
				content += "</id>";
			}
		}
		if (parentIds != null){
			int count = parentIds.size();
			if (count > 0){
				content += "<parentId>";
				for (int i = 0; i < count; i++){
					content += parentIds.get(i);
					if (i < (count -1)){
						content += ",";
					}
				}
				content += "</parentId>";
			}
		}
		if (timeFrom > 0){
			content += "<timeFrom>" + timeFrom + "</timeFrom>";
		}
		if (timeTo > 0){
			content += "<timeTo>" + timeTo + "</timeTo>";
		}
	}
}
