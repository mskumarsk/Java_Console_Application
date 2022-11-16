package roombooking;

import database.ContactDatabase;
import repository.Database;
import database.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateContact {
    private String hotelName;
    private String phoneNumber;
    private String acRooms;
    private String acRoomAmount;
    private String rooms;
    private String roomAmount;
    private String address;


    public void updateContact() {
        try {
            System.out.println("================================>> UPDATE CONTACT DETAILS <<=====================" +
                    "===========");
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter hotel name:");
            hotelName = scanner.nextLine();
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("Enter your phone number:");
                long phoneNo = scan.nextLong();
                phoneNumber = String.valueOf(phoneNo);
                boolean numberCheck = phoneNumberCheck();
                if (numberCheck) {
                    isTrue = false;
                } else {
                    System.out.println("Invalid Phone Number...");
                }
            }
            System.out.println("Enter A/C rooms:");
            int acRoom = scan.nextInt();
            acRooms = String.valueOf(acRoom);
            System.out.println("Enter A/C rooms amount:");
            int getAcRoomAmount = scan.nextInt();
            acRoomAmount = String.valueOf(getAcRoomAmount);
            System.out.println("Enter Non A/C rooms:");
            int room = scan.nextInt();
            rooms = String.valueOf(room);
            System.out.println("Enter Non A/C rooms amount:");
            int getRoomAmount = scan.nextInt();
            roomAmount = String.valueOf(getRoomAmount);
            System.out.println("Enter address:");
            address = scanner.nextLine();
            updateHotelContact();
            System.out.println("Contact update successfully...");
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your details...");
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) UPDATE CONTACT");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            updateContact();
        }
    }

    private void updateHotelContact() throws SQLException {
        int id = 1;
        String updateContact = "update contactdetails set hotelname='" + hotelName + "',phone='" + phoneNumber + "'," +
                "acrooms='" + acRooms + "',rooms='" + rooms + "',acroomsamount='" + acRoomAmount + "'," +
                "roomsamount='" + roomAmount + "',address='" + address + "' where id='" + id + "'";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(updateContact);
        Database database = Database.getInstance();
        ArrayList<ContactDatabase> contactDatabases = database.getContactDatabases();
        contactDatabases.get(0).setName(hotelName);
        contactDatabases.get(0).setPhoneNumber(phoneNumber);
        contactDatabases.get(0).setAcRooms(acRooms);
        contactDatabases.get(0).setNonAcRooms(rooms);
        contactDatabases.get(0).setAcRoomAmount(acRoomAmount);
        contactDatabases.get(0).setNonAcRoomAmount(roomAmount);
        contactDatabases.get(0).setAddress(address);
    }

    private boolean phoneNumberCheck() {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find() && matcher.group().equals(phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }
}
