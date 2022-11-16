package view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAccountPage {
    public void userAccount(String accountNumber, String pinNumber) {
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("================================>> USER ACCOUNT <<================================");

            System.out.println("1) ACCOUNT BALANCE");
            System.out.println("2) VIEW PROFILE");
            System.out.println("3) MONEY TRANSFER");
            System.out.println("4) VIEW STATEMENTS");
            System.out.println("5) SET PIN NUMBER");
            System.out.println("6) EXIT");
            try {
                Scanner scanner = new Scanner(System.in);
                int userOption = scanner.nextInt();
                if (userOption == 1) {
                    new ViewAccount().userAmountDetails(accountNumber);
                } else if (userOption == 2) {
                    new AccountDetails().accountDetails(accountNumber, pinNumber);
                } else if (userOption == 3) {
                    new MoneyTransfer().moneyTransfer();
                } else if (userOption == 4) {
                    new TransactionDetails().transaction(accountNumber);
                } else if (userOption == 5) {
                    new SetPinNumber().setPinNumber(accountNumber);
                } else {
                    isTrue = false;
                }
            } catch (InputMismatchException | SQLException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                userAccount(accountNumber, pinNumber);
            }
        }
    }
}
