package repository;

import model.AccountDetails;

import java.util.ArrayList;

public class Database {
    public static Database database;
    public ArrayList<AccountDetails> accountDetails;

    private Database() {
        accountDetails = new ArrayList<>();
        getData();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    private void getData() {
        AccountDetails accountOne = new AccountDetails(1000, "sathis", 10, 1234);
        AccountDetails accountTwo = new AccountDetails(1001, "kumar", 0, 1234);
        AccountDetails accountThree = new AccountDetails(1002, "mari", 0, 1234);
        AccountDetails accountFour = new AccountDetails(1003, "suriya", 0, 1234);
        AccountDetails accountFive = new AccountDetails(1004, "main", 0, 1234);
        accountDetails.add(accountOne);
        accountDetails.add(accountTwo);
        accountDetails.add(accountThree);
        accountDetails.add(accountFour);
        accountDetails.add(accountFive);
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(Database database) {
        Database.database = database;
    }

    public ArrayList<AccountDetails> getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(ArrayList<AccountDetails> accountDetails) {
        this.accountDetails = accountDetails;
    }
}
