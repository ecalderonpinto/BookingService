package com.entities.utilities.hibernate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable 
public class VersionAuditor  implements Serializable,Cloneable{

	private static final long serialVersionUID = 6399416412803183404L;
	
	
	private long refId=0l;
	private String creationUser=null;
	private Date creationDate=null;
	private boolean deleted=false;
	
	
	public VersionAuditor(){
		this("DEFAULT",Calendar.getInstance().getTime());
	} 
	public VersionAuditor(String _user){
		this(_user,Calendar.getInstance().getTime());
	}
	public VersionAuditor(VersionAuditor _auditor){
		this(_auditor.creationUser,_auditor.creationDate,_auditor.deleted);
	}
	public VersionAuditor(String _user,Date _date){
		this(_user,_date,false);
	}
	public VersionAuditor(String _user,Date _date,boolean _deleted){
		super();
		this.creationUser=_user;
		this.setCreationDate(_date);
		this.deleted=_deleted;
	}

	@Column(name="REF_ID")
	public long getRefId() {
		return refId;
	}
	public void setRefId(long id) {
		this.refId = id;
	}
	
	@Column(name="CREATE_ID", length=128, nullable=false)
	public String getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TMS", nullable=false)
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

	@Column(name="IS_DELETED", nullable=false)
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}