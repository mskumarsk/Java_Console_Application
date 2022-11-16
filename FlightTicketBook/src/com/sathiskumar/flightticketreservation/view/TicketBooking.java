package view;

import controller.FlightController;
import model.FlightDetails;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketBooking {
    private String fromStation;
    private String toStation;
    private FlightController flightController;
    private Scanner scanner = new Scanner(System.in);
    private int seatNumber = 100;

    public TicketBooking() {
        flightController = new FlightController(this);
    }

    public void ticketBooking() {
        try {
            System.out.println("============================= TICKET BOOKING =============================");
            System.out.println("Enter the from station:");
            fromStation = scanner.nextLine();
            System.out.println("Enter the to station:");
            toStation = scanner.nextLine();
            showFlightDetails();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Please check your details...");
            ticketBooking();
        }
    }

    private void showFlightDetails() throws InputMismatchException, NumberFormatException {
        ArrayList<FlightDetails> routes = flightController.getRoutes(fromStation, toStation);
        for (int i = 0; i < routes.size(); i++) {
            System.out.println("FLIGHT NUMBER   :  " + routes.get(i).getFlightNumber());
            System.out.println("FLIGHT NAME     :  " + routes.get(i).getFlightName());
            System.out.println("DEPARTURE TIME  :  " + routes.get(i).getDepartureTime());
            System.out.println("ARRIVAL TIME    :  " + routes.get(i).getArrivalTime());
            System.out.println("SEAT            :  " + routes.get(i).getSeat());
            System.out.println("ROUTES          :  ");
            ArrayList<String> flightRoute = new ArrayList<>(routes.get(i).getRoutes());
            for (int j = 0; j < flightRoute.size(); j++) {
                System.out.println(flightRoute.get(j));
            }
            System.out.println("FARE            :  " + routes.get(i).getFare());
            System.out.println("PNR NUMBER      :  " + routes.get(i).getPnrNumber());
        }
        ticketBook();
    }

    private void ticketBook() throws InputMismatchException, NumberFormatException {
        System.out.println("Enter the PNR number:");
        String getFlightNumber = scanner.nextLine();
        int flightNumber = Integer.parseInt(getFlightNumber);
        System.out.println("Enter number of passenger:");
        String getSeat = scanner.nextLine();
        int size = Integer.parseInt(getSeat);
        if (checkSeat(flightNumber, size)) {
            for (int i = 0; i < size; i++) {
                System.out.println("Enter your name:");
                String name = scanner.nextLine();
                System.out.println("Enter your age:");
                String getAge = scanner.nextLine();
                int age = Integer.parseInt(getAge);
                System.out.println("Enter your gender:");
                String gender = scanner.nextLine();
                seatNumber++;
                String status = "CNF";
                System.out.println("1) OK");
                System.out.println("2) CANCEL");
                System.out.println("Enter the option:");
                String getOption = scanner.nextLine();
                int option = Integer.parseInt(getOption);
                if (option == 1) {
                    int amount = getAmount(flightNumber);
                    flightController.addTickets(fromStation, toStation, flightNumber, name, age, gender,
                            seatNumber, status, amount);
                    System.out.println("ID    : " + seatNumber);
                    System.out.println("Ticket booked successfully...");
                } else {
                    ticketBooking();
                }
            }
        } else {
            System.out.println("Seat not available...");
            ticketBook();
        }
    }

    private boolean checkSeat(int flightNumber, int seats) {
        boolean checkSeat = flightController.ticketCheck(flightNumber, seats);
        if (checkSeat) {
            return true;
        } else {
            return false;
        }
    }

    private int getAmount(int flightNumber) {
        return flightController.getAmount(flightNumber);
    }
}
