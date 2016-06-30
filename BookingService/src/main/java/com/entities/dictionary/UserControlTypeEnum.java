package com.entities.dictionary;

/**
 * Enum with all UserControl.type values
 * 
 * @author Alberto
 *
 */
public enum UserControlTypeEnum {

	LOGIN("LOGIN", "Login to application of "), 
	LOGINERROR("LOGIN", "Fail access with user "), 
	LOADFILE("LOADFILE", "Load file "), 
	DELETEFILE("DELETEFILE", "Delete file "), 
	ASSIGFILE("ASSIGFILE","Assign file to report "),
	LOADXML("LOADXML","Load xml to report "),
	NEWREPORT("NEWREPORT","Create new report "),
	EDITREPORT("EDITREPORT","Edit report "),
	VIEWREPORT("VIEWREPORT","View report "),
	USERNEW("USERNEW","User new "),
	USEREDIT("USERNEW","User edit "),
	DMO("DMO","DMO loader "),
	;

	private String type = null;
	private String desc = null;

	UserControlTypeEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}
	
	public String getDesc() {
		return desc;
	}
}
