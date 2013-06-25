package com.yast.lib.user;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public abstract class YastRequest {
	private static int reqCounter = 0;

	private final int reqId;
	private final String method;
	protected String content;

	protected boolean userRequired = true;


	public YastRequest(final String method){
		this.reqId = ++reqCounter;
		this.method = method;
	}

	public boolean isValid(){
		// TODO: implement
		return true;
	}

	public boolean isUserRequired(){
		return userRequired;
	}

	public String getMethod(){
		return method;
	}

	public int getId(){
		return reqId;
	}

	public UrlEncodedFormEntity getEntity() {
		String xml = "<request req=\"" + method + "\">" + content + "</request>";

		//Utilities.i("REQUEST: " + xml);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("request", xml));

		UrlEncodedFormEntity out = null;
		try {
			out =  new UrlEncodedFormEntity(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

}
