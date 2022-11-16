package view;

import controller.LoginController;
import controller.ValidationController;

import java.sql.SQLException;
import java.util.Scanner;

public class SetPinNumber {
    private String phoneNumber;
    private String pinNumber;

    public void setPinNumber(String accountNumber) {
        System.out.println("================================>> SET PIN NUMBER <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("Enter the phone number:");
                long getPhoneNumber = scan.nextLong();
                phoneNumber = String.valueOf(getPhoneNumber);
                boolean numberCheck = phoneNumberCheck(phoneNumber);
                if (numberCheck) {
                    isTrue = false;
                } else {
                    System.out.println("Invalid Phone Number...");
                }
            }
            System.out.println("Enter the pin number:");
            pinNumber = scanner.nextLine();
            updatePinNumber(accountNumber);
        } catch (SQLException e) {
            System.out.println("Please check your details...");
        }
    }

    private void updatePinNumber(String accountNumber) throws SQLException {
        LoginController loginController = new LoginController();
        boolean accountCheck = loginController.updatePinNumber(accountNumber, pinNumber, phoneNumber);
        if (accountCheck) {
            System.out.println("Pin number updated...");
        } else {
            System.out.println("Check your account number and phone number...");
        }
    }

    private boolean phoneNumberCheck(String userPhoneNumber) {
        ValidationController validationController = new ValidationController();
        boolean numberCheck = validationController.phoneNumberCheck(userPhoneNumber);
        if (numberCheck) {
            return true;
        } else {
            return false;
        }
    }
}
