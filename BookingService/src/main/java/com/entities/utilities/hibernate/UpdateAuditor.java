package com.entities.utilities.hibernate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable 
public class UpdateAuditor implements Serializable,Cloneable{

	private static final long serialVersionUID = -959001195421314836L;
	
	
	private String creationUser=null;
	private Date creationDate=null;
	private String updateUser;
	private Date  updateDate;
	private boolean deleted=false;
	
	
	public UpdateAuditor(){
		this("DEFAULT",Calendar.getInstance().getTime());
	}
	public UpdateAuditor(String _user){
		this(_user,Calendar.getInstance().getTime());
	}
	public UpdateAuditor(String _user,Date _date){
		this(_user,_date,_user,_date,false);
	}
	public UpdateAuditor(UpdateAuditor _auditor){
		this(_auditor.creationUser,_auditor.creationDate,_auditor.updateUser,_auditor.updateDate,_auditor.deleted);
	}
	public UpdateAuditor(String _insertUser,Date _insertDate,String _updateUser,Date _updateDate,boolean _deleted){
		super();
		this.creationUser=_insertUser;
		this.setCreationDate(_insertDate);
		this.updateUser=_updateUser;
		this.setUpdateDate(_updateDate);
		this.deleted=_deleted;
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
	
	@Column(name="UPDUSER", length=128, nullable=false)
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDDATE", nullable=false)
	public Date getUpdateDate() {
		
		Date reply=null;
		
		if(updateDate!=null){
			reply=new Date(updateDate.getTime());
		}
		
		return reply;
	}
	public void setUpdateDate(final Date updateDate) {
		
		if(updateDate!=null){
			this.updateDate=updateDate;
		}else{
			this.updateDate=null;
		}
	}

	@Column(name="ISDELETED", nullable=false)
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
	
	@Override
	public String toString() {
		
		StringBuilder reply=new StringBuilder(512);
		
		reply.append("\ncreationUser=[");
		reply.append(this.creationUser);
		reply.append("]\ncreationDate=[");
		reply.append(this.creationDate);
		reply.append("]\nupdateUser=[");
		reply.append(this.updateUser);
		reply.append("]\nupdateDate=[");
		reply.append(this.updateDate);
		reply.append("]\ndeleted=[");
		reply.append(this.deleted);
		reply.append("]");
		
		return reply.toString();
	}
}