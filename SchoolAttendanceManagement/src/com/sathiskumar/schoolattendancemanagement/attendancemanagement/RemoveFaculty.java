package attendancemanagement;

import repository.Database;
import database.DatabaseConnection;
import database.FacultyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveFaculty {
    private String facultyName;
    private String facultyId;

    public void removeFaculty() {
        System.out.println("================================>> REMOVE FACULTY  <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter faculty name:");
            facultyName = scanner.nextLine();
            System.out.println("Enter faculty id:");
            int id = scan.nextInt();
            facultyId = String.valueOf(id);
            removeFacultyDetails();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check details...");
            removeFaculty();
        }
    }

    private void removeFacultyDetails() throws SQLException {
        String deleteDetails = "delete from faculty where username='" + facultyName + "' and id='" + facultyId + "'";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(deleteDetails);
        Database database = Database.getInstance();
        ArrayList<FacultyDatabase> facultyDatabases = database.getFaculty();
        int removeCheck = 0;
        for (int i = 0; i < facultyDatabases.size(); i++) {
            if (facultyName.equals(facultyDatabases.get(i).getFacultyName())) {
                if (facultyId.equals(facultyDatabases.get(i).getFacultyId())) {
                    facultyDatabases.remove(i);
                    removeCheck = 1;
                    break;
                }
            }
        }
        if (removeCheck == 0) {
            System.out.println("Not found student details. Please check details...");
        } else {
            System.out.println("Faculty remove successfully...");
        }
    }
}
