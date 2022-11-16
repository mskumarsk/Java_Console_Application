package view;

import controller.FlightController;
import model.FlightDetails;
import model.TicketBookedDetails;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PNRStatus {
    private FlightController controller;

    PNRStatus() {
        controller = new FlightController(this);
    }

    public void getStatus() throws InputMismatchException, NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the PNR number:");
        String getPnr = scanner.nextLine();
        int pnrNumber = Integer.parseInt(getPnr);
        showBookedDetails(pnrNumber);
    }

    private void showBookedDetails(int pnrNumber) {
        ArrayList<TicketBookedDetails> bookedDetails = controller.getBookedDetails();
        System.out.println("=========================== BOOKED DETAILS ===========================");
        for (int i = 0; i < bookedDetails.size(); i++) {
            if (pnrNumber == bookedDetails.get(i).getFlightId()) {
                System.out.println("FLIGHT NUMBER   :  " + bookedDetails.get(i).getFlightId());
                System.out.println("NAME            :  " + bookedDetails.get(i).getName());
                System.out.println("AGE             :  " + bookedDetails.get(i).getAge());
                System.out.println("GENDER          :  " + bookedDetails.get(i).getGender());
                System.out.println("FORM STATION    :  " + bookedDetails.get(i).getFromStation());
                System.out.println("TO STATION      :  " + bookedDetails.get(i).getToStation());
                System.out.println("STATUS          :  " + bookedDetails.get(i).getStatus());
            }
        }
        showFlightDetails(pnrNumber);
    }

    private void showFlightDetails(int pnrNumber) {
        ArrayList<FlightDetails> flightDetails = controller.getFlightDetails();
        System.out.println("=========================== FLIGHT DETAILS ===========================");
        for (int i = 0; i < flightDetails.size(); i++) {
            if (pnrNumber == flightDetails.get(i).getPnrNumber()) {
                System.out.println("FLIGHT NUMBER    :  " + flightDetails.get(i).getFlightNumber());
                System.out.println("FLIGHT NAME      :  " + flightDetails.get(i).getFlightName());
                System.out.println("DEPARTURE TIME   :  " + flightDetails.get(i).getDepartureTime());
                System.out.println("ARRIVAL TIME     :  " + flightDetails.get(i).getArrivalTime());
                System.out.println("SEAT             :  " + flightDetails.get(i).getSeat());
                System.out.println("FARE             :  " + flightDetails.get(i).getFare());
                System.out.println("PNR NUMBER       :  " + flightDetails.get(i).getPnrNumber());
            }
        }
    }
}
