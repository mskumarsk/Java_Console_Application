package attendancemanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminPage {
    public void adminPage() {
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("=================================== ADMIN PAGE ===================================");
                System.out.println("1) FACULTY MANAGEMENT");
                System.out.println("2) VIEW ATTENDANCE");
                System.out.println("3) REMOVE STUDENTS LIST");
                System.out.println("4) EXIT");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new Faculty().faculty();
                } else if (option == 2) {
                    new Attendance().attendance();
                } else if (option == 3) {
                    new StudentsAttendance().showRemoveStudents();
                } else if (option == 4) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                adminPage();
            }
        }
    }
}
