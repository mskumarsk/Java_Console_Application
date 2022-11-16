package roombooking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserPage {
    public void userPage() {
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("================================>> USER PAGE  <<================================");
                System.out.println("1) ROOM BOOK");
                System.out.println("2) ROOM CANCEL");
                System.out.println("3) BOOKED ROOM DETAILS");
                System.out.println("4) CONTACT DETAILS");
                System.out.println("5) EXIT");
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new RoomBooking().roomBooking();
                } else if (option == 2) {
                    new RoomCancel().roomCancel();
                } else if (option == 3) {
                    new BookedDatails().details();
                } else if (option == 4) {
                    new ContactDetails().contact();
                } else if (option == 5) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                userPage();
            }
        }
    }
}
