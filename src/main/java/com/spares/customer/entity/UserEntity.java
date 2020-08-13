package com.spares.customer.entity;

import javax.persistence.*;

@Entity(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer userId;
	@Column(name="user_name")
	private String userName;
	@Column(name="company_name")
	private String companyName;
	@Column(name="contact_no")
	private String contact_no;
	@Column(name="address")
	private String address;
	@Column(name="state")
	private String state;
	@Column(name="country")
	private String country;
	@Column(name="dob")
	private String dob;
	@Column(name="user_role")
	private String userRole;
	@Column(name="email_id")
	private String email_id;
	@Column(name="password")
	private String password;
	@Column(name="active")
	private boolean active;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public UserEntity(Integer userId, String userName, String companyName, String contactNumber, String address,
                      String state, String country, String dateOfBirth, String userRole, String emailAddress, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.companyName = companyName;
		this.contact_no = contactNumber;
		this.address = address;
		this.state = state;
		this.country = country;
		this.dob = dateOfBirth;
		this.userRole = userRole;
		this.email_id = emailAddress;
		this.password = password;
	}
	public UserEntity() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactNumber() {
		return contact_no;
	}
	public void setContactNumber(String contactNumber) {
		this.contact_no = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDateOfBirth() {
		return dob;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dob = dateOfBirth;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getEmailAddress() {
		return email_id;
	}
	public void setEmailAddress(String emailAddress) {
		this.email_id = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
