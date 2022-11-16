package controler;

import model.AdminLogin;
import repository.Database;
import model.DatabaseConnection;
import model.LoginDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController extends AdminLogin {
    // Admin login
    public boolean checkAdminName(String adminName) {
        if (adminName.equals(getAdminName())) {
            return true;
        } else {
            System.out.println("Admin name is wrong...");
            return false;
        }
    }

    public boolean checkAdminPassword(String adminPassword) {
        if (adminPassword.equals(getAdminPassword())) {
            return true;
        } else {
            System.out.println("Incorrect Password");
            return false;
        }
    }

    // User Signup Login Check
    private Database database = Database.getInstance();

    public boolean checkUserLogin(String mailId, String password) {
        ArrayList<LoginDatabase> loginDatabases = database.getLoginDatabases();
        for (int i = 0; i < loginDatabases.size(); i++) {
            if (mailId.equals(loginDatabases.get(i).getMailId())) {
                if (password.equals(loginDatabases.get(i).getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insertLoginData(String userName, String phoneNumber, String mailId, String password) throws SQLException {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        String insertData = "insert into user_login_data (user_name,phone_number,mail_id,user_password) values " +
                "('" + userName + "','" + phoneNumber + "','" + mailId + "','" + password + "');";
        databaseConnection.executeQuery(insertData);
        ArrayList<LoginDatabase> loginDatabases = database.getLoginDatabases();
        loginDatabases.add(new LoginDatabase());
        loginDatabases.get(loginDatabases.size() - 1).setUserName(userName);
        loginDatabases.get(loginDatabases.size() - 1).setPhoneNumber(phoneNumber);
        loginDatabases.get(loginDatabases.size() - 1).setMailId(mailId);
        loginDatabases.get(loginDatabases.size() - 1).setPassword(password);
    }
}
