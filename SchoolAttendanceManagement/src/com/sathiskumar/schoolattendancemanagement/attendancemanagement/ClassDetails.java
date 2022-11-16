package attendancemanagement;


import java.util.InputMismatchException;
import java.util.Scanner;

public class ClassDetails {

    public void classDetails(String facultyName, String facultyId) {
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("=================================== CLASS DETAILS ==============================" +
                        "=====");
                System.out.println("1) ADD STUDENT");
                System.out.println("2) REMOVE STUDENT");
                System.out.println("3) ATTENDANCE");
                System.out.println("4) VIEW ATTENDANCE");
                System.out.println("5) REMOVE STUDENTS LIST");
                System.out.println("6) EXIT");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new AddStudent().addStudent();
                } else if (option == 2) {
                    new RemoveStudent().removeStudent();
                } else if (option == 3) {
                    new StudentAttendance().studentAttendance(facultyName, facultyId);
                } else if (option == 4) {
                    new Attendance().attendance();
                } else if (option == 5) {
                    new StudentsAttendance().showRemoveStudents();
                } else if (option == 6) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number...");
                isTrue = false;
                classDetails(facultyName, facultyId);
            }
        }
    }
}
