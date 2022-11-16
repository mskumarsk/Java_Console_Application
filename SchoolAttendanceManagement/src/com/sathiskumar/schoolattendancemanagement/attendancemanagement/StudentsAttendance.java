package attendancemanagement;

import repository.Database;
import database.StudentDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentsAttendance {
    private String standard;
    private String section;
    private Database database = Database.getInstance();
    private ArrayList<StudentDatabase> studentDatabase = database.getStudent();

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void studentAttendance() {
        try {
            System.out.println("==================================================>>  ATTENDANCE  <<=====" +
                    "===========================================");
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter standard:");
            int getStandard = scan.nextInt();
            standard = String.valueOf(getStandard);
            System.out.println("Enter section:");
            section = scanner.nextLine();
            showStudentAttendance();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check details...");
        }
    }

    private void showStudentAttendance() throws SQLException {
        System.out.print(" ===============================================================================" +
                "===================================================================================");
        System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                "STUDENT ID") + " | " + centerString(20, "STANDARD") + " | " + centerString(20,
                "SECTION") + " | " + centerString(20, "ABSENT") + " | " + centerString(20,
                "PRESENT") + " | " + centerString(20, "ATTENDANCE DATE") + " | ");
        System.out.print("\n =============================================================================" +
                "=====================================================================================");
        for (int i = 0; i < studentDatabase.size(); i++) {
            if (standard.equals(studentDatabase.get(i).getStandard())) {
                if (section.equals(studentDatabase.get(i).getSection())) {
                    if (!"LEFT".equals(studentDatabase.get(i).getAbsent())) {
                        System.out.printf("\n | " + centerString(20, studentDatabase.get(i).getStudentName())
                                + " | " + centerString(20, studentDatabase.get(i).getStudentId())
                                + " | " + centerString(20, studentDatabase.get(i).getStandard())
                                + " | " + centerString(20, studentDatabase.get(i).getSection())
                                + " | " + centerString(20, studentDatabase.get(i).getAbsent())
                                + " | " + centerString(20, studentDatabase.get(i).getPresent())
                                + " | " + centerString(20, studentDatabase.get(i).getDate()) + " | ");
                    }
                }
            }
        }
        System.out.print("\n ============================================================================" +
                "======================================================================================");
        System.out.println();
    }

    public void showRemoveStudents() {
        System.out.println("==================================================>>  REMOVE STUDENT DETAILS  <<=====" +
                "===========================================");
        System.out.print(" ===============================================================================" +
                "===================================================================================");
        System.out.printf("\n | " + centerString(20, "NAME") + " | " + centerString(20,
                "STUDENT ID") + " | " + centerString(20, "STANDARD") + " | " + centerString(20,
                "SECTION") + " | " + centerString(20, "ABSENT") + " | " + centerString(20,
                "PRESENT") + " | " + centerString(20, "ATTENDANCE DATE") + " | ");
        System.out.print("\n =============================================================================" +
                "=====================================================================================");
        for (int i = 0; i < studentDatabase.size(); i++) {
            if ("LEFT".equals(studentDatabase.get(i).getAbsent())) {
                System.out.printf("\n | " + centerString(20, studentDatabase.get(i).getStudentName())
                        + " | " + centerString(20, studentDatabase.get(i).getStudentId())
                        + " | " + centerString(20, studentDatabase.get(i).getStandard())
                        + " | " + centerString(20, studentDatabase.get(i).getSection())
                        + " | " + centerString(20, studentDatabase.get(i).getAbsent())
                        + " | " + centerString(20, studentDatabase.get(i).getPresent())
                        + " | " + centerString(20, studentDatabase.get(i).getDate()) + " | ");
            }
        }
        System.out.print("\n ============================================================================" +
                "======================================================================================");
        System.out.println();
    }
}
