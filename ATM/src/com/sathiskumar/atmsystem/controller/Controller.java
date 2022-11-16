package controller;

import model.AccountDetails;
import repository.Database;

import java.util.ArrayList;

public class Controller {
    private Database database = Database.getInstance();
    private ArrayList<AccountDetails> accountDetails = database.getAccountDetails();

    //  Account Number Check
    public boolean accountCheck(int accountNumber) {
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }

    //  Get Balance Amount
    public int getBalanceAmount(int accountNumber) {
        int balance = 0;
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getAccountNumber() == accountNumber) {
                balance = accountDetails.get(i).getBalance();
            }
        }
        return balance;
    }

    //  Pin Number Check
    public boolean pinNumberCheck(int pinNumber) {
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getPinNumber() == pinNumber) {
                return true;
            }
        }
        return false;
    }

    public boolean amountCheck(int accountNumber, int amount) {
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getAccountNumber() == accountNumber) {
                if (amount <= accountDetails.get(i).getBalance()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void withdrawal(int accountNumber, int amount) {
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getAccountNumber() == accountNumber) {
                int balance = accountDetails.get(i).getBalance();
                int totalAmount = balance - amount;
                accountDetails.get(i).setBalance(totalAmount);
            }
        }
    }

    //  Deposit Amount
    public void deposit(int accountNumber, int amount) {
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getAccountNumber() == accountNumber) {
                int balance = accountDetails.get(i).getBalance();
                int totalAmount = balance + amount;
                accountDetails.get(i).setBalance(totalAmount);
            }
        }
    }

    // OTP number
    public int otp() {
        int number = 0;
        long otp = (long) (Math.random() * 100000);
        if (otp > 1000) {
            number = (int) otp;
        } else {
            otp();
        }
        return number;
    }

    //  OTP Check
    public boolean otpCheck(int userNumber, int otp) {
        if (userNumber == otp) {
            return true;
        } else {
            return false;
        }
    }

    //  Set Pin Number
    public void setPinNumber(int pinNumber, int accountNumber) {
        for (int i = 0; i < accountDetails.size(); i++) {
            if (accountDetails.get(i).getAccountNumber() == accountNumber) {
                accountDetails.get(i).setPinNumber(pinNumber);
            }
        }
    }
}
