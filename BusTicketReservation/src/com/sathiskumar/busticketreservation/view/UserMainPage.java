package view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMainPage {
    public void userPage(String mailId) {
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("================================>> USER PAGE  <<================================");
                System.out.println("1) BOOKING");
                System.out.println("2) AVAILABLE TICKET");
                System.out.println("3) TICKET CANCEL");
                System.out.println("4) BOOKED TICKET");
                System.out.println("5) BUS DETAILS AND TIME");
                System.out.println("6) EXIT");
                System.out.println("Enter the number:");
                int userOption = scanner.nextInt();
                if (userOption == 1) {
                    new TicketBook().ticketBook(mailId);
                } else if (userOption == 2) {
                    new AvailableTickets().availableTicket();
                } else if (userOption == 3) {
                    new TicketCancel().ticketCancel(mailId);
                } else if (userOption == 4) {
                    new BookedTicketDetails().showBookedDetails(mailId);
                } else if (userOption == 5) {
                    new ContactDetails().showContactDetails();
                } else if (userOption == 6) {
                    isTrue = false;
                }
            } catch (InputMismatchException | SQLException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                userPage(mailId);
            }
        }
    }
}
