package com.yast.lib.user.test;

import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.yast.lib.user.Yast;
import com.yast.lib.user.YastDataObject;
import com.yast.lib.user.YastProject;
import com.yast.lib.user.YastRecordWork;
import com.yast.lib.user.exceptions.YastLibApiException;
import com.yast.lib.user.exceptions.YastLibBadResponseException;
import com.yast.lib.user.exceptions.YastLibNotLoggedInException;

public class DataTest extends Base{
	private static Yast yast;


	@BeforeClass
	public static void setUp() throws Exception{
		
		Properties props = getProps();
		
		yast = new Yast();
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		yast = new Yast();
		yast.login(username, password);
	}
	
	@Test
	public void testAddRecordWork() throws YastLibNotLoggedInException, YastLibApiException, YastLibBadResponseException{
		int startTime = (int)(System.currentTimeMillis()/1000 - 3600);
		int endTime = (int)(System.currentTimeMillis()/1000 - 0);
		String comment = "YastJavaLibTest";
		int project = 117768;
		boolean isRunning = false;
		
		YastProject pObj = new YastProject("Dummy project");
		pObj.setId(project);
		
		YastRecordWork record = new YastRecordWork();
		record.setStartTime(startTime);
		record.setEndTime(endTime);
		record.setComment(comment);
		record.setProject(pObj);
		record.setRunning(isRunning);
		
		ArrayList<YastDataObject> adds = new ArrayList<YastDataObject>();
		adds.add(record);
		
		yast.add(adds);
		
		// verify returned values
		assertEquals("Starttime changed", record.getStartTime() , startTime);
		assertEquals("Endtime changed", record.getEndTime(), endTime);
		assertEquals("Comment changed", record.getComment(), comment);
		assertEquals("Project changed", record.getProject(), project);
		assertEquals("Running changed", record.isRunning(), isRunning);
	
		assertTrue("Id not position", record.getId() > 0);
		assertTrue("Created time not set", record.getTimeCreated() > 0);
		assertTrue("Updated time not set", record.getTimeUpdated() > 0);
	}
}
