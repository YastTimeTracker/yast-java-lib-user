package com.yast.lib.user;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class YastRecord extends YastDataObject {
	protected int typeId;
	protected int timeCreated;
	protected int timeUpdated;
	protected int projectId;
	protected long internalProjectId;
	protected int creator;
	protected int flags;
	
	public YastRecord(){
		
	}
	
	public YastRecord(long internalId, int id, int typeId, int timeCreated, int timeUpdated, long internalProjectId, int projectId, int creator, int flags){
		super(internalId, id);
		
		this.timeCreated = timeCreated;
		this.timeUpdated = timeUpdated;
		this.internalProjectId = internalProjectId;
		this.projectId = projectId;
		this.creator = creator;
		this.flags = flags;
	}
	
	public YastRecord(int id, int typeId, int timeCreated, int timeUpdated, int project, int creator, int flags){
		this.id = id;
		this.typeId = typeId;
		this.timeCreated = timeCreated;
		this.timeUpdated = timeUpdated;
		this.projectId = project;
		this.creator = creator;
		this.flags = flags;
	}
	
	// Getters
	public int getTypeId(){return typeId;}
	public int getTimeCreated(){return timeCreated;}
	public int getTimeUpdated(){return timeUpdated;}
	public int getProject(){return projectId;}
	public int getCreator(){return creator;}
	public int getFlags(){return flags;}
	
	// Setters
	//public void setProject(int project){this.projectId = project;}
	public void setFlags(int flags){this.flags = flags;}
	
	
	protected void updateFromRecord(YastRecord record){
		this.id = record.getId();
		this.timeCreated = record.getTimeCreated();
		this.timeUpdated = record.getTimeUpdated();
		this.projectId = record.getProject();
		this.creator = record.getCreator();
		this.flags = record.getFlags();
	}
	
	/*
	@Override
	public String toXml() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	public static YastRecord createRecord(Node item){
		int id = 0;
		int typeId = 0;
		int timeCreated = 0;
		int timeUpdated = 0;
		int project = 0;
		int creator = 0;
		int flags = 0;
		ArrayList<String> variables = new ArrayList<String>(6);
		
		NodeList properties = item.getChildNodes();
		
		for(int i = 0; i < properties.getLength(); i++){
			Node property = properties.item(i);
			
			String nodeName = property.getNodeName();
			
			if (nodeName.equals("id")){
				id = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("typeId")){
				typeId = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("timeCreated")){
				timeCreated = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("timeUpdated")){
				timeUpdated = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("project")){
				project = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("creator")){
				creator = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("flags")){
				flags = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("variables")){
				variables.clear();
				
				NodeList variablesList = property.getChildNodes();
				for (int k = 0; k < variablesList.getLength(); k++){
					Node var = variablesList.item(k);
					if (var.getNodeName().equals("v")){
						
						Node firstChild = var.getFirstChild();
						if (firstChild != null){
							String value = firstChild.getNodeValue();
						
							variables.add(value);
						} else {
							// Empty value
							variables.add("");
						}
					}
				}
			}
		}
		
		// Decide if it is a phone or work record
		if (typeId == 3){
			return new YastRecordPhonecall(id, timeCreated, timeUpdated, project, creator, flags, variables);
		} else if (typeId == 1){
			return new YastRecordWork(id, timeCreated, timeUpdated, project, creator, flags, variables);
		} else {
			Utilities.w("Records typeId was not recogniced, is there any new types defined?");
		}
		return null;
	}
	
	public String toString(){
		return "  id         : " + id +
			   "\n  typeId     : " + typeId +
			   "\n  timeCreated: " + timeCreated +
			   "\n  timeUpdated: " + timeUpdated +
			   "\n  project    : " + projectId +
			   "\n  creator    : " + creator +
			   "\n  flags      : " + flags;
	}
	
	public void setProject(YastProject parent){
		this.projectId = parent.getId();
		this.internalProjectId = parent.getInternalId();
	}
}
