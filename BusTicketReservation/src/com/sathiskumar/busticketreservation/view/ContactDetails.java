package view;

import controler.TicketController;
import model.BusDetailsDatabase;

import java.util.ArrayList;

public class ContactDetails {
    private String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void showContactDetails() {
        System.out.println("===================================  CONTACT DETAILS  ===================================");
        TicketController ticketController = new TicketController();
        ArrayList<BusDetailsDatabase> busDetailsDatabases = ticketController.getBusDetails();
        System.out.print(" ===================================================================================" +
                "=============================================================================================" +
                "=======================================================");
        System.out.printf("\n | " + centerString(15, "TRAVEL NAME") + " | " + centerString(20,
                "PHONE NUMBER") + " | " + centerString(25, "  ADDRESS") + " | " + centerString(20,
                "BUS ROOT") + " | " + centerString(20, "TOTAL SEAT") + " | " + centerString(20,
                "START TIME") + " | " + centerString(20, "END TIME") + " | " + centerString(20,
                "BUS NUMBER") + " | " + centerString(20, "DRIVER NAME") + " | " + centerString(20,
                "DRIVER PHONE NUMBER") + " | ");
        System.out.print("\n ===================================================================================" +
                "=============================================================================================" +
                "=======================================================");
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            System.out.printf("\n | " + centerString(15,
                    busDetailsDatabases.get(i).getTravelName()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getPhoneNumber()) + " | " + centerString(25,
                    busDetailsDatabases.get(i).getAddress()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getBusRoot()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getSeat()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getStartTime()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getEndTime()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getBusNumber()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getDriverName()) + " | " + centerString(20,
                    busDetailsDatabases.get(i).getDriverPhoneNumber()) + " | ");
        }
        System.out.print("\n ===================================================================================" +
                "=============================================================================================" +
                "=======================================================");
        System.out.println();
    }
}
