package com.yast.lib.user;

public class YastRequestProjects extends YastRequest {
	public YastRequestProjects(String username, String hash) {
		super("data.getProjects");
		
		content = "<user>" + username + "</user><hash>" + hash + "</hash>";
	}
}
