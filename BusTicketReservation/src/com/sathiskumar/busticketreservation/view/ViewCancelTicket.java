package view;

import controler.TicketController;
import model.TicketBook;

import java.util.ArrayList;

public class ViewCancelTicket {
    private String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void showCancelDetails() {
        System.out.println("==================================  CANCEL TICKETS  ==================================");
        TicketController ticketController = new TicketController();
        ArrayList<TicketBook> ticketBooks = ticketController.getTicketBook();
        System.out.print(" ===================================================================================" +
                "=============================================================================================" +
                "===================================================================================");
        System.out.printf("\n | " + centerString(25, "BUS") + " | " + centerString(20,
                "NAME") + " | " + centerString(20, "  PHONE NUMBER") + " | " + centerString(20,
                "FROM STATION") + " | " + centerString(20, "TO STATION") + " | " + centerString(20,
                "SEAT") + " | " + centerString(20, "TRAVEL DATE") + " | " + centerString(20,
                "AMOUNT") + " | " + centerString(20, "STATUS") + " | " + centerString(20,
                "MAIL") + " | " + centerString(20, "BOOKING DATE") + " | ");
        System.out.print("\n ===================================================================================" +
                "=============================================================================================" +
                "===================================================================================");
        String status = "BLOCK";
        for (int i = 0; i < ticketBooks.size(); i++) {
            if (status.equals(ticketBooks.get(i).getStatus())) {
                System.out.printf("\n | " + centerString(25,
                        ticketBooks.get(i).getBus()) + " | " + centerString(20,
                        ticketBooks.get(i).getUserName()) + " | " + centerString(20,
                        ticketBooks.get(i).getPhoneNumber()) + " | " + centerString(20,
                        ticketBooks.get(i).getFormStation()) + " | " + centerString(20,
                        ticketBooks.get(i).getToStation()) + " | " + centerString(20,
                        ticketBooks.get(i).getSeat()) + " | " + centerString(20,
                        ticketBooks.get(i).getDate()) + " | " + centerString(20,
                        ticketBooks.get(i).getAmount()) + " | " + centerString(20,
                        ticketBooks.get(i).getStatus()) + " | " + centerString(20,
                        ticketBooks.get(i).getMail()) + " | " + centerString(20,
                        ticketBooks.get(i).getBookingDate()) + " | ");
            }
        }
        System.out.print("\n ===================================================================================" +
                "=============================================================================================" +
                "===================================================================================");
        System.out.println();
    }
}
