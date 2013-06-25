package com.yast.lib.user;

import java.util.ArrayList;

public class YastRequestDelete extends YastRequest {

	public YastRequestDelete(String username, String hash, ArrayList<? extends YastDataObject> objects) {
		super("data.delete");
		
		this.content = "<user>" + username + "</user><hash>" + hash + "</hash><objects>";
		for (YastDataObject obj : objects){
			if (obj.getId() > 0){
				if (obj instanceof YastRecord){
					this.content += "<record><id>" + obj.getId() + "</id></record>";
				} else if (obj instanceof YastProject){
					this.content += "<project><id>" + obj.getId() + "</id></project>";
				} else if (obj instanceof YastFolder){
					this.content += "<folder><id>" + obj.getId() + "</id></folder>";
				}
			}
		}
		this.content += "</objects>";
	}

}
