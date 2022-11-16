package database;

public class FacultyDatabase {
    private String facultyId;
    private String facultyName;
    private String phoneNumber;
    private String facultyMail;

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFacultyMail() {
        return facultyMail;
    }

    public void setFacultyMail(String facultyMail) {
        this.facultyMail = facultyMail;
    }
}
