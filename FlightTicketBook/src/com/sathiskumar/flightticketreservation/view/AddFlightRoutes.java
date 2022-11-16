package view;

import controller.FlightController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddFlightRoutes {
    private int flightNumber;
    private String flightName;
    private String departureTime;
    private String arrivalTime;
    private int seat;
    private ArrayList<String> routes = new ArrayList<>();
    private int fare;
    private FlightController flightController;

    public AddFlightRoutes() {
        flightController = new FlightController(this);
    }

    public void addRoutes() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("============================ ADD FLIGHT ROUTES ============================");
            System.out.println("Enter the flight number:");
            String number = scanner.nextLine();
            flightNumber = Integer.parseInt(number);
            System.out.println("Enter the flight name:");
            flightName = scanner.nextLine();
            System.out.println("Enter the departure time:");
            departureTime = scanner.nextLine();
            System.out.println("Enter the arrival time:");
            arrivalTime = scanner.nextLine();
            System.out.println("Enter the seat:");
            String getSeat = scanner.nextLine();
            seat = Integer.parseInt(getSeat);
            System.out.println("Enter the total routes:");
            String numberOfRoutes = scanner.nextLine();
            int size = Integer.parseInt(numberOfRoutes);
            for (int i = 0; i < size; i++) {
                routes.add(scanner.nextLine());
            }
            System.out.println("Enter the fare:");
            String amount = scanner.nextLine();
            fare = Integer.parseInt(amount);
            System.out.println("Enter the PNR number:");
            String getPNR = scanner.nextLine();
            int pnr = Integer.parseInt(getPNR);
            flightController.addRoutes(flightNumber, flightName, departureTime, arrivalTime, seat, routes, fare, pnr);
            System.out.println("Flight add successfully...");
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Please check details...");
            addRoutes();
        }
    }
}
