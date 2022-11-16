package repository;

import model.BusDetailsDatabase;
import model.DatabaseConnection;
import model.LoginDatabase;
import model.TicketBook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<LoginDatabase> loginDatabases;
    private ArrayList<TicketBook> ticketBooks;
    private ArrayList<BusDetailsDatabase> busDetailsDatabases;
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    private Database() {
        loginDatabases = new ArrayList<>();
        ticketBooks = new ArrayList<>();
        busDetailsDatabases = new ArrayList<>();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void getLoginData() throws SQLException {
        String getData = "select * from user_login_data";
        ResultSet resultSet = databaseConnection.getDataQuery(getData);
        int i = 0;
        while (resultSet.next()) {
            loginDatabases.add(new LoginDatabase());
            loginDatabases.get(i).setUserName(resultSet.getString("user_name"));
            loginDatabases.get(i).setPhoneNumber(resultSet.getString("phone_number"));
            loginDatabases.get(i).setMailId(resultSet.getString("mail_id"));
            loginDatabases.get(i).setPassword(resultSet.getString("user_password"));
            i++;
        }
        getBookingData();
    }

    private void getBookingData() throws SQLException {
        String getData = "select * from book_ticket";
        ResultSet resultSet = databaseConnection.getDataQuery(getData);
        int i = 0;
        while (resultSet.next()) {
            ticketBooks.add(new TicketBook());
            ticketBooks.get(i).setBus(resultSet.getString("bus"));
            ticketBooks.get(i).setUserName(resultSet.getString("user_name"));
            ticketBooks.get(i).setPhoneNumber(resultSet.getString("phone"));
            ticketBooks.get(i).setFormStation(resultSet.getString("form_station"));
            ticketBooks.get(i).setToStation(resultSet.getString("to_station"));
            ticketBooks.get(i).setSeat(resultSet.getString("seat"));
            ticketBooks.get(i).setDate(resultSet.getString("travel_date"));
            ticketBooks.get(i).setAmount(resultSet.getString("amount"));
            ticketBooks.get(i).setStatus(resultSet.getString("book_status"));
            ticketBooks.get(i).setMail(resultSet.getString("mail"));
            ticketBooks.get(i).setBookingDate(resultSet.getString("booking_date"));
            i++;
        }
        getBusDetails();
    }

    private void getBusDetails() throws SQLException {
        String getData = "select * from travel_details";
        ResultSet resultSet = databaseConnection.getDataQuery(getData);
        int i = 0;
        while (resultSet.next()) {
            busDetailsDatabases.add(new BusDetailsDatabase());
            busDetailsDatabases.get(i).setTravelName(resultSet.getString("travel_name"));
            busDetailsDatabases.get(i).setPhoneNumber(resultSet.getString("phone"));
            busDetailsDatabases.get(i).setAddress(resultSet.getString("address"));
            busDetailsDatabases.get(i).setBusRoot(resultSet.getString("bus_root"));
            busDetailsDatabases.get(i).setAmount(resultSet.getString("amount"));
            busDetailsDatabases.get(i).setSeat(resultSet.getString("total_seat"));
            busDetailsDatabases.get(i).setStartTime(resultSet.getString("start_time"));
            busDetailsDatabases.get(i).setEndTime(resultSet.getString("end_time"));
            busDetailsDatabases.get(i).setBusNumber(resultSet.getString("bus_no"));
            busDetailsDatabases.get(i).setDriverName(resultSet.getString("driver_name"));
            busDetailsDatabases.get(i).setDriverPhoneNumber(resultSet.getString("driver_number"));
            i++;
        }
    }

    public ArrayList<BusDetailsDatabase> getBusDetailsDatabases() {
        return busDetailsDatabases;
    }

    public void setBusDetailsDatabases(ArrayList<BusDetailsDatabase> busDetailsDatabases) {
        this.busDetailsDatabases = busDetailsDatabases;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public ArrayList<TicketBook> getTicketBooks() {
        return ticketBooks;
    }

    public void setTicketBooks(ArrayList<TicketBook> ticketBooks) {
        this.ticketBooks = ticketBooks;
    }

    public ArrayList<LoginDatabase> getLoginDatabases() {
        return loginDatabases;
    }

    public void setLoginDatabases(ArrayList<LoginDatabase> loginDatabases) {
        this.loginDatabases = loginDatabases;
    }
}
