package model;

public class AccountDetails {
    private int accountNumber;
    private String name;
    private int balance;
    private int pinNumber;

    public AccountDetails(int accountNumber, String name, int balance, int pinNumber) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pinNumber = pinNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
}
