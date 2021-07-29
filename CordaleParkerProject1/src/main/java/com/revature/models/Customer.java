package com.revature.models;

import java.sql.Date;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String accountName;
	private String accountPassword;
	private String phoneNumber;
	private String email;
	private Date dateOfBirth;
	private String address;
	private String apartment;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private boolean accountApproved;
	private String approvedBy;
	
	public Customer() {
		super();
		this.accountApproved = false;
	}

	public Customer(int id, String firstName, String lastName, String accountName, String accountPassword,
			String phoneNumber, String email, String dateOfBirth, String address, String apartment, String city,
			String state, String country, String postalCode, boolean accountApproved, String approvedBy) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateOfBirth = Date.valueOf(dateOfBirth);
		this.address = address;
		this.apartment = apartment;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.accountApproved = accountApproved;
		this.approvedBy = approvedBy;
	}
	
	public Customer(Customer c) {
		this.id = c.getId();
		this.firstName = c.getFirstName();
		this.lastName = c.getLastName();
		this.accountName = c.getAccountName();
		this.accountPassword = c.getAccountPassword();
		this.phoneNumber = c.getPhoneNumber();
		this.email = c.getEmail();
		this.dateOfBirth = c.getDateOfBirth();
		this.address = c.getAddress();
		this.apartment = c.getApartment();
		this.city = c.getCity();
		this.state = c.getState();
		this.country = c.getCountry();
		this.postalCode = c.getPostalCode();
		this.accountApproved = c.isAccountApproved();
		this.approvedBy = c.getApprovedBy();
	}


	public void setId(int id) {
		this.id= id;
	}
	public int getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = Date.valueOf(dateOfBirth);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isAccountApproved() {
		return accountApproved;
	}

	public void setAccountApproved(boolean accountApproved) {
		this.accountApproved = accountApproved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Override
	public String toString() {
		return "Customer: " + firstName + " " + lastName + " Id: "+ id + " |date of birth: " + dateOfBirth + "  |Approved: " + accountApproved +"\n";
	}

	public String displayAll() {
		return "Customer: " + firstName + " " + lastName + " Id: "+ id + " |date of birth: " + dateOfBirth + "  |Email: " + email +"\n"
				+ " Phone number: "+ phoneNumber+" |Address: " + address + " |City: " + city +"\n";
	}

	
}
