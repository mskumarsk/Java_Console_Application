package controller;

import model.AccountDatabase;
import model.AdminLogin;
import repository.BankDatabase;
import model.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController extends AdminLogin {
    //  Admin Login Check
    public boolean checkAdminName(String adminName) {
        if (adminName.equals(getAdminName())) {
            return true;
        } else {
            System.out.println("Admin name is wrong...");
            return false;
        }
    }

    public boolean checkAdminPassword(String adminPassword) {
        if (adminPassword.equals(getAdminPassword())) {
            return true;
        } else {
            System.out.println("Incorrect Password");
            return false;
        }
    }

    // User Login Check
    public boolean checkUserLogin(String accountNumber, String pinNumber) throws SQLException {
        BankDatabase bankDatabase = BankDatabase.getInstance();
        ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (accountNumber.equals(accountDatabases.get(i).getUserId())) {
                if (pinNumber.equals(accountDatabases.get(i).getPinNumber())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Set User Pin Number
    public boolean updatePinNumber(String accountNumber, String pinNumber, String phoneNumber) throws SQLException {
        String setPinNumber = "update new_account set pin_number='" + pinNumber + "' where " +
                "user_id='" + accountNumber + "' and phone_no='" + phoneNumber + "'";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(setPinNumber);
        BankDatabase bankDatabase = BankDatabase.getInstance();
        ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (accountNumber.equals(accountDatabases.get(i).getUserId())) {
                if (phoneNumber.equals(accountDatabases.get(i).getUserPhoneNumber())) {
                    accountDatabases.get(i).setPinNumber(pinNumber);
                    return true;
                }
            }
        }
        return false;
    }
}
