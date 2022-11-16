package view;

import controller.AccountController;
import controller.ValidationController;
import repository.BankDatabase;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NewAccount {
    private String userName;
    private String userDateOfBirth;
    private String userPhoneNumber;
    private String userGender;
    private String userCountry;
    private String userState;
    private String userAadharNumber;
    private String pinNumber;
    private int accountCount;
    private AccountController accountController = new AccountController();
    private BankDatabase bankDatabase = BankDatabase.getInstance();

    public void newAccount() {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("================================>> NEW ACCOUNT <<================================");
                System.out.println("Enter your name:");
                userName = scanner.nextLine();
                System.out.println("Enter your date of birth:");
                userDateOfBirth = scanner.nextLine();
                boolean numberCheckTrue = true;
                while (numberCheckTrue) {
                    System.out.println("Enter the phone number:");
                    long phoneNumber = scan.nextLong();
                    userPhoneNumber = String.valueOf(phoneNumber);
                    boolean numberCheck = phoneNumberCheck(userPhoneNumber);
                    if (numberCheck) {
                        numberCheckTrue = false;
                    } else {
                        System.out.println("Invalid Phone Number...");
                    }
                }
                System.out.println("Gender:");
                System.out.println("1) MALE");
                System.out.println("2) FEMALE");
                System.out.println("Enter the number:");
                int gender = scan.nextInt();
                if (gender == 1) {
                    userGender = "MALE";
                } else if (gender == 2) {
                    userGender = "FEMALE";
                }
                System.out.println("Enter country:");
                userCountry = scanner.nextLine();
                System.out.println("Enter state:");
                userState = scanner.nextLine();
                System.out.println("Enter your aadhar number:");
                long aadharNumber = scan.nextLong();
                userAadharNumber = String.valueOf(aadharNumber);
                accountCheck();
                System.out.println("0  -> OK");
                System.out.println("1  -> CANCEL");
                System.out.println("Enter the number:");
                int number = scan.nextInt();
                if (number == 0) {
                    pinNumber = accountController.getAccountPinNumber();
                    accountCount = bankDatabase.getAccountCount() + 1;
                    bankDatabase.setAccountCount(accountCount);
                    createNewAccount();
                    isTrue = false;
                } else if (number == 1) {
                    System.out.println("Cancel...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                callMethod();
            } catch (SQLException e) {
                System.out.println("Please check your details...");
                isTrue = false;
                callMethod();
            }
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) NEW ACCOUNT");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            newAccount();
        }
    }

    private void accountCheck() throws SQLException {
        boolean accountCheck = accountController.accountCheck(userAadharNumber);
        if (accountCheck) {
            System.out.println("This account already exist...");
            newAccount();
        }
    }

    private void createNewAccount() throws SQLException {
        accountController.createNewAccount(userName, userDateOfBirth, userPhoneNumber, userGender, userCountry,
                userState, userAadharNumber, pinNumber, accountCount);
        System.out.println("ACCOUNT NUMBER       :  " + accountCount);
        System.out.println("ACCOUNT PIN NUMBER   :  " + pinNumber);
        System.out.println("New account create successfully...");
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
