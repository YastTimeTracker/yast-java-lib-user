package com.yast.lib.user.exceptions;

public class YastLibBadResponseException extends Exception {
	public YastLibBadResponseException(){
		super("Failed to parse xml response");
	}
	
	public YastLibBadResponseException(String message){
		super(message);
	}
	
	public YastLibBadResponseException(String message, Exception cause){
		super(message, cause);
	}
}
