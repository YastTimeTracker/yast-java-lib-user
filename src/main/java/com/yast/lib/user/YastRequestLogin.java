package com.yast.lib.user;

public class YastRequestLogin extends YastRequest {

	public YastRequestLogin(String username, String password) {
		super("auth.login");
		
		this.content = "<user>" + username + "</user><password>" + password + "</password>";
	}
}
