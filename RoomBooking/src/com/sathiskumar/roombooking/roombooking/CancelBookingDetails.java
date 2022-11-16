package roombooking;

import database.CancelBookingData;
import repository.Database;

import java.util.ArrayList;

public class CancelBookingDetails {
    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void showCancelBookingDetails() {
        Database database = Database.getInstance();
        ArrayList<CancelBookingData> cancelBookingData = database.getCancelBookingData();
        System.out.println("==================================================>>  CANCEL BOOKING ROOMS  <<==============" +
                "==================================");
        System.out.print(" =====================================================================================" +
                "===============================================================================================" +
                "===================================================");
        System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                "PHOME") + " | " + centerString(20, "MAIL") + " | " + centerString(20,
                "ROOMS") + " | " + centerString(20, "ROOM TYPE") + " | " + centerString(20,
                "DAYS") + " | " + centerString(20, "DATE") + " | " + centerString(20,
                "AMOUNT") + " | " + centerString(20, "BOOKED DATE") + " | " + centerString(20,
                "CANCEL DATE") + " | ");
        System.out.print("\n =====================================================================================" +
                "===============================================================================================" +
                "===================================================");
        for (int i = 0; i < cancelBookingData.size(); i++) {
            System.out.printf("\n | " + centerString(20, cancelBookingData.get(i).getUserName())
                    + " | " + centerString(20, cancelBookingData.get(i).getPhoneNumber())
                    + " | " + centerString(20, cancelBookingData.get(i).getMail())
                    + " | " + centerString(20, cancelBookingData.get(i).getRooms())
                    + " | " + centerString(20, cancelBookingData.get(i).getRoomType())
                    + " | " + centerString(20, cancelBookingData.get(i).getDays())
                    + " | " + centerString(20, cancelBookingData.get(i).getStartDate())
                    + " | " + centerString(20, cancelBookingData.get(i).getAmount())
                    + " | " + centerString(20, cancelBookingData.get(i).getBookingDate())
                    + " | " + centerString(20, cancelBookingData.get(i).getCancelDate()) + " | ");
        }
        System.out.print("\n =====================================================================================" +
                "===============================================================================================" +
                "===================================================");
        System.out.println();
    }
}