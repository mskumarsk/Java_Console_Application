package view;

import repository.BankDatabase;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) throws SQLException {
        BankMain bankMain = new BankMain();
        bankMain.mainPage();
    }

    public void mainPage() throws SQLException {
        BankDatabase bankDatabase = BankDatabase.getInstance();
        bankDatabase.getLoginData();
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("================================>> WELCOME  <<================================");
                System.out.println("1) ADMIN");
                System.out.println("2) USER");
                System.out.println("3) EXIT");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new AdminLogin().adminPage();
                } else if (option == 2) {
                    new UserLogin().userLogin();
                } else if (option == 3) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
            }
        }
    }
}
