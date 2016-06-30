package com.reportingtool.controllers.forms;

import com.entities.entity.usermanager.User;

public class UserChangePassForm {

	private String oldPass;
	private String newPass;
	private String newPass2;
	private String userId;
	private User user;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNewPass2() {
		return newPass2;
	}

	public void setNewPass2(String newPass2) {
		this.newPass2 = newPass2;
	}

	
	
}
