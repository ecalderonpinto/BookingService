package com.entities.utilities.hibernate;

import java.io.Serializable;

public interface InsertableAdapter extends Serializable{

	public InsertAuditor getAuditor();
	public void setAuditor(InsertAuditor _auditor);
}
