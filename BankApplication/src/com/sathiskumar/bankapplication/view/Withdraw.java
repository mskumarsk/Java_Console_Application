package view;

import controller.TransactionController;
import model.AccountDatabase;
import model.TransactionDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Withdraw {
    private String userId;
    private String depositAmount;
    private String withdrawAmount;
    private TransactionController transactionController = TransactionController.getInstance();
    private ArrayList<TransactionDatabase> accountDatabases = transactionController.getAmountDetails();

    public void withdraw() {
        System.out.println("================================>> WITHDRAW <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter account id:");
            userId = scanner.nextLine();
            boolean checkAmount = checkAmount();
            if (checkAmount) {
                System.out.println("Enter withdraw amount:");
                withdrawAmount = scanner.nextLine();
                getWithdrawAmount();
            } else {
                System.out.println("Please check your id...");
                System.out.println("1) WITHDRAW");
                System.out.println("2) EXIT");
                System.out.println("Enter the number:");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    withdraw();
                }
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Please check your name and account id...");
        }
    }

    private boolean checkAmount() throws SQLException {
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (userId.equals(accountDatabases.get(i).getUserId())) {
                System.out.println("DEPOSIT AMOUNT:     " + accountDatabases.get(i).getDepositAmount());
                depositAmount = String.valueOf(accountDatabases.get(i).getDepositAmount());
                getAccountStatus();
                return true;
            }
        }
        return false;
    }

    private void getAccountStatus() {
        ArrayList<AccountDatabase> accountDatabase = transactionController.accountDetails();
        for (int i = 0; i < accountDatabase.size(); i++) {
            if (userId.equals(accountDatabase.get(i).getUserId())) {
                System.out.println("ACCOUNT STATUS:     " + accountDatabase.get(i).getStatus());
                break;
            }
        }
    }

    private void getWithdrawAmount() throws SQLException {
        if (Integer.parseInt(withdrawAmount) <= Integer.parseInt(depositAmount)) {
            transactionController.getWithdrawAmount(userId, depositAmount, withdrawAmount);
            System.out.println("Withdraw successfully...");
        } else {
            System.out.println("Check your deposit amount...");
            withdraw();
        }
    }
}
