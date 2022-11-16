package view;

import controler.TicketController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AvailableTickets {
    private String burRoot;
    private String date;

    public void availableTicket() {
        try {
            System.out.println("================================>> AVAILABLE TICKET  <<===========================" +
                    "=====");
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            System.out.println("Select bus:");
            System.out.println("1) TENKASI TO CHENNAI");
            System.out.println("2) TENKASI TO KOVAI");
            System.out.println("3) CHENNAI TO TENKASI");
            System.out.println("4) KOVAI TO TENKASI");
            int option = scan.nextInt();
            if (option == 1) {
                burRoot = "TENKASI TO CHENNAI";
            } else if (option == 2) {
                burRoot = "TENKASI TO KOVAI";
            } else if (option == 3) {
                burRoot = "CHENNAI TO TENKASI";
            } else if (option == 4) {
                burRoot = "KOVAI TO TENKASI";
            }
            System.out.println("Enter the date : format(YYYY-MM-DD)");
            date = scanner.nextLine();
            getAvailableSeat();
        } catch (InputMismatchException e) {
            System.out.println("Please check details...");
        }
    }

    private void getAvailableSeat() {
        TicketController ticketController = new TicketController();
        int getSeatCount = ticketController.getAvailableSeat(burRoot, date);
        System.out.println(burRoot + "           : " + getSeatCount);
    }
}
