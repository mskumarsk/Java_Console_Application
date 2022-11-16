package view;

import controler.TicketController;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketCancel {
    private String bus;
    private String userName;
    private String date;

    public void ticketCancel(String mailId) throws InputMismatchException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        System.out.println("================================>> TICKET CANCEL  <<================================");
        System.out.println("1) TENKASI TO CHENNAI");
        System.out.println("2) TENKASI TO KOVAI");
        System.out.println("3) CHENNAI TO TENKASI");
        System.out.println("4) KOVAI TO TENKASI");
        System.out.println("5) EXIT");
        int option = scan.nextInt();
        if (option == 1) {
            bus = "TENKASI TO CHENNAI";
        } else if (option == 2) {
            bus = "TENKASI TO KOVAI";
        } else if (option == 3) {
            bus = "CHENNAI TO TENKASI";
        } else if (option == 4) {
            bus = "KOVAI TO TENKASI";
        }
        System.out.println("Enter the name:");
        userName = scanner.nextLine();
        System.out.println("Enter the date:");
        date = scanner.nextLine();
        TicketController ticketController = new TicketController();
        ticketController.cancelTicket(bus, userName, date, mailId);
        System.out.println("Ticket cancel successfully...");
    }
}
