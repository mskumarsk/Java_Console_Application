package roombooking;

import repository.Database;
import database.RoomBookingDatabase;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookedDatails {
    private String phoneNumber;
    private String userName;

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void details() {
        System.out.println("==================================================>>  BOOKED ROOMS  <<==============" +
                "==================================");
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Enter the name:");
            userName = scan.nextLine();
            System.out.println("Enter your phone number:");
            long phoneNo = scanner.nextLong();
            phoneNumber = String.valueOf(phoneNo);
            boolean numberCheck = phoneNumberCheck(phoneNumber);
            if (numberCheck) {
                isTrue = false;
            } else {
                System.out.println("Invalid Phone Number...");
            }
        }
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
                "AMOUNT") + " | " + centerString(20, "BOOKED DATE") + " | ");
        System.out.print("\n =====================================================================================" +
                "===============================================================================================" +
                "============================");
        for (int i = 0; i < bookingDatabases.size(); i++) {
            if (userName.equals(bookingDatabases.get(i).getUserName())) {
                if (phoneNumber.equals(bookingDatabases.get(i).getPhoneNumber())) {
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
            }
        }
        System.out.print("\n =====================================================================================" +
                "===============================================================================================" +
                "============================");
        System.out.println();
    }

    private boolean phoneNumberCheck(String userPhoneNumber) {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher matcher = pattern.matcher(userPhoneNumber);
        if (matcher.find() && matcher.group().equals(userPhoneNumber)) {
            return true;
        } else {
            return false;
        }
    }
}
