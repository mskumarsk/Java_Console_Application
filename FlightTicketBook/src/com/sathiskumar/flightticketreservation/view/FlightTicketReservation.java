package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightTicketReservation {
    public static void main(String[] args) {
        FlightTicketReservation ticketReservation = new FlightTicketReservation();
        ticketReservation.ticketReservation();
    }

    private void ticketReservation() {
        boolean isTrue = true;
        Scanner scanner = new Scanner(System.in);
        try {
            while (isTrue) {
                System.out.println("==========================  FLIGHT TICKET RESERVATION ==========================");
                System.out.println("1) BOOKING");
                System.out.println("2) GET PNR STATUS");
                System.out.println("3) BOOKED TICKETS");
                System.out.println("4) CANCEL TICKETS");
                System.out.println("5) SEARCH PASSENGERS");
                System.out.println("6) LIST FLIGHT ROUTES");
                System.out.println("7) ADD FLIGHT ROUTES");
                System.out.println("Enter the option:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new TicketBooking().ticketBooking();
                } else if (option == 2) {
                    new PNRStatus().getStatus();
                } else if (option == 3) {
                    new BookedTicketDetails().bookedTicketDetails();
                } else if (option == 4) {
                    new TicketCancel().ticketCancel();
                } else if (option == 5) {
                    new SearchPassenger().showPassengerDetails();
                } else if (option == 6) {
                    new FlightRoutes().flightRoutesShow();
                } else if (option == 7) {
                    new AddFlightRoutes().addRoutes();
                } else {
                    isTrue = false;
                    ticketReservation();
                }
            }
        } catch (InputMismatchException e) {
            isTrue = false;
            System.out.println("Enter the number...");
            ticketReservation();
        }
    }
}
