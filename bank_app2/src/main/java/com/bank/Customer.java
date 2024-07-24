package com.bank;

public class Customer {
    private String accountNo;
    private String name;
    private String password;
    private String email;
    private String dob;
    private String address;
    private String phone;
    private String gender;
    private String accountType;
    private String joinDate;
    private double balance;

    // Constructor
    public Customer(String accountNo, String name, String password, String email, String dob, String address,
                    String phone, String gender, String accountType, String joinDate, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.accountType = accountType;
        this.joinDate = joinDate;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


	public void setMobile(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setMobile1(String string) {
		// TODO Auto-generated method stub
		
	}
}
