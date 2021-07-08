package com.revature.models;

public class Account {
	private int accountNumber;
	private int customerId;
	private double accountBalance;
	private String accountType;
	private boolean accountApproved;
	private String approvedBy;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int accountNumber, int customerId, double accountBalance, String accountType,
			boolean accountApproved, String approvedBy) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
		this.accountApproved = accountApproved;
		this.approvedBy = approvedBy;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
		return "Account: " + accountNumber + ", customerId= " + customerId + ", accountBalance="
				+ accountBalance + ", accountType=" + accountType + ", accountApproved=" + accountApproved
				+ ", approvedBy=" + approvedBy + "\n";
	}
	
}
