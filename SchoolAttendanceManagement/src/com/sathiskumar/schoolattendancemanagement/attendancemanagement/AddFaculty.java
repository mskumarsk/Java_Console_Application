package attendancemanagement;

import repository.Database;
import database.DatabaseConnection;
import database.FacultyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddFaculty {
    private String name;
    private String phoneNumber;
    private String mail;
    private Database database = Database.getInstance();
    private ArrayList<FacultyDatabase> addFaculty = database.getFaculty();
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void addFaculty() {
        try {
            System.out.println("============================= ADD FACULTY =============================");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter faculty name:");
            name = scanner.nextLine();
            Scanner scan = new Scanner(System.in);
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("Enter the phone number:");
                long phone = scan.nextLong();
                phoneNumber = String.valueOf(phone);
                boolean numberCheck = phoneNumberCheck();
                if (numberCheck) {
                    isTrue = false;
                } else {
                    System.out.println("Invalid Phone number...");
                }
            }
            isTrue = true;
            while (isTrue) {
                System.out.println("Enter mail:");
                mail = scanner.nextLine();
                boolean mailCheck = mailCheck();
                if (mailCheck) {
                    isTrue = false;
                } else {
                    System.out.println("Invalid mail id...");
                }
            }
            System.out.println("1) OK");
            System.out.println("2) CANCEL");
            System.out.println("Enter the number:");
            int choice = scan.nextInt();
            if (choice == 1) {
                insertFaculty();
            } else if (choice == 2) {
                addFaculty();
            }
        } catch (SQLException e) {
            System.out.println("Please check details...");
            addFaculty();
        } catch (InputMismatchException e) {
            System.out.println("Please enter number...");
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scannerOne = new Scanner(System.in);
        System.out.println("1) ADD FACULTY");
        System.out.println("2) CANCEL");
        System.out.println("Enter the number:");
        int option = scannerOne.nextInt();
        if (option == 1) {
            addFaculty();
        }
    }

    private void insertFaculty() throws SQLException {
        String insertFaculty = "insert into faculty (username,phone,mail) values ('" + name + "'," +
                "'" + phoneNumber + "','" + mail + "');";
        databaseConnection.executeQuery(insertFaculty);
        System.out.println("Faculty add successfully...");
        addFaculty.add(new FacultyDatabase());
        addFaculty.get(addFaculty.size() - 1).setFacultyName(name);
        addFaculty.get(addFaculty.size() - 1).setPhoneNumber(phoneNumber);
        addFaculty.get(addFaculty.size() - 1).setFacultyMail(mail);
        getFacultyDetails();
    }

    private void getFacultyDetails() throws SQLException {
        String getFacultyDetails = "select * from faculty where username='" + name + "' and phone='" + phoneNumber + "' " +
                "and mail='" + mail + "'";
        ResultSet resultSet = databaseConnection.getDataQuery(getFacultyDetails);
        if (resultSet.next()) {
            addFaculty.get(addFaculty.size() - 1).setFacultyId(resultSet.getString("id"));
            System.out.println("============================ FACULTY DETAILS ============================");
            System.out.println("FACULTY NAME               :  " + resultSet.getString("username"));
            System.out.println("FACULTY PHONE NUMBER       :  " + resultSet.getString("phone"));
            System.out.println("FACULTY MAIL               :  " + resultSet.getString("mail"));
            System.out.println("FACULTY ID                 :  " + resultSet.getString("id"));
        }
    }

    private boolean phoneNumberCheck() {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find() && matcher.group().equals(phoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean mailCheck() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(mail);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
