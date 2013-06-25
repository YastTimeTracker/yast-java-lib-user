package com.yast.lib.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class YastRecordWork extends YastRecord {
	protected int startTime;
	protected int endTime;
	protected String comment;
	protected boolean running;
	
	// Getters
	public int getStartTime(){return startTime;}
	public int getEndTime(){return endTime;}
	public String getComment(){return comment;}
	public boolean isRunning(){return running;}
	
	// Setters
	public void setStartTime(int startTime){this.startTime = startTime;}
	public void setStartTime(Date date){this.startTime = (int)(date.getTime()/1000);}
	public void setStartTime(Calendar cal){this.startTime = (int)(cal.getTimeInMillis()/1000);}
	public void setEndTime(int endTime){this.endTime = endTime;}
	public void setEndTime(Date date){this.endTime = (int)(date.getTime()/1000);}
	public void setEndTime(Calendar cal){this.endTime = (int)(cal.getTimeInMillis()/1000);}
	public void setComment(String comment){this.comment = comment;}
	public void setRunning(boolean isRunning){this.running = isRunning;}
	
	public YastRecordWork(){
		this.typeId = 1;
	}
	
	public YastRecordWork(long internalId, 
						  int id,  
						  int timeCreated, 
						  int timeUpdated, 
						  long internalProjectId, 
						  int projectId, 
						  int creator, 
						  int flags,
						  int startTime,
						  int endTime, 
						  String comment,
						  boolean running){
		super(internalId, id, 1, timeCreated,timeUpdated, internalProjectId, projectId, creator, flags);
		this.startTime = startTime;
		this.endTime = endTime;
		this.comment = comment;
		this.running = running;
		
	}
	
	public YastRecordWork(int id, int timeCreated, int timeUpdated, int project, int creator, int flags, ArrayList<String> variables){
		super(id, 1, timeCreated, timeUpdated, project, creator, flags);
		
		for (int i = 0; i < variables.size(); i++){
			switch(i){
			case 0:
				startTime = Integer.parseInt(variables.get(i));
				break;
			case 1:
				endTime = Integer.parseInt(variables.get(i));
				break;
			case 2:
				comment = variables.get(i);
				break;
			case 3:
				running = !variables.get(i).equals("0");
				break;
			}
		}
	}
	
	@Override
	public String toXml() {
		String res = "<record>" +
					 	"<id>" + id + "</id>" +
					 	"<typeId>" + 1 + "</typeId>" +
					 	"<timeCreated>" + timeCreated + "</timeCreated>" +
					 	"<timeUpdated>" + timeUpdated + "</timeUpdated>" +
					 	"<project>" + projectId + "</project>" +
					 	"<creator>" + creator + "</creator>" +
					 	"<flags>" + flags + "</flags>" +
					 	"<variables>" +
					 		"<v>" + startTime + "</v>" +
					 		"<v>" + endTime + "</v>" +
					 		"<v><![CDATA[" + comment + "]]></v>"+
					 		"<v>" + (running ? "1" : "0") + "</v>" +
					 	"</variables>" +
					 "</record>";		
					 				
					 	
		return res;
	}
	
	public String toString(){
		return super.toString() + 
			"\n    startTime: " + startTime +
			"\n    endTime  : " + endTime + 
			"\n    comment  : " + comment +
			"\n    running  : " + running;
	}
	

	/*
	 * protected int startTime;
	protected int endTime;
	protected String comment;
	protected boolean running;
	 */
	public boolean needsUpdate(YastRecordWork other){
		// Check projectId for the record
		if (other.getProject() != this.projectId){
			return true;
		}
		// Check start time
		if (other.getStartTime() != this.startTime){
			return true;
		}
		// check end time
		if (other.getEndTime() != this.endTime){
			return true;
		}
		// check comment
		if (!other.getComment().equals(this.comment)){
			return true;
		}
		// check running
		Boolean b = other.isRunning();
		if (!b.equals(this.running)){
			return true;
		}
		return false;
	}

	@Override
	public void updateFromObject(YastDataObject object) {
		if (object instanceof YastRecordWork){
			YastRecordWork record = (YastRecordWork)object;
			this.updateFromRecord(record);
			
			// Update variables
			this.startTime = record.getStartTime();
			this.endTime = record.getEndTime();
			this.comment = record.getComment();
			this.running = record.isRunning();
		} else {
			Utilities.e("Unable to update YastRecordWork, object is of wrong type");
		}
		
	}
	
}
