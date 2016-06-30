/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package com.entities.entity.usermanager;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.entities.entity.reportingtool.Company;

/**
 * This class represents a JPA entity which is used to store user details.
 * 
 * @author Roshan Amadoru
 **/
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	/**
	 * Randomly generated serialVersionUID
	 **/
	private static final long serialVersionUID = -6960838612205920533L;

	/**
	 * The unique username
	 **/
	@Id
	@Column(name = "USERNAME", nullable = false)
	private String username;

	/**
	 * Hashed password of the user
	 **/
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	/**
	 * First name of the user
	 **/
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	/**
	 * Last name of teh user
	 **/
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = true)
	private String userMail;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled = true;

	@Column(name = "LAST_LOGIN_TMS", nullable = true)
	private Date lastLoginTms;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "COMPANY_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_USER_COMPANY"))
	@JoinColumn(name = "COMPANY_ID", nullable = true)
	private Company company;
	
	public User () {
	}
	
	public User (String username, String firstName, String lastName, String userMail, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userMail = userMail;
		this.password = password;
		this.enabled = true;
		Calendar today = Calendar.getInstance();
		this.lastLoginTms = today.getTime();
	}

	/**
	 * The collection of roles associated with the user
	 **/
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "T_USER_ROLE", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "ROLENAME"))
	private Collection<Role> roles;

	/**
	 * Reutrns the username of the user
	 * 
	 * @return username of the user
	 **/
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user
	 * 
	 * @param username
	 *            username of the user
	 **/
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Reutrns the password of the user
	 * 
	 * @return password of the user
	 **/
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user
	 * 
	 * @param password
	 *            password of the user
	 **/
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Reutrns the first name of the user
	 * 
	 * @return first name of the user
	 **/
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user
	 * 
	 * @param firstName
	 *            first name of the user
	 **/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Reutrns the last name of the user
	 * 
	 * @return last name of the user
	 **/
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user
	 * 
	 * @param lastName
	 *            last name of the user
	 **/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastLoginTms() {
		return lastLoginTms;
	}

	public void setLastLoginTms(Date lastLoginTms) {
		this.lastLoginTms = lastLoginTms;
	}
	
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Returns the collection of roles associated to this user
	 * 
	 * @returns the collection of roles
	 **/
	public Collection<Role> getRoles() {
		return this.roles;
	}

	/**
	 * Sets the collection of roles associated to this user
	 * 
	 * @param roles
	 *            Collection of roles
	 **/
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}