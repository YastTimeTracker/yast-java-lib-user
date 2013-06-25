package com.yast.lib.user;

import org.w3c.dom.Node;

public class YastFolder extends YastAbstractProject {

	public YastFolder(Node node) {
		super(node);
	}
	
	public YastFolder(String name){
		this(0, 0, name, "#008000", 0, 0, 0);
	}
	
	public YastFolder(long internalId, int id, String name, String primaryColor, long internalParentId, int parentId, int privileges){
		super(internalId, id, name, primaryColor, internalParentId, parentId, privileges);
	}

	public String toXml(){
		return toXml("folder");
	}
	
	@Override
	public void updateFromObject(YastDataObject object) {
		if (object instanceof YastFolder){
			this.updateFromProject((YastFolder)object);
		} else {
			Utilities.e("Unable to update. Object not a YastFolder");
		}
	}
	
	/*
	public void fromXml(XmlPullParser parser){
		fromXml(parser, "folder");
	}
	*/
}
