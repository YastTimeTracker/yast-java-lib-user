package com.yast.lib.user.test;

import java.io.IOException;
import java.util.Properties;


public class Base{

	protected static Properties getProps(){
		
		Properties props = new Properties(); 
	    try {
	        //load a properties file from class path, inside static method
	        props.load(Base.class.getResourceAsStream("/test.properties"));

			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			if(username == null || password == null)
				throw new RuntimeException("Please create src/test/resources/test.properties with username=USER and password=PW");

	        } 
	   catch (Exception ex) {
	        throw new RuntimeException("Please create src/test/resources/test.properties with username=USER and password=PW");
	    }
		return props;
	}

}
