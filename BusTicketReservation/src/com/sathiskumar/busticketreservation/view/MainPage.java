package view;

import repository.Database;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPage {
    public static void main(String[] args) throws SQLException {
        MainPage mainPage = new MainPage();
        mainPage.mainPage();
    }

    public void mainPage() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Database database = Database.getInstance();
        database.getLoginData();
        while (true) {
            System.out.println("=========================================>> BSU TICKET RESERVATION <<===================" +
                    "======================");
            try {
                System.out.println("1) ADMIN");
                System.out.println("2) USER");
                System.out.println("3) EXIT");
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new AdminLoginPage().adminLogin();
                } else if (option == 2) {
                    userOption();
                } else if (option == 3) {
                    System.exit(3);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                mainPage();
            }
        }
    }

    private void userOption() {
        try {
            System.out.println("1) LOGIN");
            System.out.println("2) SIGN UP");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
                new UserLogin().userLogin();
            } else if (option == 2) {
                new UserSignUp().signUp();
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter the number...");
        }
    }
}
