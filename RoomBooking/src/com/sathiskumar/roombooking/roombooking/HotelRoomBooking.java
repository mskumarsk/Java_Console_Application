package roombooking;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.AdminLoginData;
import repository.Database;

public class HotelRoomBooking {
    public static void main(String[] args) throws SQLException {
        HotelRoomBooking hotelRoomBooking = new HotelRoomBooking();
        hotelRoomBooking.hotelRoomBooking();
    }

    public void hotelRoomBooking() throws SQLException {
        Database database = Database.getInstance();
        database.getContactDetails();
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("=========================================>> HOTEL ROOM BOOKING <<=======================" +
                    "==================");
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1) ADMIN");
                System.out.println("2) USER");
                System.out.println("3) EXIT");
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    adminLogin();
                } else if (option == 2) {
                    new UserPage().userPage();
                } else if (option == 3) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                hotelRoomBooking();
            }
        }
    }

    private void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        AdminLoginData adminLoginData = new AdminLoginData();
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("================================>> ADMIN LOGIN <<======================" +
                    "==========");
            System.out.println("Enter the admin name:");
            String adminName = scanner.nextLine();
            if (adminName.equals(adminLoginData.getAdminName())) {
                System.out.println("Enter the password:");
                String adminPassword = scanner.nextLine();
                if (adminPassword.equals(adminLoginData.getAdminPassword())) {
                    new AdminPage().admin();
                    isTrue = false;
                } else {
                    System.out.println("Incorrect password...");
                }
            } else {
                System.out.println("Admin name is wrong...");
            }
        }
    }
}
