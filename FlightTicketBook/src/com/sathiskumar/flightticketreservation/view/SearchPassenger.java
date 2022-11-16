package view;

import controller.FlightController;
import model.TicketBookedDetails;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchPassenger {
    private FlightController flightController;

    public SearchPassenger() {
        flightController = new FlightController(this);
    }

    public void showPassengerDetails() {
        System.out.println("Enter the passenger id:");
        Scanner scanner = new Scanner(System.in);
        String getId = scanner.nextLine();
        int id = Integer.parseInt(getId);
        showDetails(id);
    }

    private void showDetails(int id) {
        ArrayList<TicketBookedDetails> bookedDetails = flightController.getBookedDetails();
        System.out.println("=========================== BOOKED DETAILS ===========================");
        for (int i = 0; i < bookedDetails.size(); i++) {
            if (id == bookedDetails.get(i).getSeatNumber()) {
                System.out.println("FLIGHT NUMBER   :  " + bookedDetails.get(i).getFlightId());
                System.out.println("NAME            :  " + bookedDetails.get(i).getName());
                System.out.println("AGE             :  " + bookedDetails.get(i).getAge());
                System.out.println("GENDER          :  " + bookedDetails.get(i).getGender());
                System.out.println("FORM STATION    :  " + bookedDetails.get(i).getFromStation());
                System.out.println("TO STATION      :  " + bookedDetails.get(i).getToStation());
                System.out.println("STATUS          :  " + bookedDetails.get(i).getStatus());
            }
        }
    }
}
