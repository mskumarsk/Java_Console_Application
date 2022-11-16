package roombooking;

import database.ContactDatabase;
import repository.Database;
import database.DatabaseConnection;
import database.RoomBookingDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomBooking {
    private String userName;
    private String phoneNumber;
    private String mailId;
    private String rooms;
    private String days;
    private String date;
    private String totalAmount;
    private String roomsType;
    private Database database = Database.getInstance();

    public void roomBooking() {
        Scanner scanner = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("========================================== ROOM BOOKING ===========================" +
                        "===============");
                System.out.println("Enter your name:");
                userName = scanner.nextLine();
                boolean checkTrue = true;
                while (checkTrue) {
                    System.out.println("Enter the phone number:");
                    long phone = scan.nextLong();
                    phoneNumber = String.valueOf(phone);
                    boolean numberCheck = phoneNumberCheck();
                    if (numberCheck) {
                        checkTrue = false;
                    } else {
                        System.out.println("Invalid Phone Number...");
                    }
                }
                checkTrue = true;
                while (checkTrue) {
                    System.out.println("Enter your mail id:");
                    mailId = scanner.nextLine();
                    boolean mailCheck = mailCheck();
                    if (mailCheck) {
                        checkTrue = false;
                    } else {
                        System.out.println("Invalid mail...");
                    }
                }
                System.out.println("Enter rooms:");
                int roomCount = scan.nextInt();
                rooms = String.valueOf(roomCount);
                ArrayList<ContactDatabase> contactDatabases = database.getContactDatabases();
                System.out.println("AMOUNT  :");
                System.out.println("** A/C ROOM       :  " + contactDatabases.get(0).getAcRoomAmount());
                System.out.println("** NON A/C ROOM   :  " + contactDatabases.get(0).getNonAcRoomAmount());
                System.out.println("Room type:");
                System.out.println("1) A/C Room");
                System.out.println("2) Non A/C Room");
                System.out.println("Enter the number:");
                int roomType = scan.nextInt();
                int amount = 0;
                if (roomType == 1) {
                    roomsType = "A/C Room";
                    amount = Integer.parseInt(contactDatabases.get(0).getAcRoomAmount());
                } else if (roomType == 2) {
                    roomsType = "Non A/C Room";
                    amount = Integer.parseInt(contactDatabases.get(0).getNonAcRoomAmount());
                }
                System.out.println("How many days? :");
                int day = scan.nextInt();
                days = String.valueOf(day);
                System.out.println("Coming date:");
                date = scanner.nextLine();
                int totalDay = day * roomCount;
                int totalAmounts = amount * totalDay;
                totalAmount = String.valueOf(totalAmounts);
                showRoomDetails();
                System.out.println("1) OK");
                System.out.println("2) CANCEL");
                System.out.println("Enter the number:");
                int option = scan.nextInt();
                if (option == 1) {
                    insertBookingData();
                    isTrue = false;
                } else if (option == 2) {
                    isTrue = false;
                    roomBooking();
                }
            } catch (SQLException e) {
                System.out.println("Please check your details...");
                isTrue = false;
            } catch (InputMismatchException e) {
                System.out.println("Please check your phone number...");
                isTrue = false;
                callMethod();
            }
        }
    }

    private void callMethod() {
        Scanner scannerOne = new Scanner(System.in);
        System.out.println("1) ROOM BOOKING");
        System.out.println("2) CANCEL");
        System.out.println("Enter the number:");
        int option = scannerOne.nextInt();
        if (option == 1) {
            roomBooking();
        }
    }

    private void insertBookingData() throws SQLException {
        String insertData = "insert into bookingdetails (username,phone,mail,rooms,roomtype,days,startdate," +
                "amount) values ('" + userName + "','" + phoneNumber + "','" + mailId + "','" + rooms + "','" + roomsType + "','" + days + "'," +
                "'" + date + "','" + totalAmount + "');";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(insertData);
        LocalDateTime localDateTime = LocalDateTime.now();
        String currentDate = localDateTime.toString();
        ArrayList<RoomBookingDatabase> bookingDatabases = database.getBookingDatabases();
        bookingDatabases.add(new RoomBookingDatabase());
        bookingDatabases.get(bookingDatabases.size() - 1).setUserName(userName);
        bookingDatabases.get(bookingDatabases.size() - 1).setPhoneNumber(phoneNumber);
        bookingDatabases.get(bookingDatabases.size() - 1).setMail(mailId);
        bookingDatabases.get(bookingDatabases.size() - 1).setRooms(rooms);
        bookingDatabases.get(bookingDatabases.size() - 1).setRoomType(roomsType);
        bookingDatabases.get(bookingDatabases.size() - 1).setDays(days);
        bookingDatabases.get(bookingDatabases.size() - 1).setStartDate(date);
        bookingDatabases.get(bookingDatabases.size() - 1).setAmount(totalAmount);
        bookingDatabases.get(bookingDatabases.size() - 1).setBookingDate(currentDate);
        System.out.println("Room booking successfully...");
    }

    private void showRoomDetails() {
        System.out.println("============================  DETAILS  ============================");
        System.out.println("ROOM          :  " + rooms);
        System.out.println("DAYS          :  " + days);
        System.out.println("DATE          :  " + date);
        System.out.println("AMOUNT        :  " + totalAmount);
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

    private boolean mailCheck() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(mailId);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

}
