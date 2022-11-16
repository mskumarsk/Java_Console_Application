package attendancemanagement;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Attendance {

    public void attendance() {
        try {
            System.out.println("=================================== ATTENDANCE ===================================");
            System.out.println("1) STUDENT INDIVIDUAL ATTENDANCE");
            System.out.println("2) VIEW ATTENDANCE");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number:");
            int number = scanner.nextInt();
            if (number == 1) {
                new IndividualAttendance().individualAttendance();
            } else if (number == 2) {
                new StudentsAttendance().studentAttendance();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter number...");
            attendance();
        }
    }
}
