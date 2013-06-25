package com.yast.lib.user.exceptions;

public class YastLibNotLoggedInException extends RuntimeException {
	public YastLibNotLoggedInException(){
		super("You need to be logged in to use this function");
	}
}
