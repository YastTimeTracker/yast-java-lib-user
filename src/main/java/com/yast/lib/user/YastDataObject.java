package com.yast.lib.user;

public abstract class YastDataObject {
	protected long internalId; // Internal id, can be used to keep track of objects not already synced with Yast.com
	protected int id; // Yast service id
	protected int dirty;
	
	// Getters
	public long getInternalId(){return internalId;}
	public int getId(){return id;}
	public int getDirty(){return dirty;}
	
	//Setters
	public void setInternalId(long newInternalId){
		if (internalId == 0){
		internalId = newInternalId;
		} else {
			Utilities.w("Trying to update internalId when it is already set");
		}
	}
	public void setId(int newId){
		if (id == 0){
			id = newId;
		} else {
			Utilities.w("Trying to update id when it is already set");
		}
	}
	
	public void setDirty(int value){
		dirty = value;
	}
	
	public YastDataObject(){};
	public YastDataObject(long internalId, int id){
		this.internalId = internalId;
		this.id = id;
	}
	
	
	public abstract void updateFromObject(YastDataObject object);
	
	public abstract String toXml();
	
	//public abstract void fromXml(XmlPullParser parser);
}
