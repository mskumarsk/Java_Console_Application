package view;

import controller.FlightController;
import model.TicketBookedDetails;
import repository.FlightDatabase;

import java.util.ArrayList;

public class BookedTicketDetails {
    FlightController controller;

    BookedTicketDetails() {
        controller = new FlightController(this);
    }

    public void bookedTicketDetails() {
        System.out.println("============================  BOOKED TICKET DETAILS ============================");
        ArrayList<TicketBookedDetails> bookedDetails = controller.getBookedData();
        for (int i = 0; i < bookedDetails.size(); i++) {
            System.out.println("Name       : " + bookedDetails.get(i).getName());
            System.out.println("Age        : " + bookedDetails.get(i).getAge());
            System.out.println("Gender     : " + bookedDetails.get(i).getGender());
            System.out.println("FlightId   : " + bookedDetails.get(i).getFlightId());
            System.out.println("From station:" + bookedDetails.get(i).getFromStation());
            System.out.println("To station : " + bookedDetails.get(i).getToStation());
            System.out.println("Amount     : " + bookedDetails.get(i).getAmount());
            System.out.println("Seat number: " + bookedDetails.get(i).getSeatNumber());
            System.out.println("Status     : " + bookedDetails.get(i).getStatus());
        }
    }
}
