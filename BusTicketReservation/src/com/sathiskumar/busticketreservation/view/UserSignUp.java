package view;

import controler.LoginController;
import controler.ValidationController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserSignUp {
    private String userName;
    private String phoneNumber;
    private String mailId;
    private String password;
    private LoginController loginController = new LoginController();

    public void signUp() {
        boolean isTrue = true;
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        while (isTrue) {
            try {
                System.out.println("============================= SIGN UP =============================");
                System.out.println("Enter user name:");
                userName = scanner.nextLine();
                boolean numberCheckTrue = true;
                while (numberCheckTrue) {
                    System.out.println("Enter the phone number:");
                    long number = scan.nextLong();
                    phoneNumber = String.valueOf(number);
                    boolean numberCheck = new ValidationController().phoneNumberCheck(phoneNumber);
                    if (numberCheck) {
                        numberCheckTrue = false;
                    } else {
                        System.out.println("Invalid Phone Number...");
                    }
                }
                numberCheckTrue = true;
                while (numberCheckTrue) {
                    System.out.println("Enter your mail id:");
                    mailId = scanner.nextLine();
                    boolean mailCheck = new ValidationController().mailCheck(mailId);
                    if (mailCheck) {
                        numberCheckTrue = false;
                    } else {
                        System.out.println("Gmail not valid...");
                    }
                }
                System.out.println("Enter the password:");
                password = scanner.nextLine();
                System.out.println("1) OK");
                System.out.println("2) CANCEL");
                int option = scan.nextInt();
                if (option == 1) {
                    if (checkData()) {
                        loginController.insertLoginData(userName, phoneNumber, mailId, password);
                        System.out.println("Signup successfully...");
                        isTrue = false;
                    } else {
                        System.out.println("Already this id exists...");
                    }
                } else if (option == 2) {
                    isTrue = false;
                }
            } catch (InputMismatchException | SQLException e) {
                System.out.println("Enter the number...");
                isTrue = false;
                callMethod();
            }
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) SIGN UP");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            signUp();
        }
    }

    private boolean checkData() {
        boolean checkId = loginController.checkUserLogin(mailId, password);
        if (checkId) {
            return false;
        } else {
            return true;
        }
    }
}
