package com.yast.lib.user;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.yast.lib.user.exceptions.YastLibBadResponseException;

public class YastResponseUserInfo extends YastResponse {
	private YastUserInfo info;
	
	public YastUserInfo getUserInfo(){
		return info;
	}
	
	@Override
	public void processResponse(Element response) throws YastLibBadResponseException {
		info = new YastUserInfo(response);
	}

}
