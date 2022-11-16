package roombooking;

import repository.Database;
import database.RoomBookingDatabase;

import java.util.ArrayList;

public class BookedRoomDetails {
    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void bookedRoom() {
        Database database = Database.getInstance();
        ArrayList<RoomBookingDatabase> bookingDatabases = database.getBookingDatabases();
        System.out.println("==================================================>>  BOOKED ROOMS  <<==============" +
                "==================================");
        System.out.print(" =====================================================================================" +
                "===============================================================================================" +
                "============================");
        System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                "PHOME") + " | " + centerString(20, "MAIL") + " | " + centerString(20,
                "ROOMS") + " | " + centerString(20, "ROOM TYPE") + " | " + centerString(20,
                "DAYS") + " | " + centerString(20, "DATE") + " | " + centerString(20,
                "AMOUNT") + " | " + centerString(20, "BOOKING DATE") + " | ");
        System.out.print("\n =====================================================================================" +
                "===============================================================================================" +
                "============================");
        for (int i = 0; i < bookingDatabases.size(); i++) {
            System.out.printf("\n | " + centerString(20, bookingDatabases.get(i).getUserName())
                    + " | " + centerString(20, bookingDatabases.get(i).getPhoneNumber())
                    + " | " + centerString(20, bookingDatabases.get(i).getMail())
                    + " | " + centerString(20, bookingDatabases.get(i).getRooms())
                    + " | " + centerString(20, bookingDatabases.get(i).getRoomType())
                    + " | " + centerString(20, bookingDatabases.get(i).getDays())
                    + " | " + centerString(20, bookingDatabases.get(i).getStartDate())
                    + " | " + centerString(20, bookingDatabases.get(i).getAmount())
                    + " | " + centerString(20, bookingDatabases.get(i).getBookingDate()) + " | ");
        }
        System.out.print("\n =====================================================================================" +
                "===============================================================================================" +
                "============================");
        System.out.println();
    }
}
