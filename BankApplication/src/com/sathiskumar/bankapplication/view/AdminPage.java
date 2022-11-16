package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPage {
    public void adminPage() {
        boolean isTrue = true;
        while (isTrue) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("================================>> ADMIN PAGE  <<================================");
                System.out.println("1) NEW ACCOUNT");
                System.out.println("2) DEPOSIT");
                System.out.println("3) WITHDRAW");
                System.out.println("4) MONEY TRANSFER");
                System.out.println("5) CUSTOMER ACCOUNT DETAILS");
                System.out.println("6) ACCOUNT BLOCK");
                System.out.println("7) ACCOUNT ACTIVE");
                System.out.println("8) BLOCK ACCOUNT DETAILS");
                System.out.println("9) EXIT");
                System.out.println("Enter the option:");
                int adminOption = scanner.nextInt();
                if (adminOption == 1) {
                    new NewAccount().newAccount();
                } else if (adminOption == 2) {
                    new Deposit().deposit();
                } else if (adminOption == 3) {
                    new Withdraw().withdraw();
                } else if (adminOption == 4) {
                    new MoneyTransfer().moneyTransfer();
                } else if (adminOption == 5) {
                    new ViewAccount().view();
                } else if (adminOption == 6) {
                    new AccountBlock().accountBlock();
                } else if (adminOption == 7) {
                    new AccountBlock().accountActive();
                } else if (adminOption == 8) {
                    new ViewAccount().showBlockAccounts();
                } else if (adminOption == 9) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
            }
        }
    }
}
