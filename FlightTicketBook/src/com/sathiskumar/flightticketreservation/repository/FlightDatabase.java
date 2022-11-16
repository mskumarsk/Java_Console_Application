package repository;

import model.FlightDetails;
import model.TicketBookedDetails;

import java.util.ArrayList;

public class FlightDatabase {
    private static FlightDatabase flightDatabase;
    private ArrayList<FlightDetails> flightDetails;
    private ArrayList<TicketBookedDetails> bookedTicket;

    private FlightDatabase() {
        flightDetails = new ArrayList<>();
        bookedTicket = new ArrayList();
    }

    public static FlightDatabase getInstance() {
        if (flightDatabase == null) {
            flightDatabase = new FlightDatabase();
        }
        return flightDatabase;
    }

    public ArrayList<FlightDetails> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(ArrayList<FlightDetails> flightDetails) {
        this.flightDetails = flightDetails;
    }

    public ArrayList<TicketBookedDetails> getBookedTicket() {
        return bookedTicket;
    }

    public void setBookedTicket(ArrayList<TicketBookedDetails> bookedTicket) {
        this.bookedTicket = bookedTicket;
    }
}
