package attendancemanagement;

import repository.Database;
import database.FacultyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FacultyLogin {
    private String facultyName;
    private String facultyId;

    public void facultyLogin() {
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("=================================== FACULTY LOGIN ===================================");
                Scanner scanner = new Scanner(System.in);
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the faculty name:");
                facultyName = scanner.nextLine();
                System.out.println("Enter the faculty id:");
                int id = scan.nextInt();
                facultyId = String.valueOf(id);
                checkFacultyDetails();
                isTrue = false;
            } catch (InputMismatchException | SQLException e) {
                isTrue = false;
                System.out.println("Please check your details...");
                facultyLogin();
            }
        }
    }

    private void checkFacultyDetails() throws SQLException {
        Database database = Database.getInstance();
        ArrayList<FacultyDatabase> getFacultyDetails = database.getFaculty();
        int checkId = 0;
        for (int i = 0; i < getFacultyDetails.size(); i++) {
            if (facultyId.equals(getFacultyDetails.get(i).getFacultyId())) {
                if (facultyName.equals(getFacultyDetails.get(i).getFacultyName())) {
                    System.out.println("Faculty login successfully...");
                    new ClassDetails().classDetails(facultyName, facultyId);
                    checkId = 1;
                    break;
                }
            }
        }
        if (checkId == 0) {
            System.out.println("Please check your name or id...");
        }
    }
}
