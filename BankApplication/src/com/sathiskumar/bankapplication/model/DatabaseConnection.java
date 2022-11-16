package model;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;
    private ResultSet resultSet;
    private static DatabaseConnection databaseConnection;

    public DatabaseConnection() throws SQLException {
        database();
    }

    public static DatabaseConnection getInstance() {
        try {
            if (databaseConnection == null) {
                databaseConnection = new DatabaseConnection();
            }
        } catch (SQLException e) {
            System.out.println("Database");
        }
        return databaseConnection;
    }

    public Connection database() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/BankDetails";
        String user = "root";
        String password = "8056812985msk";
        connection = DriverManager.getConnection(url, user, password);
        return connection;
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
