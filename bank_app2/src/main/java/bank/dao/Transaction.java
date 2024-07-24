package bank.dao;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private String accountNo;
    private Date datetime;
    private String transactionType;
    private double amount;

    // Constructors
    public Transaction() {
    }

    public Transaction(int transactionID, String accountNo, Date datetime, String transactionType, double amount) {
        this.transactionID = transactionID;
        this.accountNo = accountNo;
        this.datetime = datetime;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    // Getters and Setters
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
