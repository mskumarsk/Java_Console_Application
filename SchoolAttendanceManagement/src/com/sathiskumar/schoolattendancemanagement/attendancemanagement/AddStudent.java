package attendancemanagement;

import repository.Database;
import database.DatabaseConnection;
import database.StudentDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddStudent {
    private String studentId;
    private String studentName;
    private String standard;
    private String section;
    private Database database = Database.getInstance();
    private ArrayList<StudentDatabase> addStudent = database.getStudent();
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public void addStudent() {
        try {
            System.out.println("============================= ADD STUDENT =============================");
            Scanner scan = new Scanner(System.in);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter student name:");
            studentName = scanner.nextLine();
            System.out.println("Enter standard:");
            int getStandard = scan.nextInt();
            standard = String.valueOf(getStandard);
            System.out.println("Enter section:");
            section = scanner.nextLine();
            System.out.println("1) OK");
            System.out.println("2) CANCEL");
            System.out.println("Enter the number:");
            int number = scan.nextInt();
            if (number == 1) {
                getID();
                insertStudentDetails();
                System.out.println("1) ADD STUDENT");
                System.out.println("2) CANCEL");
                System.out.println("Enter the number:");
                int option = scan.nextInt();
                if (option == 1) {
                    addStudent();
                }
            } else if (number == 2) {
                addStudent();
            }
        } catch (SQLException | InputMismatchException e) {
            System.out.println("Please check details...");
            callMethod();
        }

    }

    private void callMethod() {
        Scanner scannerOne = new Scanner(System.in);
        System.out.println("1) ADD STUDENT");
        System.out.println("2) CANCEL");
        System.out.println("Enter the number:");
        int option = scannerOne.nextInt();
        if (option == 1) {
            addStudent();
        }
    }

    private void getID() {
        int count = 0;
        for (int i = 0; i < addStudent.size(); i++) {
            if (standard.equals(addStudent.get(i).getStandard()) || !standard.equals(addStudent.get(i).getStandard() + "(LEFT)")) {
                if (section.equals(addStudent.get(i).getSection())) {
                    count++;
                }
            }
        }
        if (count == 0) {
            studentId = standard + "" + section + "" + "1";
        } else {
            String id = String.valueOf(count + 1);
            studentId = standard + "" + section + "" + id;
        }
    }

    private void insertStudentDetails() throws SQLException {
        String value = "0";
        String insertStudent = "insert into student (studentname,studentid,standard,section,absent," +
                "present) values ('" + studentName + "','" + studentId + "','" + standard + "'," +
                "'" + section + "','" + value + "','" + value + "');";
        databaseConnection.executeQuery(insertStudent);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(dateTimeFormatter);
        addStudent.add(new StudentDatabase());
        addStudent.get(addStudent.size() - 1).setStudentName(studentName);
        addStudent.get(addStudent.size() - 1).setStudentId(studentId);
        addStudent.get(addStudent.size() - 1).setStandard(standard);
        addStudent.get(addStudent.size() - 1).setSection(section);
        addStudent.get(addStudent.size() - 1).setAbsent(value);
        addStudent.get(addStudent.size() - 1).setPresent(value);
        addStudent.get(addStudent.size() - 1).setDate(date);
        System.out.println("Student add successfully...");
        showStudentDetails();
    }

    private void showStudentDetails() {
        System.out.println("============================ STUDENT DETAILS ============================");
        System.out.println("STUDENT NAME               :  " + studentName);
        System.out.println("STUDENT ID                 :  " + studentId);
        System.out.println("STUDENT STANDARD           :  " + standard);
        System.out.println("STUDENT SECTION            :  " + section);
        System.out.println("=========================================================================");
    }
}
