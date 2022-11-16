package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMView {
    public static void main(String[] args) {
        ATMView view = new ATMView();
        view.atmView();
    }

    private void atmView() {
        boolean isTrue = true;
        try {
            Scanner scanner = new Scanner(System.in);
            while (isTrue) {
                System.out.println("=========================== WELCOME =============================");
                System.out.println("1) WITHDRAW");
                System.out.println("2) DEPOSIT");
                System.out.println("3) CHECK BALANCE");
                System.out.println("4) SET PIN NUMBER");
                System.out.println("5) EXIT");
                System.out.println("Enter the option:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new Withdraw().withdraw();
                } else if (option == 2) {
                    new Deposit().deposit();
                } else if (option == 3) {
                    new ViewBalance().viewBalance();
                } else if (option == 4) {
                    new SetPinNumber().setPinNumber();
                } else if (option == 5) {
                    System.exit(option);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter the number...");
            isTrue = false;
            atmView();
        }
    }
}
