package repository;

import model.AccountDatabase;
import model.DatabaseConnection;
import model.LoginDatabase;
import model.TransactionDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankDatabase {
    public static BankDatabase bankDatabase;
    private ArrayList<AccountDatabase> account;
    private ArrayList<LoginDatabase> login;
    private ArrayList<TransactionDatabase> deposit;
    private ArrayList<TransactionDatabase> withdraw;
    private ArrayList<TransactionDatabase> amount;
    private int accountCount;
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    private BankDatabase() {
        account = new ArrayList<AccountDatabase>();
        login = new ArrayList<LoginDatabase>();
        deposit = new ArrayList<TransactionDatabase>();
        withdraw = new ArrayList<TransactionDatabase>();
        amount = new ArrayList<TransactionDatabase>();
    }

    public static BankDatabase getInstance() {
        if (bankDatabase == null) {
            bankDatabase = new BankDatabase();
        }
        return bankDatabase;
    }

    public void getLoginData() throws SQLException {
        String getUserLoginData = "select user_name,user_password from user_sign";
        ResultSet resultSet = databaseConnection.getDataQuery(getUserLoginData);
        int i = 0;
        while (resultSet.next()) {
            login.add(new LoginDatabase());
            login.get(i).setUserName(resultSet.getString("user_name"));
            login.get(i).setUserPassword(resultSet.getString("user_password"));
            i++;
        }
        getAccountData();
    }

    public void getAccountData() throws SQLException {
        String getAccountData = "select * from new_account";
        ResultSet resultSet = databaseConnection.getDataQuery(getAccountData);
        int i = 0;
        while (resultSet.next()) {
            account.add(new AccountDatabase());
            account.get(i).setUserId(resultSet.getString("user_id"));
            account.get(i).setPinNumber(resultSet.getString("pin_number"));
            account.get(i).setUserName(resultSet.getString("user_name"));
            account.get(i).setUserDateOfBirth(resultSet.getString("date_of_birth"));
            account.get(i).setUserPhoneNumber(resultSet.getString("phone_no"));
            account.get(i).setUserGender(resultSet.getString("gender"));
            account.get(i).setUserCountry(resultSet.getString("country"));
            account.get(i).setUserState(resultSet.getString("state"));
            account.get(i).setUserAadhaarNumber(resultSet.getString("aadhar"));
            account.get(i).setStatus(resultSet.getString("account_status"));
            account.get(i).setDate(resultSet.getString("account_date"));
            i++;
        }
        getAccountId();
        getDepositDetails();
    }

    private void getAccountId() throws SQLException {
        String getAccountCount = "select max(user_id) as accountCount from new_account";
        ResultSet resultSet = databaseConnection.getDataQuery(getAccountCount);
        resultSet.next();
        setAccountCount(resultSet.getInt("accountCount"));
    }

    public void getDepositDetails() throws SQLException {
        String getDepositDetails = "select * from deposit";
        ResultSet resultSet = databaseConnection.getDataQuery(getDepositDetails);
        int i = 0;
        while (resultSet.next()) {
            deposit.add(new TransactionDatabase());
            deposit.get(i).setUserId(resultSet.getString("user_id"));
            deposit.get(i).setDepositAmount(Integer.parseInt(resultSet.getString("deposit")));
            deposit.get(i).setDate(resultSet.getString("deposit_date"));
            i++;
        }
        getWithdrawDetails();
    }

    public void getWithdrawDetails() throws SQLException {
        String getWithdrawDetails = "select * from withdraw";
        ResultSet resultSet = databaseConnection.getDataQuery(getWithdrawDetails);
        int i = 0;
        while (resultSet.next()) {
            withdraw.add(new TransactionDatabase());
            withdraw.get(i).setUserId(resultSet.getString("user_id"));
            withdraw.get(i).setWithdrawAmount(Integer.parseInt(resultSet.getString("withdraw")));
            withdraw.get(i).setDate(resultSet.getString("withdraw_date"));
            i++;
        }
        getUserAmountDetails();
    }

    public void getUserAmountDetails() throws SQLException {
        String getDepositDetails = "select * from amount";
        ResultSet resultSet = databaseConnection.getDataQuery(getDepositDetails);
        int i = 0;
        while (resultSet.next()) {
            amount.add(new TransactionDatabase());
            amount.get(i).setUserId(resultSet.getString("user_id"));
            amount.get(i).setDepositAmount(Integer.parseInt(resultSet.getString("deposit")));
            i++;
        }
    }

    public ArrayList<TransactionDatabase> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<TransactionDatabase> amount) {
        this.amount.addAll(amount);
    }

    public void setAccount(ArrayList<AccountDatabase> account) {
        this.account = account;
    }

    public void setLogin(ArrayList<LoginDatabase> login) {
        this.login.addAll(login);
    }

    public ArrayList<AccountDatabase> getAccount() {
        return account;
    }

    public ArrayList<LoginDatabase> getLogin() {
        return login;
    }

    public ArrayList<TransactionDatabase> getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(ArrayList<TransactionDatabase> withdraw) {
        this.withdraw.addAll(withdraw);
    }

    public ArrayList<TransactionDatabase> getDeposit() {
        return deposit;
    }

    public void setDeposit(ArrayList<TransactionDatabase> deposit) {
        this.deposit.addAll(deposit);
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }

    public int getAccountCount() {
        return accountCount;
    }
}
