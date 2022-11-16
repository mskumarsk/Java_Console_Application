package view;

import model.AccountDatabase;
import repository.BankDatabase;
import model.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountBlock {
    private String accountNumber;
    private BankDatabase bankDatabase = BankDatabase.getInstance();
    private ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void accountBlock() {
        System.out.println("================================>> ACCOUNT BLOCK  <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter customer account id:");
            accountNumber = scanner.nextLine();
            updateBlockAccount();
        } catch (NumberFormatException | InputMismatchException | SQLException e) {
            System.out.println("Please check your account number...");
        }
    }

    private void updateBlockAccount() throws SQLException {
        boolean accountCheck = false;
        String status = "BLOCK";
        String updateBlockAccount = "update new_account set account_status='" + status + "' where user_id='" + accountNumber + "'";
        databaseConnection.executeQuery(updateBlockAccount);
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (accountNumber.equals(accountDatabases.get(i).getUserId())) {
                accountDatabases.get(i).setStatus(status);
                System.out.println("Account block successfully...");
                accountCheck = true;
                break;
            }
        }

        if (!accountCheck) {
            System.out.println("Please check your account number...");
        }
    }

    public void accountActive() {
        System.out.println("================================>> ACCOUNT ACTIVE <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter customer account id:");
            accountNumber = scanner.nextLine();
            updateActive();
        } catch (SQLException e) {
            System.out.println("Please check your account number...");
        }
    }


    private void updateActive() throws SQLException {
        boolean accountCheck = false;
        String status = "ACTIVE";
        String updateBlockAccount = "update new_account set account_status='" + status + "' where user_id='" + accountNumber + "'";
        databaseConnection.executeQuery(updateBlockAccount);
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (accountNumber.equals(accountDatabases.get(i).getUserId())) {
                accountDatabases.get(i).setStatus(status);
                System.out.println("Account active successfully...");
                accountCheck = true;
                break;
            }
        }
        if (!accountCheck) {
            System.out.println("Please check your account number...");
        }
    }
}
