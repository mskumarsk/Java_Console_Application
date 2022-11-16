package view;

import controller.TransactionController;
import model.AccountDatabase;
import model.TransactionDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDetails {
    private String userId;
    private TransactionController transactionController = TransactionController.getInstance();
    private ArrayList<AccountDatabase> accountDatabases = transactionController.accountDetails();

    public void accountDetails(String accountId, String pinNumber) throws SQLException {
        this.userId = accountId;
        System.out.println("===========================>>  ACCOUNT DETAILS  <<===========================");
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (userId.equals(accountDatabases.get(i).getUserId())) {
                if (pinNumber.equals(accountDatabases.get(i).getPinNumber())) {
                    System.out.println("USER ACCOUNT NUMBER :  " + accountDatabases.get(i).getUserId());
                    System.out.println("USER NAME           :  " + accountDatabases.get(i).getUserName());
                    System.out.println("DATE OF BIRTH       :  " + accountDatabases.get(i).getUserDateOfBirth());
                    System.out.println("PHONE NO            :  " + accountDatabases.get(i).getUserPhoneNumber());
                    System.out.println("GENDER              :  " + accountDatabases.get(i).getUserGender());
                    System.out.println("COUNTRY             :  " + accountDatabases.get(i).getUserCountry());
                    System.out.println("STATE               :  " + accountDatabases.get(i).getUserState());
                    System.out.println("AADHAR NUMBER       :  " + accountDatabases.get(i).getUserAadhaarNumber());
                    break;
                }
            }
        }
        getDepositAmount();
    }

    private void getDepositAmount() {
        ArrayList<TransactionDatabase> deposit = transactionController.getAmountDetails();
        for (int i = 0; i < deposit.size(); i++) {
            if (userId.equals(deposit.get(i).getUserId())) {
                System.out.println("AMOUNT              :  " + deposit.get(i).getDepositAmount());
                break;
            }
        }
    }
}
