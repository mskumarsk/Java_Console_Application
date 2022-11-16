package controller;

import repository.FlightDatabase;
import model.FlightDetails;
import model.TicketBookedDetails;
import view.*;

import java.util.ArrayList;

public class FlightController {
    private FlightDatabase database = FlightDatabase.getInstance();
    private ArrayList<FlightDetails> flightDetails = database.getFlightDetails();
    private ArrayList<TicketBookedDetails> bookedDetails = database.getBookedTicket();
    private AddFlightRoutes addFlightRoutes;
    private TicketBooking ticketBooking;
    private PNRStatus pnrStatus;
    private TicketCancel ticketCancel;
    private SearchPassenger passenger;
    private BookedTicketDetails bookedTicketDetails;
    private FlightRoutes flightRoutes;

    public FlightController(AddFlightRoutes addFlightRoutes) {
        this.addFlightRoutes = addFlightRoutes;
    }

    public FlightController(TicketBooking ticketBooking) {
        this.ticketBooking = ticketBooking;
    }

    public FlightController(PNRStatus pnrStatus) {
        this.pnrStatus = pnrStatus;
    }

    public FlightController(TicketCancel ticketCancel) {
        this.ticketCancel = ticketCancel;
    }

    public FlightController(SearchPassenger searchPassenger) {
        this.passenger = searchPassenger;
    }

    public FlightController(BookedTicketDetails bookedTicketDetails) {
        this.bookedTicketDetails = bookedTicketDetails;
    }

    public FlightController(FlightRoutes flightRoutes) {
        this.flightRoutes = flightRoutes;
    }

    //  Add Flight Routes
    public void addRoutes(int flightNumber, String flightName, String departureTime, String arrivalTime, int seat,
                          ArrayList<String> routes, int fare, int pnr) {
        flightDetails.add(new FlightDetails());
        flightDetails.get(flightDetails.size() - 1).setFlightNumber(flightNumber);
        flightDetails.get(flightDetails.size() - 1).setFlightName(flightName);
        flightDetails.get(flightDetails.size() - 1).setDepartureTime(departureTime);
        flightDetails.get(flightDetails.size() - 1).setArrivalTime(arrivalTime);
        flightDetails.get(flightDetails.size() - 1).setSeat(seat);
        flightDetails.get(flightDetails.size() - 1).setRoutes(routes);
        flightDetails.get(flightDetails.size() - 1).setFare(fare);
        flightDetails.get(flightDetails.size() - 1).setPnrNumber(pnr);
    }

    // Get Flight Routes
    public ArrayList<FlightDetails> getRoutes(String fromStation, String toStation) {
        ArrayList<FlightDetails> flightList = new ArrayList();
        for (int i = 0; i < flightDetails.size(); i++) {
            if (fromStation.equals(flightDetails.get(i).getRoutes().get(i))) {
                for (int j = i + 1; j < flightDetails.get(i).getRoutes().size(); j++) {
                    if (toStation.equals(flightDetails.get(i).getRoutes().get(j))) {
                        flightList.add(flightDetails.get(i));
                    }
                }
            }
        }
        return flightList;
    }

    // Ticket Check
    public boolean ticketCheck(int flightNumber, int seats) {
        int flightSeats = 0;
        for (int i = 0; i < flightDetails.size(); i++) {
            if (flightNumber == flightDetails.get(i).getPnrNumber()) {
                flightSeats = flightDetails.get(i).getSeat();
                break;
            }
        }
        int cnt = 0;
        for (int j = 0; j < bookedDetails.size(); j++) {
            if (flightNumber == bookedDetails.get(j).getFlightId()) {
                if ("CNF".equals(bookedDetails.get(j).getStatus())) {
                    cnt++;
                }
            }
        }
        int totalSeat = cnt + seats;
        if (flightSeats >= totalSeat) {
            return true;
        } else {
            return false;
        }
    }

    //  Get Amount
    public int getAmount(int flightNumber) {
        int amount = 0;
        for (int i = 0; i < flightDetails.size(); i++) {
            if (flightNumber == flightDetails.get(i).getFare()) {
                amount = flightDetails.get(i).getFare();
            }
        }
        return amount;
    }

    public void addTickets(String fromStation, String toStation, int flightNumber, String name, int age,
                           String gender, int seatNumber, String status, int amount) {
        bookedDetails.add(new TicketBookedDetails());
        bookedDetails.get(bookedDetails.size() - 1).setFromStation(fromStation);
        bookedDetails.get(bookedDetails.size() - 1).setToStation(toStation);
        bookedDetails.get(bookedDetails.size() - 1).setFlightId(flightNumber);
        bookedDetails.get(bookedDetails.size() - 1).setName(name);
        bookedDetails.get(bookedDetails.size() - 1).setAge(age);
        bookedDetails.get(bookedDetails.size() - 1).setGender(gender);
        bookedDetails.get(bookedDetails.size() - 1).setSeatNumber(seatNumber);
        bookedDetails.get(bookedDetails.size() - 1).setStatus(status);
        bookedDetails.get(bookedDetails.size() - 1).setAmount(amount);
    }

    public ArrayList getBookedDetails() {
        return bookedDetails;
    }

    public ArrayList getFlightDetails() {
        return flightDetails;
    }

    // Ticket Cancel
    public void ticketCancel(int pnrNumber, int seat) {
        for (int j = 0; j < bookedDetails.size(); j++) {
            if (pnrNumber == bookedDetails.get(j).getFlightId()) {
                if (seat == bookedDetails.get(j).getSeatNumber()) {
                    bookedDetails.get(j).setStatus("CANCEL");
                }
            }
        }
    }

    public ArrayList<TicketBookedDetails> getBookedData() {
        return bookedDetails;
    }

    public ArrayList<FlightDetails> getFlightData() {
        return flightDetails;
    }
}
