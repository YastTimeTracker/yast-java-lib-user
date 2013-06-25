package com.yast.lib.user;

import org.w3c.dom.Node;

public class YastProject extends YastAbstractProject {
	public YastProject(Node node) {
		super(node);
	}
	
	public YastProject(String name){
		this(0,0,name,"#008000",0,0,0);
	}
	
	public YastProject(long internalId, int id, String name, String primaryColor, long internalParentId, int parentId, int privileges){
		super(internalId, id, name, primaryColor, internalParentId, parentId, privileges);
	}

	public String toXml(){
		return toXml("project");
	}

	@Override
	public void updateFromObject(YastDataObject object) {
		if (object instanceof YastProject){
			this.updateFromProject((YastProject)object);
		} else {
			Utilities.e("Unable to update. Object not a YastProject");
		}
	}
	
	/*
	public void fromXml(XmlPullParser parser){
		fromXml(parser, "project");
	}
	*/
}
