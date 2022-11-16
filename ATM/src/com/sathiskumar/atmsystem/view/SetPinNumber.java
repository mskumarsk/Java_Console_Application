package view;

import controller.Controller;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SetPinNumber {
    private int otpNumber;
    private int userOtpNumber;
    private Controller controller = new Controller();

    public void setPinNumber() throws InputMismatchException {
        System.out.println("======================= SET PIN NUMBER =======================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your account number:");
        int accountNumber = scanner.nextInt();
        if (accountCheck(accountNumber)) {
            otp();
            System.out.println("Enter the OTP number:");
            userOtpNumber = scanner.nextInt();
            if (checkOTP()) {
                System.out.println("Enter the pin number:");
                int pinNumber = scanner.nextInt();
                setNumber(pinNumber, accountNumber);
            } else {
                System.out.println("Please check OTP number...");
                setPinNumber();
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

    private void otp() {
        otpNumber = controller.otp();
        System.out.println("OTP Number  : " + otpNumber);
    }

    private boolean checkOTP() {
        if (controller.otpCheck(userOtpNumber, otpNumber)) {
            return true;
        } else {
            return false;
        }
    }

    private void setNumber(int pinNumber, int accountNumber) {
        controller.setPinNumber(pinNumber, accountNumber);
        System.out.println("Pin number check successfully...");
    }
}

