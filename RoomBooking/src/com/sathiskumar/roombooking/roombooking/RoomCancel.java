package roombooking;

import database.CancelBookingData;
import repository.Database;
import database.DatabaseConnection;
import database.RoomBookingDatabase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomCancel {
    private String userName;
    private String phoneNumber;
    private Database database = Database.getInstance();

    public void roomCancel() {
        System.out.println("================================>> ROOM CANCEL  <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your name:");
            userName = scanner.nextLine();
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
            getBookingData();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your name and phone number...");
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) ROOM CANCEL");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            roomCancel();
        }
    }

    private void deleteDetails() throws SQLException {
        String deleteData = "delete from bookingdetails where username='" + userName + "' and phone='" + phoneNumber + "'";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(deleteData);
    }

    private void getBookingData() throws SQLException {
        ArrayList<RoomBookingDatabase> bookingDatabases = database.getBookingDatabases();
        ArrayList<CancelBookingData> cancelBookingData = database.getCancelBookingData();
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.toString();
        boolean checkDetails = true;
        for (int i = 0; i < bookingDatabases.size(); i++) {
            if (userName.equals(bookingDatabases.get(i).getUserName())) {
                if (phoneNumber.equals(bookingDatabases.get(i).getPhoneNumber())) {
                    cancelBookingData.add(new CancelBookingData());
                    cancelBookingData.get(cancelBookingData.size() - 1).setUserName(bookingDatabases.get(i).getUserName());
                    cancelBookingData.get(cancelBookingData.size() - 1).setPhoneNumber(bookingDatabases.get(i).getPhoneNumber());
                    cancelBookingData.get(cancelBookingData.size() - 1).setMail(bookingDatabases.get(i).getMail());
                    cancelBookingData.get(cancelBookingData.size() - 1).setRooms(bookingDatabases.get(i).getRooms());
                    cancelBookingData.get(cancelBookingData.size() - 1).setRoomType(bookingDatabases.get(i).getRoomType());
                    cancelBookingData.get(cancelBookingData.size() - 1).setDays(bookingDatabases.get(i).getDays());
                    cancelBookingData.get(cancelBookingData.size() - 1).setStartDate(bookingDatabases.get(i).getStartDate());
                    cancelBookingData.get(cancelBookingData.size() - 1).setAmount(bookingDatabases.get(i).getAmount());
                    cancelBookingData.get(cancelBookingData.size() - 1).setBookingDate(bookingDatabases.get(i).getBookingDate());
                    cancelBookingData.get(cancelBookingData.size() - 1).setCancelDate(date);
                    String insertData = "insert into cancelbookingdetails (username,phone,mail,rooms,roomtype,days,startdate," +
                            "amount,bookingdate) values ('" + bookingDatabases.get(i).getUserName() + "'," +
                            "'" + bookingDatabases.get(i).getPhoneNumber() + "'," +
                            "'" + bookingDatabases.get(i).getMail() + "'," +
                            "'" + bookingDatabases.get(i).getRooms() + "'," +
                            "'" + bookingDatabases.get(i).getRoomType() + "'," +
                            "'" + bookingDatabases.get(i).getDays() + "'," +
                            "'" + bookingDatabases.get(i).getStartDate() + "'," +
                            "'" + bookingDatabases.get(i).getAmount() + "'," +
                            "'" + bookingDatabases.get(i).getBookingDate() + "');";
                    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
                    databaseConnection.executeQuery(insertData);
                    bookingDatabases.remove(i);
                    deleteDetails();
                    checkDetails = false;
                    break;
                }
            }
        }
        if (checkDetails) {
            System.out.println("Please check name and phone number...");
        } else {
            System.out.println("Room cancel successfully...");
        }
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
