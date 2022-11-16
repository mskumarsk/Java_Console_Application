package repository;

import database.CancelBookingData;
import database.ContactDatabase;
import database.DatabaseConnection;
import database.RoomBookingDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    public static Database database = new Database();
    private ArrayList<ContactDatabase> contactDatabases;
    private ArrayList<RoomBookingDatabase> bookingDatabases;
    private ArrayList<CancelBookingData> cancelBookingData;

    private Database() {
        contactDatabases = new ArrayList<ContactDatabase>();
        bookingDatabases = new ArrayList<RoomBookingDatabase>();
        cancelBookingData = new ArrayList<CancelBookingData>();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void getContactDetails() throws SQLException {
        contactDatabases.clear();
        String getContactDetails = "select * from contactdetails";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        ResultSet resultSet = databaseConnection.getDataQuery(getContactDetails);
        int i = 0;
        while (resultSet.next()) {
            contactDatabases.add(new ContactDatabase());
            contactDatabases.get(i).setId(resultSet.getString("id"));
            contactDatabases.get(i).setName(resultSet.getString("hotelname"));
            contactDatabases.get(i).setPhoneNumber(resultSet.getString("phone"));
            contactDatabases.get(i).setAcRooms(resultSet.getString("acrooms"));
            contactDatabases.get(i).setNonAcRooms(resultSet.getString("rooms"));
            contactDatabases.get(i).setAcRoomAmount(resultSet.getString("acroomsamount"));
            contactDatabases.get(i).setNonAcRoomAmount(resultSet.getString("roomsamount"));
            contactDatabases.get(i).setAddress(resultSet.getString("address"));
            i++;
        }
        getBookingRoomDetails();
    }

    public void getBookingRoomDetails() throws SQLException {
        bookingDatabases.clear();
        String getBookingDetails = "select * from bookingdetails";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        ResultSet resultSet = databaseConnection.getDataQuery(getBookingDetails);
        int i = 0;
        while (resultSet.next()) {
            bookingDatabases.add(new RoomBookingDatabase());
            bookingDatabases.get(i).setUserName(resultSet.getString("username"));
            bookingDatabases.get(i).setPhoneNumber(resultSet.getString("phone"));
            bookingDatabases.get(i).setMail(resultSet.getString("mail"));
            bookingDatabases.get(i).setRooms(resultSet.getString("rooms"));
            bookingDatabases.get(i).setRoomType(resultSet.getString("roomtype"));
            bookingDatabases.get(i).setDays(resultSet.getString("days"));
            bookingDatabases.get(i).setStartDate(resultSet.getString("startdate"));
            bookingDatabases.get(i).setAmount(resultSet.getString("amount"));
            bookingDatabases.get(i).setBookingDate(resultSet.getString("bookingdate"));
            i++;
        }
        getCancelBookingRoomDetails();
    }

    public void getCancelBookingRoomDetails() throws SQLException {
        cancelBookingData.clear();
        String getCancelBookingDetails = "select * from cancelbookingdetails";
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        ResultSet resultSet = databaseConnection.getDataQuery(getCancelBookingDetails);
        int i = 0;
        while (resultSet.next()) {
            cancelBookingData.add(new CancelBookingData());
            cancelBookingData.get(i).setUserName(resultSet.getString("username"));
            cancelBookingData.get(i).setPhoneNumber(resultSet.getString("phone"));
            cancelBookingData.get(i).setMail(resultSet.getString("mail"));
            cancelBookingData.get(i).setRooms(resultSet.getString("rooms"));
            cancelBookingData.get(i).setRoomType(resultSet.getString("roomtype"));
            cancelBookingData.get(i).setDays(resultSet.getString("days"));
            cancelBookingData.get(i).setStartDate(resultSet.getString("startdate"));
            cancelBookingData.get(i).setAmount(resultSet.getString("amount"));
            cancelBookingData.get(i).setBookingDate(resultSet.getString("bookingdate"));
            cancelBookingData.get(i).setCancelDate(resultSet.getString("canceldate"));
            i++;
        }
    }


    public ArrayList<CancelBookingData> getCancelBookingData() {
        return cancelBookingData;
    }

    public void setCancelBookingData(ArrayList<CancelBookingData> cancelBookingData) {
        this.cancelBookingData = cancelBookingData;
    }

    public ArrayList<ContactDatabase> getContactDatabases() {
        return contactDatabases;
    }

    public void setContactDatabases(ArrayList<ContactDatabase> contactDatabases) {
        this.contactDatabases = contactDatabases;
    }

    public ArrayList<RoomBookingDatabase> getBookingDatabases() {
        return bookingDatabases;
    }

    public void setBookingDatabases(ArrayList<RoomBookingDatabase> bookingDatabases) {
        this.bookingDatabases = bookingDatabases;
    }
}
