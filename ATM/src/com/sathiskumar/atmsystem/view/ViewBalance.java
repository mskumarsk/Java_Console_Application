package view;

import controller.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewBalance {
    private Controller controller = new Controller();

    public void viewBalance() throws InputMismatchException {
        System.out.println("=========================== BALANCE ===========================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your account number:");
        int accountNumber = scanner.nextInt();
        if (accountCheck(accountNumber)) {
            System.out.println("Enter the pin number:");
            int pinNumber = scanner.nextInt();
            System.out.println("1) OK");
            System.out.println("2) CANCEL");
            int option = scanner.nextInt();
            if (option == 1) {
                if (pinNumberCheck(pinNumber)) {
                    viewAmount(accountNumber);
                } else {
                    System.out.println("Please check your pin number...");
                }
            }
        } else {
            System.out.println("Please check your account number...");
        }
    }

    private boolean accountCheck(int accountNumber) {
        if (controller.accountCheck(accountNumber)) {
            return true;
        } else {
            return false;
        }
    }

    private void viewAmount(int accountNumber) {
        int amount = controller.getBalanceAmount(accountNumber);
        System.out.println("BALANCE AMOUNT           :  " + amount);
    }

    private boolean pinNumberCheck(int pinNumber) {
        if (controller.pinNumberCheck(pinNumber)) {
            return true;
        } else {
            return false;
        }
    }
}
