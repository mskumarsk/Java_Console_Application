package controler;

import model.*;
import repository.Database;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TicketController {
    // Ticket book controller
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    private Database database = Database.getInstance();

    public boolean seatCheck(String seat, String date, String bus) {
        int totSeatCount = checkSeatAvailable(date, bus);
        int totSeat = Integer.parseInt(seat);
        int seatCount = totSeatCount + totSeat;
        if (seatCount <= 60) {
            return true;
        } else {
            return false;
        }
    }

    public int checkSeatAvailable(String date, String bus) {
        int count = 0;
        String status = "ACTIVE";
        ArrayList<TicketBook> ticketBooks = database.getTicketBooks();
        for (int i = 0; i < ticketBooks.size(); i++) {
            if (bus.equals(ticketBooks.get(i).getBus())) {
                if (date.equals(ticketBooks.get(i).getDate())) {
                    if (status.equals(ticketBooks.get(i).getStatus())) {
                        count += Integer.parseInt(ticketBooks.get(i).getSeat());
                    }
                }
            }
        }
        return count;
    }

    public int getAmount(String seat, int startPoint, int endPoint, String busRoot) {
        int getSeat = Integer.parseInt(seat);
        int distance = 0;
        if (startPoint < endPoint) {
            distance = endPoint - startPoint + 1;
        } else if (startPoint > endPoint) {
            distance = startPoint - endPoint + 1;
        } else {
            distance = 1;
        }
        String getAmount = getAmountFromDatabase(busRoot);
        int amount = Integer.parseInt(getAmount);
        int totalAmount = distance * amount;
        return getSeat * totalAmount;
    }

    private String getAmountFromDatabase(String busRoot) {
        String amount = "";
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busRoot.equals(busDetailsDatabases.get(i).getBusRoot())) {
                amount = busDetailsDatabases.get(i).getAmount();
                break;
            }
        }
        return amount;
    }

    public void insertBookingData(String bus, String userName, String phoneNumber, String fromStation, String toStation,
                                  String seat, String travelDate, String amount, String mail) throws SQLException {
        String status = "ACTIVE";
        String insertData = "insert into book_ticket(bus,user_name,phone,form_station,to_station,seat,travel_date,amount," +
                "book_status,mail) values('" + bus + "','" + userName + "','" + phoneNumber + "','" + fromStation + "','" + toStation + "'," +
                "'" + seat + "','" + travelDate + "','" + amount + "','" + status + "','" + mail + "');";
        databaseConnection.executeQuery(insertData);
        LocalDateTime dateTime = LocalDateTime.now();
        ArrayList<TicketBook> ticketBooks = database.getTicketBooks();
        ticketBooks.add(new TicketBook());
        ticketBooks.get(ticketBooks.size() - 1).setBus(bus);
        ticketBooks.get(ticketBooks.size() - 1).setUserName(userName);
        ticketBooks.get(ticketBooks.size() - 1).setPhoneNumber(phoneNumber);
        ticketBooks.get(ticketBooks.size() - 1).setFormStation(fromStation);
        ticketBooks.get(ticketBooks.size() - 1).setToStation(toStation);
        ticketBooks.get(ticketBooks.size() - 1).setSeat(seat);
        ticketBooks.get(ticketBooks.size() - 1).setDate(travelDate);
        ticketBooks.get(ticketBooks.size() - 1).setAmount(amount);
        ticketBooks.get(ticketBooks.size() - 1).setStatus(status);
        ticketBooks.get(ticketBooks.size() - 1).setMail(mail);
        ticketBooks.get(ticketBooks.size() - 1).setBookingDate(String.valueOf(dateTime));
    }

    // Available tickets
    public int getAvailableSeat(String busRoot, String date) {
        int count = checkSeatAvailable(date, busRoot);
        return 60 - count;
    }

    // Ticket cancel
    public void cancelTicket(String bus, String userName, String date, String mailId) throws SQLException {
        String status = "BLOCK";
        String cancelTicket = "update book_ticket set book_status='" + status + "' where bus='" + bus + "' and " +
                "user_name='" + userName + "' and travel_date='" + date + "' and mail='" + mailId + "'";
        databaseConnection.executeQuery(cancelTicket);
        ArrayList<TicketBook> ticketBooks = database.getTicketBooks();
        for (int i = 0; i < ticketBooks.size(); i++) {
            if (bus.equals(ticketBooks.get(i).getBus())) {
                if (userName.equals(ticketBooks.get(i).getUserName())) {
                    if (date.equals(ticketBooks.get(i).getDate())) {
                        if (mailId.equals(ticketBooks.get(i).getMail())) {
                            ticketBooks.get(i).setStatus(status);
                            break;
                        }
                    }
                }
            }
        }
    }

    // Return booked ticket details
    public ArrayList<TicketBook> getTicketBook() {
        return database.getTicketBooks();
    }

    // Return bus details
    public ArrayList<BusDetailsDatabase> getBusDetails() {
        return database.getBusDetailsDatabases();
    }

    // Update contact
    public void setTravelName(String busNumber, String name) throws SQLException {
        String setTravelName = "update travel_details set travel_name='" + name + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setTravelName);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setTravelName(name);
                break;
            }
        }
    }

    public void setPhoneNumber(String busNumber, String phoneNumber) throws SQLException {
        String setPhoneNumber = "update travel_details set phone='" + phoneNumber + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setPhoneNumber);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setPhoneNumber(phoneNumber);
                break;
            }
        }
    }

    public void setAddress(String busNumber, String address) throws SQLException {
        String setAddress = "update travel_details set address='" + address + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setAddress);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setAddress(address);
                break;
            }
        }
    }

    public void setAmount(String busNumber, String amount) throws SQLException {
        String setAmount = "update travel_details set amount='" + amount + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setAmount);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setAmount(amount);
                break;
            }
        }
    }

    public void setStartTime(String busNumber, String time) throws SQLException {
        String setTime = "update travel_details set start_time='" + time + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setTime);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setStartTime(time);
                break;
            }
        }
    }

    public void setEndTime(String busNumber, String time) throws SQLException {
        String setTime = "update travel_details set end_time='" + time + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setTime);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setEndTime(time);
                break;
            }
        }
    }

    public void setDriverName(String busNumber, String driverName) throws SQLException {
        String setDriverName = "update travel_details set driver_name='" + driverName + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setDriverName);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setDriverName(driverName);
                break;
            }
        }
    }

    public void setDriverNumber(String busNumber, String driverPhoneNumber) throws SQLException {
        String setDriverNumber = "update travel_details set driver_number='" + driverPhoneNumber + "' where bus_no='" + busNumber + "'";
        databaseConnection.executeQuery(setDriverNumber);
        ArrayList<BusDetailsDatabase> busDetailsDatabases = database.getBusDetailsDatabases();
        for (int i = 0; i < busDetailsDatabases.size(); i++) {
            if (busNumber.equals(busDetailsDatabases.get(i).getBusNumber())) {
                busDetailsDatabases.get(i).setDriverPhoneNumber(driverPhoneNumber);
                break;
            }
        }
    }
}
