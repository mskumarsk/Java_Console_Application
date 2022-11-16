package controller;

import model.AccountDatabase;
import repository.BankDatabase;
import model.DatabaseConnection;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AccountController {
    // Create Account Controller
    private BankDatabase bankDatabase = BankDatabase.getInstance();

    public void createNewAccount(String userName, String userDateOfBirth, String userPhoneNumber, String userGender,
                                 String userCountry, String userState, String userAadharNumber, String pinNumber,
                                 int accountCount) throws SQLException {
        String status = "ACTIVE";
        String insertUserDetails = "insert into new_account(user_id,pin_number,user_name,date_of_birth,phone_no," +
                "gender,country,state,aadhar,account_status) values('" + accountCount + "','" + pinNumber + "'," +
                "'" + userName + "','" + userDateOfBirth + "','" + userPhoneNumber + "','" + userGender + "'," +
                "'" + userCountry + "','" + userState + "','" + userAadharNumber + "','" + status + "');";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(insertUserDetails);
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.toString();
        ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
        accountDatabases.add(new AccountDatabase());
        accountDatabases.get(accountDatabases.size() - 1).setUserId(String.valueOf(accountCount));
        accountDatabases.get(accountDatabases.size() - 1).setPinNumber(pinNumber);
        accountDatabases.get(accountDatabases.size() - 1).setUserName(userName);
        accountDatabases.get(accountDatabases.size() - 1).setUserDateOfBirth(userDateOfBirth);
        accountDatabases.get(accountDatabases.size() - 1).setUserPhoneNumber(userPhoneNumber);
        accountDatabases.get(accountDatabases.size() - 1).setUserGender(userGender);
        accountDatabases.get(accountDatabases.size() - 1).setUserCountry(userCountry);
        accountDatabases.get(accountDatabases.size() - 1).setUserState(userState);
        accountDatabases.get(accountDatabases.size() - 1).setUserAadhaarNumber(userAadharNumber);
        accountDatabases.get(accountDatabases.size() - 1).setStatus(status);
        accountDatabases.get(accountDatabases.size() - 1).setDate(date);
    }

    public boolean accountCheck(String userAadharNumber) {
        ArrayList<AccountDatabase> account = bankDatabase.getAccount();
        for (int i = 0; i < account.size(); i++) {
            if (userAadharNumber.equals(account.get(i).getUserAadhaarNumber())) {
                return true;
            }
        }
        return false;
    }

    public String getAccountPinNumber() {
        String pinNumber = "";
        int number = (int) Math.floor(Math.random() * 9999);
        if (number >= 1000) {
            String randomNumber = String.valueOf(number);
            if (accountNumberCheck(randomNumber)) {
                pinNumber = randomNumber;
            }
        } else {
            getAccountPinNumber();
        }
        return pinNumber;
    }

    private boolean accountNumberCheck(String randomNumber) {
        ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (randomNumber.equals(accountDatabases.get(i).getPinNumber())) {
                return false;
            }
        }
        return true;
    }

    public void setAccountCount(int count) {
        bankDatabase.setAccountCount(count);
    }
}
