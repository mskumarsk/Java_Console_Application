package attendancemanagement;

import database.AttendanceDatabase;
import repository.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IndividualAttendance {
    private String studentId;

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void individualAttendance() {
        try {
            System.out.println("==================================================>>  INDIVIDUAL ATTENDANCE  <<======" +
                    "==========================================");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter student id:");
            studentId = scanner.nextLine();
            showAttendance();
        } catch (SQLException | InputMismatchException e) {
            System.out.println("Please check details...");
        }
    }

    private void showAttendance() throws SQLException {
        Database database = Database.getInstance();
        ArrayList<AttendanceDatabase> attendanceDatabases = database.getStudentAttendance();
        System.out.print(" ================================================================================" +
                "==========================================================================================" +
                "===============");
        System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                "STUDENT ID") + " | " + centerString(20, "STANDARD") + " | " + centerString(20,
                "SECTION") + " | " + centerString(20, "ATTENDACNE") + " | " + centerString(20,
                "FACULTY NAME") + " | " + centerString(20, "FACULTY ID") + " | " + centerString(20,
                "ATTENDANCE DATE") + " | ");
        System.out.print("\n ==============================================================================" +
                "==========================================================================================" +
                "=================");
        for (int i = 0; i < attendanceDatabases.size(); i++) {
            if (studentId.equals(attendanceDatabases.get(i).getStudentId())) {
                System.out.printf("\n | " + centerString(20, attendanceDatabases.get(i).getStudentName())
                        + " | " + centerString(20, attendanceDatabases.get(i).getStudentId())
                        + " | " + centerString(20, attendanceDatabases.get(i).getStandard())
                        + " | " + centerString(20, attendanceDatabases.get(i).getSection())
                        + " | " + centerString(20, attendanceDatabases.get(i).getAttendance())
                        + " | " + centerString(20, attendanceDatabases.get(i).getFacultyName())
                        + " | " + centerString(20, attendanceDatabases.get(i).getFacultyId())
                        + " | " + centerString(20, attendanceDatabases.get(i).getDate()) + " | ");
            }
        }
        System.out.print("\n =============================================================================" +
                "=========================================================================================" +
                "===================");
        System.out.println();
    }
}
