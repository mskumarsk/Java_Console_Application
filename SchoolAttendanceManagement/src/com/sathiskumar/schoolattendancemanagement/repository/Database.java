package repository;

import database.AttendanceDatabase;
import database.DatabaseConnection;
import database.FacultyDatabase;
import database.StudentDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    public static Database database = new Database();
    private ArrayList<FacultyDatabase> faculty;
    private ArrayList<StudentDatabase> student;
    private ArrayList<AttendanceDatabase> studentAttendance;

    private Database() {
        faculty = new ArrayList<FacultyDatabase>();
        student = new ArrayList<StudentDatabase>();
        studentAttendance = new ArrayList<AttendanceDatabase>();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void getFacultyDetails() throws SQLException {
        String getFacultyDetails = "select * from faculty";
        ResultSet resultSet = new DatabaseConnection().getDataQuery(getFacultyDetails);
        int i = 0;
        while (resultSet.next()) {
            faculty.add(new FacultyDatabase());
            faculty.get(i).setFacultyId(resultSet.getString("id"));
            faculty.get(i).setFacultyName(resultSet.getString("username"));
            faculty.get(i).setFacultyMail(resultSet.getString("phone"));
            faculty.get(i).setPhoneNumber(resultSet.getString("mail"));
            i++;
        }
        getStudentDetails();
    }

    public void getStudentDetails() throws SQLException {
        String getStudentDetails = "select * from student";
        ResultSet resultSet = new DatabaseConnection().getDataQuery(getStudentDetails);
        int i = 0;
        while (resultSet.next()) {
            student.add(new StudentDatabase());
            student.get(i).setStudentId(resultSet.getString("studentid"));
            student.get(i).setStudentName(resultSet.getString("studentname"));
            student.get(i).setStandard(resultSet.getString("standard"));
            student.get(i).setSection(resultSet.getString("section"));
            student.get(i).setAbsent(resultSet.getString("absent"));
            student.get(i).setPresent(resultSet.getString("present"));
            student.get(i).setDate(resultSet.getString("attendance"));
            i++;
        }
        getStudentsAttendance();
    }

    public void getStudentsAttendance() throws SQLException {
        String getAttendanceDetails = "select * from studentattendance";
        ResultSet resultSet = new DatabaseConnection().getDataQuery(getAttendanceDetails);
        int i = 0;
        while (resultSet.next()) {
            studentAttendance.add(new AttendanceDatabase());
            studentAttendance.get(i).setStudentId(resultSet.getString("studentid"));
            studentAttendance.get(i).setStudentName(resultSet.getString("studentname"));
            studentAttendance.get(i).setStandard(resultSet.getString("standard"));
            studentAttendance.get(i).setSection(resultSet.getString("section"));
            studentAttendance.get(i).setAttendance(resultSet.getString("attendance"));
            studentAttendance.get(i).setFacultyName(resultSet.getString("facultyname"));
            studentAttendance.get(i).setFacultyId(resultSet.getString("facultyid"));
            studentAttendance.get(i).setDate(resultSet.getString("attendancedate"));
            i++;
        }
    }

    public ArrayList<FacultyDatabase> getFaculty() {
        return faculty;
    }

    public void setFaculty(ArrayList<FacultyDatabase> faculty) {
        this.faculty = faculty;
    }

    public ArrayList<StudentDatabase> getStudent() {
        return student;
    }

    public void setStudent(ArrayList<StudentDatabase> student) {
        this.student = student;
    }

    public void setStudentAttendance(ArrayList<AttendanceDatabase> studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    public ArrayList<AttendanceDatabase> getStudentAttendance() {
        return studentAttendance;
    }
}
