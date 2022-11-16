package attendancemanagement;

import database.AttendanceDatabase;
import repository.Database;
import database.DatabaseConnection;
import database.StudentDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentAttendance {
    private String facultyName;
    private String facultyId;
    private String standard;
    private String section;
    private String studentName;
    private String studentId;
    private Database database = Database.getInstance();
    private ArrayList<StudentDatabase> studentDatabases = database.getStudent();
    private ArrayList<AttendanceDatabase> attendanceDatabases = database.getStudentAttendance();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDateTime localDateTime = LocalDateTime.now();
    private String date = localDateTime.format(dateTimeFormatter);
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();


    public void studentAttendance(String facultyName, String facultyId) {
        try {
            System.out.println("=================================== ATTENDANCE ===================================");
            Scanner scanner = new Scanner(System.in);
            Scanner scan = new Scanner(System.in);
            this.facultyName = facultyName;
            this.facultyId = facultyId;
            System.out.println("Enter standard:");
            int getStandard = scan.nextInt();
            standard = String.valueOf(getStandard);
            System.out.println("Enter section:");
            section = scanner.nextLine();
            showStudentDetails();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check details...");
            callMethod();
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) ATTENDANCE");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            studentAttendance(facultyName, facultyId);
        }
    }

    private void showStudentDetails() throws SQLException, InputMismatchException {
        for (int i = 0; i < studentDatabases.size(); i++) {
            if (standard.equals(studentDatabases.get(i).getStandard())) {
                if (section.equals(studentDatabases.get(i).getSection())) {
                    if (!"LEFT".equals(studentDatabases.get(i).getAbsent())) {
                        studentName = studentDatabases.get(i).getStudentName();
                        studentId = studentDatabases.get(i).getStudentId();
                        System.out.println("=======================  STUDENT DETAILS =======================");
                        System.out.println("STUDENT NAME                  :  " + studentName);
                        System.out.println("STUDENT ID                    :  " + studentId);
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("1) PRESENT");
                        System.out.println("2) ABSENT");
                        System.out.println("Enter the number:");
                        int num = scanner.nextInt();
                        if (num == 1) {
                            insertPresent();
                        } else if (num == 2) {
                            insertAbsent();
                        }
                    }
                }
            }
        }
    }

    private void insertPresent() throws SQLException {
        String present = "PRESENT";
        String insertAttendance = "insert into studentattendance (studentname,studentid,standard,section," +
                "attendance,facultyname,facultyid) values ('" + studentName + "','" + studentId + "','" + standard + "'," +
                "'" + section + "','" + present + "','" + facultyName + "','" + facultyId + "')";
        databaseConnection.executeQuery(insertAttendance);
        attendanceDatabases.add(new AttendanceDatabase());
        attendanceDatabases.get(attendanceDatabases.size() - 1).setStudentName(studentName);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setStudentId(studentId);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setStandard(standard);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setSection(section);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setAttendance(present);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setFacultyName(facultyName);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setFacultyId(facultyId);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setDate(date);
        getStudentPresentAttendance();
    }

    private void getStudentPresentAttendance() throws SQLException {
        String attendance = "";
        for (int i = 0; i < studentDatabases.size(); i++) {
            if (studentName.equals(studentDatabases.get(i).getStudentName())) {
                if (studentId.equals(studentDatabases.get(i).getStudentId())) {
                    attendance = studentDatabases.get(i).getPresent();
                    break;
                }
            }
        }
        int totalAttendance = 1 + Integer.parseInt(attendance);
        String present = String.valueOf(totalAttendance);
        String updateAttendance = "update student set present='" + present + "' where " +
                "studentname='" + studentName + "' and studentid='" + studentId + "'";
        databaseConnection.executeQuery(updateAttendance);
        for (int i = 0; i < studentDatabases.size(); i++) {
            if (studentName.equals(studentDatabases.get(i).getStudentName())) {
                if (studentId.equals(studentDatabases.get(i).getStudentId())) {
                    studentDatabases.get(i).setPresent(present);
                    break;
                }
            }
        }
        System.out.println("Successfully...");
    }

    private void insertAbsent() throws SQLException {
        String absent = "ABSENT";
        String insertAttendance = "insert into studentattendance (studentname,studentid,standard,section," +
                "attendance,facultyname,facultyid) values ('" + studentName + "','" + studentId + "','" + standard + "'," +
                "'" + section + "','" + absent + "','" + facultyName + "','" + facultyId + "')";
        databaseConnection.executeQuery(insertAttendance);
        attendanceDatabases.add(new AttendanceDatabase());
        attendanceDatabases.get(attendanceDatabases.size() - 1).setStudentName(studentName);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setStudentId(studentId);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setStandard(standard);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setSection(section);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setAttendance(absent);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setFacultyName(facultyName);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setFacultyId(facultyId);
        attendanceDatabases.get(attendanceDatabases.size() - 1).setDate(date);
        getStudentAbsentAttendance();
    }

    private void getStudentAbsentAttendance() throws SQLException {
        String attendance = "";
        for (int i = 0; i < studentDatabases.size(); i++) {
            if (studentName.equals(studentDatabases.get(i).getStudentName())) {
                if (studentId.equals(studentDatabases.get(i).getStudentId())) {
                    attendance = studentDatabases.get(i).getAbsent();
                    break;
                }
            }
        }
        int totalAttendance = 1 + Integer.parseInt(attendance);
        String absent = String.valueOf(totalAttendance);
        String updateAttendance = "update student set absent='" + absent + "' where " +
                "studentname='" + studentName + "' and studentid='" + studentId + "'";
        databaseConnection.equals(updateAttendance);
        for (int i = 0; i < studentDatabases.size(); i++) {
            if (studentName.equals(studentDatabases.get(i).getStudentName())) {
                if (studentId.equals(studentDatabases.get(i).getStudentId())) {
                    studentDatabases.get(i).setAbsent(absent);
                    break;
                }
            }
        }
        System.out.println("Successfully...");
    }
}
