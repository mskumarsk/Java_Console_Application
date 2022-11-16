package view;

import controler.TicketController;
import controler.ValidationController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketBook {
    private String bus;
    private String userName;
    private String phoneNumber;
    private String formStation;
    private String toStation;
    private String seat;
    private String date;
    private String amount;
    private int startPoint;
    private int endPoint;
    private TicketController ticketController = new TicketController();

    public void ticketBook(String mailId) {
        boolean isTrue = true;
        try {
            Scanner scan = new Scanner(System.in);
            while (isTrue) {
                System.out.println("================================>> TICKET BOOK <<================================");
                System.out.println("1) TENKASI TO CHENNAI");
                System.out.println("2) TENKASI TO KOVAI");
                System.out.println("3) CHENNAI TO TENKASI");
                System.out.println("4) KOVAI TO TENKASI");
                System.out.println("5) EXIT");
                int option = scan.nextInt();
                if (option == 1) {
                    bus = "TENKASI TO CHENNAI";
                    ticketReservation(mailId);
                    break;
                } else if (option == 2) {
                    bus = "TENKASI TO KOVAI";
                    ticketReservation(mailId);
                    break;
                } else if (option == 3) {
                    bus = "CHENNAI TO TENKASI";
                    ticketReservation(mailId);
                    break;
                } else if (option == 4) {
                    bus = "KOVAI TO TENKASI";
                    ticketReservation(mailId);
                    break;
                } else if (option == 5) {
                    isTrue = false;
                }
            }
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Check your details...");
            isTrue = false;
            callMethod(mailId);
        }
    }

    private void callMethod(String mailId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) TICKET BOOK");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            ticketBook(mailId);
        }
    }

    private void ticketReservation(String mailId) throws InputMismatchException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name:");
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
        System.out.println("Enter travel date : format(YYYY-MM-DD)");
        date = scanner.nextLine();
        System.out.println("How many seat:");
        int getSeat = scan.nextInt();
        seat = String.valueOf(getSeat);
        boolean checkSeat = ticketController.seatCheck(seat, date, bus);
        if (checkSeat) {
            setFromStation();
            System.out.println("1) OK");
            System.out.println("2) CANCEL");
            int option = scan.nextInt();
            if (option == 1) {
                insertBookingData(mailId);
            }
        } else {
            System.out.println("Seat not available...");
            ticketReservation(mailId);
        }
    }

    private void setFromStation() {
        Scanner scan = new Scanner(System.in);
        if (bus.equals("TENKASI TO CHENNAI") || bus.equals("CHENNAI TO TENKASI")) {
            System.out.println("Select from station:");
            System.out.println("1) TENKASI");
            System.out.println("2) MADURAI");
            System.out.println("3) THIRUCHIRAPPALLI");
            System.out.println("4) VILUPPURAM");
            System.out.println("5) CHENNAI");
            int startStation = scan.nextInt();
            if (startStation == 1) {
                startPoint = startStation;
                formStation = "TENKASI";
            } else if (startStation == 2) {
                startPoint = startStation;
                formStation = "MADURAI";
            } else if (startStation == 3) {
                startPoint = startStation;
                formStation = "THIRUCHIRAPPALLI";
            } else if (startStation == 4) {
                startPoint = startStation;
                formStation = "VILUPPURAM";
            } else if (startStation == 5) {
                startPoint = startStation;
                formStation = "CHENNAI";
            }
        } else {
            System.out.println("Select from station:");
            System.out.println("1) TENKASI");
            System.out.println("2) SIVAKASI");
            System.out.println("3) MADURAI");
            System.out.println("4) DINDIGUL");
            System.out.println("5) KOVAI");
            int startStation = scan.nextInt();
            if (startStation == 1) {
                startPoint = startStation;
                formStation = "TENKASI";
            } else if (startStation == 2) {
                startPoint = startStation;
                formStation = "MADURAI";
            } else if (startStation == 3) {
                startPoint = startStation;
                formStation = "THIRUCHIRAPPALLI";
            } else if (startStation == 4) {
                startPoint = startStation;
                formStation = "VILUPPURAM";
            } else if (startStation == 5) {
                startPoint = startStation;
                formStation = "CHENNAI";
            }
        }
        setToStation();
    }

    private void setToStation() {
        Scanner scan = new Scanner(System.in);
        if (bus.equals("TENKASI TO CHENNAI") || bus.equals("CHENNAI TO TENKASI")) {
            System.out.println("Select to station:");
            System.out.println("1) TENKASI");
            System.out.println("2) MADURAI");
            System.out.println("3) THIRUCHIRAPPALLI");
            System.out.println("4) VILUPPURAM");
            System.out.println("5) CHENNAI");
            int endStation = scan.nextInt();
            if (endStation == 1) {
                endPoint = endStation;
                toStation = "TENKASI";
            } else if (endStation == 2) {
                endPoint = endStation;
                toStation = "MADURAI";
            } else if (endStation == 3) {
                endPoint = endStation;
                toStation = "THIRUCHIRAPPALLI";
            } else if (endStation == 4) {
                endPoint = endStation;
                toStation = "VILUPPURAM";
            } else if (endStation == 5) {
                endPoint = endStation;
                formStation = "CHENNAI";
            }
        } else {
            System.out.println("Select to station:");
            System.out.println("1) TENKASI");
            System.out.println("2) SIVAKASI");
            System.out.println("3) MADURAI");
            System.out.println("4) DINDIGUL");
            System.out.println("5) KOVAI");
            int endStation = scan.nextInt();
            if (endStation == 1) {
                endPoint = endStation;
                toStation = "TENKASI";
            } else if (endStation == 2) {
                endPoint = endStation;
                toStation = "MADURAI";
            } else if (endStation == 3) {
                endPoint = endStation;
                toStation = "THIRUCHIRAPPALLI";
            } else if (endStation == 4) {
                endPoint = endStation;
                toStation = "VILUPPURAM";
            } else if (endStation == 5) {
                endPoint = endStation;
                formStation = "CHENNAI";
            }
        }
        amountDisplay();
    }

    private void amountDisplay() {
        amount = String.valueOf(ticketController.getAmount(seat, startPoint, endPoint, bus));
        System.out.println("TOTAL SEAT   :  " + seat);
        System.out.println("AMOUNT       :  " + amount);
    }

    private void insertBookingData(String mailId) throws SQLException {
        ticketController.insertBookingData(bus, userName, phoneNumber, formStation, toStation, seat, date, amount, mailId);
        System.out.println("Booking successfully...");
    }
}
