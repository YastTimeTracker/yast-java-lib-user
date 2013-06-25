package com.yast.lib.user.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;


import org.junit.BeforeClass;
import org.junit.Test;

import com.yast.lib.user.Yast;
import com.yast.lib.user.exceptions.YastLibApiException;
import com.yast.lib.user.exceptions.YastLibBadResponseException;

public class AuthTest extends Base{
	private static Yast yast;
	private static String username;
	private static String legalPassword;
	private static String illegalPassword;
	

	@BeforeClass
	public static void setUp(){
		
		Properties props = getProps();
		
		yast = new Yast();
		username = props.getProperty("username");
		legalPassword = props.getProperty("password");
		illegalPassword = "illegalpasswordasdf";
		
	}
	
	@Test
	public void testHashValue1() throws YastLibApiException, YastLibBadResponseException{
		yast.login(username, legalPassword);
		
		assertNotNull(yast.getHash());
	}

	@Test
	public void testHashValue2() throws YastLibApiException, YastLibBadResponseException{
		yast.login(username, legalPassword);
		
		assertTrue(!yast.getHash().equals(""));
	}

	@Test
	public void testReturnValue2() throws YastLibApiException, YastLibBadResponseException{
		try{
			yast.login(username, illegalPassword);			
		} catch(YastLibApiException e){
			assertEquals(e.getStatus(), 5);
		}
	}
}
