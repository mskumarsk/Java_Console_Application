package attendancemanagement;

import repository.Database;
import database.FacultyDatabase;

import java.util.ArrayList;

public class FacultyDetails {

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void showFacultyDetails() {
        Database database = Database.getInstance();
        ArrayList<FacultyDatabase> facultyDatabases = database.getFaculty();
        System.out.println("=========================================>> FACULTY DETAILS <<===========" +
                "==============================");
        System.out.print(" ===============================================================================" +
                "==============");
        System.out.printf("\n | " + centerString(20, "FACULTY ID") + " | " + centerString(20,
                "FACULTY NAME") + " | " + centerString(20, "PHONE NUMBER") + " | " + centerString(20,
                "MAIL ID") + " | ");
        System.out.print("\n =============================================================================" +
                "================");
        for (int i = 0; i < facultyDatabases.size(); i++) {
            System.out.printf("\n | " + centerString(20, facultyDatabases.get(i).getFacultyId())
                    + " | " + centerString(20, facultyDatabases.get(i).getFacultyName())
                    + " | " + centerString(20, facultyDatabases.get(i).getPhoneNumber())
                    + " | " + centerString(20, facultyDatabases.get(i).getFacultyMail()) + " | ");
        }
        System.out.print("\n ============================================================================" +
                "=================");
        System.out.println();
    }
}

