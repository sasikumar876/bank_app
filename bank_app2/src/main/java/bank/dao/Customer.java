package bank.dao;

import java.util.Date;

public class Customer {
    private String accountNo; // Changed to int as per your provided class
    private String name;
    private String email;
    private Date dob;
    private String address;
    private String phone;
    private String gender;
    private String accountType;
    private Date joinDate;

    // Getters and setters for all fields
    public String getAccountNo() { return accountNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; } // Adjusted to accept int

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
	public void setAccountNo(long long1) {
		// TODO Auto-generated method stub
		
	}
}
