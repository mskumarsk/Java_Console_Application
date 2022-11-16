package attendancemanagement;

import repository.Database;
import database.DatabaseConnection;
import database.StudentDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveStudent {
    private String studentId;

    public void removeStudent() {
        System.out.println("================================>> REMOVE STUDENT  <<================================");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter student id:");
            studentId = scanner.nextLine();
            removeStudentDetails();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check details...");
            e.printStackTrace();
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) REMOVE STUDENT");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            removeStudent();
        }
    }


    private void removeStudentDetails() throws SQLException {
        String left = "LEFT";
        String updateDetails = "update student set absent='" + left + "',present='" + left + "' where studentid='" + studentId + "'";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        databaseConnection.executeQuery(updateDetails);
        Database database = Database.getInstance();
        ArrayList<StudentDatabase> studentDatabases = database.getStudent();
        int removeCheck = 0;
        for (int i = 0; i < studentDatabases.size(); i++) {
            if (studentId.equals(studentDatabases.get(i).getStudentId())) {
                studentDatabases.get(i).setAbsent(left);
                studentDatabases.get(i).setPresent(left);
                removeCheck = 1;
                break;
            }
        }
        if (removeCheck == 0) {
            System.out.println("NOt found faculty details. Please check details...");
        } else {
            System.out.println("Student remove successfully...");
        }
    }
}
