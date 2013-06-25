package com.yast.lib.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class YastRecordPhonecall extends YastRecord {
	protected int startTime;
	protected int endTime;
	protected String comment;
	protected boolean running;
	protected String otherPhoneNumber;
	protected boolean outgoing;
	
	// internal field used to map a record to a calllog entry. Not synced to Yast.com
	protected long callLogId;
	public long getCallLogId(){return callLogId;}
	public void setCallLogId(long newValue){callLogId = newValue;} 
	
	
	// Getters
	public int getStartTime(){return startTime;}
	public int getEndTime(){return endTime;}
	public String getComment(){return comment;}
	public boolean isRunning(){return running;}
	public String getOtherPhoneNumber(){return otherPhoneNumber;}
	public boolean isOutgoing(){return outgoing;}
	
	// Setters
	public void setStartTime(int startTime){this.startTime = startTime;}
	public void setStartTime(Date date){this.startTime = (int)(date.getTime()/1000);}
	public void setStartTime(Calendar cal){this.startTime = (int)(cal.getTimeInMillis()/1000);}
	public void setEndTime(int endTime){this.endTime = endTime;}
	public void setEndTime(Date date){this.endTime = (int)(date.getTime()/1000);}
	public void setEndTime(Calendar cal){this.endTime = (int)(cal.getTimeInMillis()/1000);}
	public void setComment(String comment){this.comment = comment;}
	public void setRunning(boolean isRunning){this.running = isRunning;}
	public void setOtherPhoneNumber(String number){this.otherPhoneNumber = number;}
	public void setOutgoing(boolean isOutgoing){this.outgoing = isOutgoing;}
	
	public YastRecordPhonecall(){
		this.typeId = 3;
	}
	
	public YastRecordPhonecall(long internalId, 
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
						  boolean running, 
						  String otherNumber,
						  boolean outgoing,
						  long callLogId){
			super(internalId, id, 3, timeCreated,timeUpdated, internalProjectId, projectId, creator, flags);
			this.startTime = startTime;
			this.endTime = endTime;
			this.comment = comment;
			this.running = running;
			this.otherPhoneNumber = otherNumber;
			this.outgoing = outgoing;
			this.callLogId = callLogId;
	}
	
	
	public YastRecordPhonecall(int id, int timeCreated, int timeUpdated, int project, int creator, int flags, ArrayList<String> variables){
		super(id, 3, timeCreated, timeUpdated, project, creator, flags);
		
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
			case 4:
				otherPhoneNumber = variables.get(i);
				break;
			case 5:
				outgoing = !variables.get(i).equals("0");
			}
		}
	}
	
	@Override
	public String toXml() {
		String res = "<record>" +
					 	"<id>" + id + "</id>" +
					 	"<typeId>" + 3 + "</typeId>" +
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
					 		"<v><![CDATA[" + otherPhoneNumber + "]]></v>"+
					 		"<v>" + (outgoing ? "1" : "0") + "</v>" +
					 	"</variables>" +
					 "</record>";		
					 				
					 	
		return res;
	}
	

	public String toString(){
		return super.toString() + 
			"\n    startTime: " + startTime +
			"\n    endTime  : " + endTime + 
			"\n    comment  : " + comment +
			"\n    running  : " + running +
			"\n    number   : " + otherPhoneNumber +
			"\n    outgoing : " + outgoing;
	}
	@Override
	public void updateFromObject(YastDataObject object) {
		if (object instanceof YastRecordPhonecall){
			YastRecordPhonecall record = (YastRecordPhonecall)object;
			this.updateFromRecord(record);
			
			// Update variables
			this.startTime = record.getStartTime();
			this.endTime = record.getEndTime();
			this.comment = record.getComment();
			this.running = record.isRunning();
			this.otherPhoneNumber = record.getOtherPhoneNumber();
			this.outgoing = record.isOutgoing();
		} else {
			Utilities.e("Unable to update YastRecordPhoneCall, object of wrong type: " + object.getClass().getName());
		}
	}
}
