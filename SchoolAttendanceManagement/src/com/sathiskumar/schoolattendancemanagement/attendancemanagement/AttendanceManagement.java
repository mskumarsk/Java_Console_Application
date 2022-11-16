package attendancemanagement;

import repository.Database;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AttendanceManagement extends AdminDetails {
    public static void main(String[] args) throws SQLException {
        AttendanceManagement attendanceManagement = new AttendanceManagement();
        attendanceManagement.attendanceManagement();
    }

    public void attendanceManagement() throws SQLException {
        Database database = Database.getInstance();
        database.getFacultyDetails();
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("=========================================>> SCHOOL ATTENDANCE MANAGEMENT <<===========" +
                    "==============================");
            try {
                System.out.println("1) ADMIN");
                System.out.println("2) FACULTY");
                System.out.println("3) EXIT");
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    adminLogin();
                } else if (option == 2) {
                    new FacultyLogin().facultyLogin();
                } else if (option == 3) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                attendanceManagement();
            }
        }
    }

    private void adminLogin() {
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("================================>> ADMIN PAGE  <<======================" +
                    "==========");
            System.out.println("================================>> LOGIN PAGE  <<======================" +
                    "==========");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the admin name:");
            String adminName = scanner.nextLine();
            if (adminName.equals(getAdminName())) {
                System.out.println("Enter the password:");
                String adminPassword = scanner.nextLine();
                if (adminPassword.equals(getAdminPassword())) {
                    new AdminPage().adminPage();
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
