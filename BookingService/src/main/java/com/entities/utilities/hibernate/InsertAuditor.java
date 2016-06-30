package com.entities.utilities.hibernate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable 
public class InsertAuditor implements Serializable,Cloneable{

	private static final long serialVersionUID = 6506086362739263723L;
	
	
	private String creationUser=null;
	private Date creationDate=null;
	
	
	public InsertAuditor(){
		this("DEFAULT",Calendar.getInstance().getTime());
	}
	public InsertAuditor(String _user){
		this(_user,Calendar.getInstance().getTime());
	}
	public InsertAuditor(InsertAuditor _auditor){
		this(_auditor.creationUser,_auditor.creationDate);
	}
	public InsertAuditor(String _user,Date _date){
		this.creationUser=_user;
		this.setCreationDate(_date);
	}

	
	@Column(name="CRTUSER", length=128, nullable=false)
	public String getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRTDATE", nullable=false)
	public Date getCreationDate(){
		Date reply=null;
		
		if(creationDate!=null){
			reply=new Date(creationDate.getTime());
		}
		
		return reply;
	}
	public void setCreationDate(final Date creationDate){
		
		if(creationDate!=null){
			this.creationDate=creationDate;
		}else{
			this.creationDate=null;
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}