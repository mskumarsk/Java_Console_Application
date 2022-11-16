package view;

import controller.TransactionController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Deposit {
    private String userId;
    private String depositAmount;
    private TransactionController transactionController = TransactionController.getInstance();

    public void deposit() {
        System.out.println("================================>> DEPOSIT <<================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id:");
        userId = scanner.nextLine();
        try {
            boolean accountCheck = transactionController.accountCheck(userId);
            if (accountCheck) {
                try {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Enter deposit amount:");
                    int amount = scan.nextInt();
                    depositAmount = String.valueOf(amount);
                    System.out.println("0  -> OK");
                    System.out.println("1  -> CANCEL");
                    System.out.println("Enter the number:");
                    int option = scanner.nextInt();
                    if (option == 0) {
                        insertDepositAmount();
                    } else if (option == 1) {
                        System.out.println("Cancel...");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter number...");
                }
            } else {
                System.out.println("Account not found, create account...");
            }
        } catch (SQLException e) {
            System.out.println("Please check your user name and password...");
        }

    }


    private void insertDepositAmount() throws SQLException {
        transactionController.insertDepositAmount(userId, depositAmount);
        System.out.println("Deposit successfully...");
        transactionController.getDepositAmount(userId, depositAmount);
    }
}
