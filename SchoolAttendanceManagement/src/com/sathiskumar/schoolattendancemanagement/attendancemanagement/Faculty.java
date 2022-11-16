package attendancemanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Faculty {
    public void faculty() {
        boolean isTrue = true;
        while (isTrue) {
            try {
                System.out.println("=================================== FACULTY MANAGEMENT ===================================");
                System.out.println("1) ADD FACULTY");
                System.out.println("2) REMOVE FACULTY");
                System.out.println("3) FACULTY LIST");
                System.out.println("4) EXIT");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the number:");
                int option = scanner.nextInt();
                if (option == 1) {
                    new AddFaculty().addFaculty();
                } else if (option == 2) {
                    new RemoveFaculty().removeFaculty();
                } else if (option == 3) {
                    new FacultyDetails().showFacultyDetails();
                } else if (option == 4) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                isTrue = false;
                System.out.println("Please enter number...");
                faculty();
            }
        }
    }
}
