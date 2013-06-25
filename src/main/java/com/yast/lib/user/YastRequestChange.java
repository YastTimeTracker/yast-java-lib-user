package com.yast.lib.user;

import java.util.ArrayList;

public class YastRequestChange extends YastRequest {
	public YastRequestChange(String username, String hash, ArrayList<? extends YastDataObject> objects){
		super("data.change");
		
		this.content = "<user>" + username + "</user><hash>" + hash + "</hash><objects>";
		for (YastDataObject obj : objects){
			this.content += obj.toXml();
		}
		this.content += "</objects>";
	}
}
