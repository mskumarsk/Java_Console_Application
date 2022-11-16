package view;

import controler.TicketController;
import controler.ValidationController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateContact {
    private String busNumber;
    private TicketController ticketController = new TicketController();
    private Scanner scanner = new Scanner(System.in);
    private Scanner scan = new Scanner(System.in);

    public void updateContact() {
        boolean isTrue = true;
        try {
            while (isTrue) {
                System.out.println("=================================  UPDATE CONTACT  =================================");
                System.out.println("Select update:");
                System.out.println("1) TRAVEL NAME");
                System.out.println("2) PHONE NUMBER");
                System.out.println("3) ADDRESS");
                System.out.println("4) AMOUNT");
                System.out.println("5) START TIME");
                System.out.println("6) END TIME");
                System.out.println("7) DRIVER NAME");
                System.out.println("8) DRIVER PHONE NUMBER");
                System.out.println("9) EXIT");
                int option = scan.nextInt();
                if (option == 1) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setBusNumber();
                } else if (option == 2) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setPhoneNumber();
                } else if (option == 3) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setAddress();
                } else if (option == 4) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setAmount();
                } else if (option == 5) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setStartTime();
                } else if (option == 6) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setEndTime();
                } else if (option == 7) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setDriverName();
                } else if (option == 8) {
                    System.out.println("Enter the bus number:");
                    busNumber = scanner.nextLine();
                    setDriverNumber();
                } else if (option == 9) {
                    isTrue = false;
                }
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Enter the number...");
            isTrue = false;
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) UPDATE CONTACT");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            updateContact();
        }
    }

    private void setBusNumber() throws SQLException {
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        ticketController.setTravelName(busNumber, name);
        System.out.println("Update successfully...");
    }

    private void setPhoneNumber() throws SQLException {
        boolean numberCheckTrue = true;
        while (numberCheckTrue) {
            System.out.println("Enter the phone number:");
            long number = scan.nextLong();
            String phoneNumber = String.valueOf(number);
            boolean numberCheck = new ValidationController().phoneNumberCheck(phoneNumber);
            if (numberCheck) {
                ticketController.setPhoneNumber(busNumber, phoneNumber);
                numberCheckTrue = false;
            } else {
                System.out.println("Invalid Phone Number...");
            }
        }
        System.out.println("Update successfully...");
    }

    private void setAddress() throws SQLException {
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        ticketController.setAddress(busNumber, address);
        System.out.println("Update successfully...");
    }

    private void setAmount() throws SQLException, InputMismatchException {
        System.out.println("Enter amount:");
        int getAmount = scan.nextInt();
        String amount = String.valueOf(getAmount);
        ticketController.setAmount(busNumber, amount);
        System.out.println("Update successfully...");
    }

    private void setStartTime() throws SQLException {
        System.out.println("Enter start time:");
        String time = scanner.nextLine();
        ticketController.setStartTime(busNumber, time);
        System.out.println("Update successfully...");
    }

    private void setEndTime() throws SQLException {
        System.out.println("Enter end time:");
        String time = scanner.nextLine();
        ticketController.setEndTime(busNumber, time);
        System.out.println("Update successfully...");
    }

    private void setDriverName() throws SQLException {
        System.out.println("Enter driver name:");
        String name = scanner.nextLine();
        ticketController.setDriverName(busNumber, name);
        System.out.println("Update successfully...");
    }

    private void setDriverNumber() throws SQLException {
        boolean numberCheckTrue = true;
        while (numberCheckTrue) {
            System.out.println("Enter driver phone number:");
            long number = scan.nextLong();
            String phoneNumber = String.valueOf(number);
            boolean numberCheck = new ValidationController().phoneNumberCheck(phoneNumber);
            if (numberCheck) {
                ticketController.setDriverNumber(busNumber, phoneNumber);
                numberCheckTrue = false;
            } else {
                System.out.println("Invalid Phone Number...");
            }
        }
        System.out.println("Update successfully...");
    }
}
