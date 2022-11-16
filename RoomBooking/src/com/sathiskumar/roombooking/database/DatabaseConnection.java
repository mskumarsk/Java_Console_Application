package database;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;
    private ResultSet resultSet;
    private static DatabaseConnection databaseConnection;

    private DatabaseConnection() throws SQLException {
        database();
    }

    public static DatabaseConnection getInstance() {
        try {
            if (databaseConnection == null) {
                databaseConnection = new DatabaseConnection();
            }
        }catch (SQLException e){
            System.out.println("Database");
        }
        return databaseConnection;
    }


    private void database() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/RoomBooking";
        String user = "root";
        String password = "8056812985msk";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void executeQuery(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
    }

    public ResultSet getDataQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }
}
