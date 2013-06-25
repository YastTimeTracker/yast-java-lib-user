package com.yast.lib.user;

import java.util.ArrayList;

public class YastRequestAdd extends YastRequest {
	public YastRequestAdd(String username, String hash, ArrayList<? extends YastDataObject> objects){
		super("data.add");
		
		this.content = "<user>" + username + "</user><hash>" + hash + "</hash><objects>";
		for (YastDataObject obj : objects){
			this.content += obj.toXml();
		}
		this.content += "</objects>";
	}
}
