package com.revature.models;

public class Transact {
private double amount;
private int accountNumber;
private int accountNumber2;


public Transact() {
	super();
	// TODO Auto-generated constructor stub
}
public Transact(double amount, int accountNumber) {
	super();
	this.amount = amount;
	this.accountNumber = accountNumber;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public int getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(int accountNumber) {
	this.accountNumber = accountNumber;
}

public int getAccountNumber2() {
	return accountNumber2;
}
public void setAccountNumber2(int accountNumber) {
	this.accountNumber2 = accountNumber;
}

}
