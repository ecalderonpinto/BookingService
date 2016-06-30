package com.entities.utilities.hibernate;

import java.io.Serializable;

public interface VersionableAdapter extends Serializable,Cloneable{

	public long getId();
	public void setId(long _id);
	public int getVersion();
	public void setVersion(int version);
	public VersionAuditor getVersionAuditor();
	public void setVersionAuditor(VersionAuditor _auditor);
	public Object clone() throws CloneNotSupportedException;
}
