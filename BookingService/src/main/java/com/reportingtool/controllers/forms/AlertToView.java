package com.reportingtool.controllers.forms;

/**
 * 
 * @author Esteban Calderon
 *
 */
public class AlertToView {

	private boolean error;
	private String message;
	
	public AlertToView(){
		
	}
	
	public AlertToView(boolean error, String message){
		this.error = error;
		this.message = message;
	}
	
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
