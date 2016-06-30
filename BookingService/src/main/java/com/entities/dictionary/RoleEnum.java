package com.entities.dictionary;

/**
 * Enum with all Role.roleName values
 * 
 * @author Alberto
 *
 */
public enum RoleEnum {

	ROLE_USER("ROLE_USER", "User role, normal access."), 
	ROLE_ADMIN("ROLE_ADMIN","Administrator role, access to install or generate schema."), 
	;

	private String name = null;
	private String description = null;

	RoleEnum(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
