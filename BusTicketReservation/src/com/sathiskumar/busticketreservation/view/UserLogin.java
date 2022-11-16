package view;

import controler.LoginController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

class UserLogin {
    private String mailId;
    private String password;

    public void userLogin() {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("================================>> USER LOGIN <<================================");
            System.out.println("Enter mail id:");
            mailId = scanner.nextLine();
            System.out.println("Enter password:");
            password = scanner.nextLine();
            boolean userLogin = checkUserLogin();
            if (userLogin) {
                System.out.println("Login successfully...");
                new UserMainPage().userPage(mailId);
            } else {
                System.out.println("Please check your user name and password...");
                System.out.println("1) LOGIN");
                System.out.println("2) EXIT");
                System.out.println("Enter the number:");
                int option = scan.nextInt();
                if (option == 1) {
                    userLogin();
                }
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your name and account id...");
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) LOGIN");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int option = scanner.nextInt();
        if (option == 1) {
            userLogin();
        }
    }

    private boolean checkUserLogin() throws SQLException {
        LoginController loginController = new LoginController();
        boolean loginCheck = loginController.checkUserLogin(mailId, password);
        if (loginCheck) {
            return true;
        } else {
            return false;
        }
    }
}
