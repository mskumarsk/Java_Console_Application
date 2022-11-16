package view;

import controller.FlightController;
import model.FlightDetails;

import java.util.ArrayList;

public class FlightRoutes {
    FlightController flightController;

    FlightRoutes() {
        flightController = new FlightController(this);
    }

    public void flightRoutesShow() {
        ArrayList<FlightDetails> flightDetails = flightController.getFlightData();
        for (int i = 0; i < flightDetails.size(); i++) {
            System.out.println("Flight Id   : " + flightDetails.get(i).getFlightNumber());
            System.out.println("Flight Name   : " + flightDetails.get(i).getFlightName());
            System.out.println("Routes  :");
            ArrayList routes = flightDetails.get(i).getRoutes();
            for (int j = 0; j < routes.size(); j++) {
                System.out.println(routes.get(j));
            }
        }
    }
}
