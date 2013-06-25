package com.yast.lib.user;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public abstract class YastAbstractProject extends YastDataObject{
	protected String name;
	//private String description;
	protected String primaryColor;
	protected int parentId;
	protected long internalParentId;
	protected int privileges;
	//private int timeCreated;
	//private int creator;
	
	// Getters
	public String getName(){return name;}
	public String getPrimaryColor(){return primaryColor;}
	public int getParentId(){return parentId;}
	public long getInternalParentId(){return internalParentId;}
	public int getPrivileges(){return privileges;}
	
	// Setters
	public void setName(String newName){name = newName;}
	public void setPrimaryColor(String newPrimaryColor){primaryColor = newPrimaryColor;}
	//public void setParentId(int newParentId){parentId = newParentId;}
	//public void setInternalParentId(long newInternalParentId){internalParentId = newInternalParentId;}
	
	
	protected String toXml(String tagName){
		String xml = "<" + tagName + ">"
					+"<id>"+id+"</id>"
					+"<name><![CDATA[" + name + "]]></name>"
					+"<description></description>"
					+"<primaryColor><![CDATA[" + primaryColor + "]]></primaryColor>"
					+"<parentId>" + parentId + "</parentId>"
					+"<privileges>" + privileges + "</privileges>"
					+"</" + tagName + ">";
		return xml;
	}
	
	
	protected void updateFromProject(YastAbstractProject obj){
		this.id = obj.getId();
		this.name = obj.getName();
		this.parentId = obj.getParentId();
		this.primaryColor = obj.getPrimaryColor();
		this.privileges = obj.getPrivileges();
	}
	
	public YastAbstractProject(Node node){
		NodeList properties = node.getChildNodes();
		//Utilities.i("YastAbstarctProject, nodename : " + node.getNodeName());
		//Utilities.i("Prop count: " + properties.getLength());
		
		
		//Utilities.i("tostring: " +node.);
		
		for(int i = 0; i < properties.getLength(); i++){
			Node property = properties.item(i);
			
			String nodeName = property.getNodeName();
			//Utilities.i("Prop type: " +property.getNodeType());
			//Utilities.i("Prop value: " + property.getNodeValue());
			//Utilities.i("Property nodename : " + nodeName);
			//String value = property.getFirstChild().getNodeValue();
			//Utilities.i("PROPERTY VALUE: " + value);
			
			if (nodeName.equals("id")){
				this.id = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("name")){
				Node nameNode = property.getFirstChild();
				if (nameNode == null){
					this.name = "";
				} else {
					this.name = nameNode.getNodeValue();
				}
			} else if (nodeName.equals("primaryColor")){
				Node colorNode = property.getFirstChild();
				if (colorNode == null){
					this.primaryColor = "";
				} else {
					this.primaryColor = colorNode.getNodeValue();
				}
			} else if (nodeName.equals("parentId")){
				this.parentId = Integer.parseInt(property.getFirstChild().getNodeValue());
			} else if (nodeName.equals("privileges")){
				this.privileges = Integer.parseInt(property.getFirstChild().getNodeValue());
			}
		}
	}
	
	public YastAbstractProject(long internalId, int id, String name, String primaryColor, long internalParentId, int parentId, int privileges){
		this.internalId = internalId;
		this.id = id;
		this.name = name;
		this.primaryColor = primaryColor;
		this.internalParentId = internalParentId;
		this.parentId = parentId;
		this.privileges = privileges;
	}
	
	public boolean needsUpdate(YastAbstractProject other){
		// Check name
		if (!other.getName().equals(this.name)){
			return true;
		}
		
		// Check color
		if (!other.getPrimaryColor().equals(this.primaryColor)){
			return true;
		}
		
		// check parent
		if (other.getParentId() != this.parentId){
			return true;
		}
		
		// check privileges
		if (other.getPrivileges() != this.privileges){
			return true;
		}
			
		return false;
	}
	
	public void setParent(YastFolder folder){
		if (folder == null){
			this.parentId = 0;
			this.internalParentId = 0;
		} else {
			this.parentId = folder.getId();
			this.internalParentId = folder.getInternalId();
		}
	}

}
