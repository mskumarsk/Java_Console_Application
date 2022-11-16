package roombooking;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPage {
    public void admin() {
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("=================================== ADMIN PAGE ===================================");
                System.out.println("1) BOOKED ROOMS DETAILS");
                System.out.println("2) CANCEL BOOKING DETAILS");
                System.out.println("3) CONTACT UPLOAD");
                System.out.println("4) EXIT");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new BookedRoomDetails().bookedRoom();
                } else if (option == 2) {
                    new CancelBookingDetails().showCancelBookingDetails();
                } else if (option == 3) {
                    new UpdateContact().updateContact();
                } else if (option == 4) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please check details...");
                isTrue = false;
                admin();
            }
        }
    }
}
