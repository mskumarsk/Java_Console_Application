package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMainPage {
    public void adminPage() {
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("===================================>> ADMIN PAGE  <<============================" +
                        "=======");
                System.out.println("1) BOOKED TICKETS");
                System.out.println("2) AVAILABLE TICKETS");
                System.out.println("3) CANCEL TICKETS");
                System.out.println("4) UPDATE CONTACT DETAILS");
                System.out.println("5) EXIT");
                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                if (option == 1) {
                    new ViewBookedTicket().showBookedDetails();
                } else if (option == 2) {
                    new AvailableTickets().availableTicket();
                } else if (option == 3) {
                    new ViewCancelTicket().showCancelDetails();
                } else if (option == 4) {
                    new UpdateContact().updateContact();
                } else if (option == 5) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                adminPage();
            }
        }
    }
}
