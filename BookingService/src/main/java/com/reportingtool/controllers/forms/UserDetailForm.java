package com.reportingtool.controllers.forms;

import com.entities.entity.usermanager.User;

public class UserDetailForm {

	private String selectUserRol;
	private String selectEnabled;
	private String selectCompany;
	private String userId;
	private Boolean isNew;
	private User user;

	public String getSelectUserRol() {
		return selectUserRol;
	}

	public void setSelectUserRol(String selectUserRol) {
		this.selectUserRol = selectUserRol;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSelectEnabled() {
		return selectEnabled;
	}

	public void setSelectEnabled(String selectEnabled) {
		this.selectEnabled = selectEnabled;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSelectCompany() {
		return selectCompany;
	}

	public void setSelectCompany(String selectCompany) {
		this.selectCompany = selectCompany;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

}
