package view;

import controller.FlightController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketCancel {
    private FlightController flightController;

    public TicketCancel() {
        flightController = new FlightController(this);
    }

    public void ticketCancel() throws InputMismatchException, NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the PNR number:");
        String number = scanner.nextLine();
        int pnrNumber = Integer.parseInt(number);
        System.out.println("Enter the seat number:");
        String getSeat = scanner.nextLine();
        int seat = Integer.parseInt(getSeat);
        System.out.println("1) OK");
        System.out.println("2) CANCEL");
        System.out.println("Enter the option:");
        String getOption = scanner.nextLine();
        int option = Integer.parseInt(getOption);
        if (option == 1) {
            flightController.ticketCancel(pnrNumber, seat);
            System.out.println("Ticket cancel successfully...");
        } else if (option == 2) {
            ticketCancel();
        }
    }
}
