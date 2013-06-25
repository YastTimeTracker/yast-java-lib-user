package com.yast.lib.user.exceptions;

public class YastLibApiException extends Exception {
	private int status;
	
	public YastLibApiException(int status){
		super("Yast API returned non-success status: " + status);
		this.status = status;
	}
	
	public int getStatus(){
		return status;
	}
}
