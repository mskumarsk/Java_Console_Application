package controller;

import model.AccountDatabase;
import repository.BankDatabase;
import model.DatabaseConnection;
import model.TransactionDatabase;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class TransactionController {

    private static TransactionController transactionController;
    private BankDatabase bankDatabase = BankDatabase.getInstance();
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    private TransactionController() {
        showDepositDetails();
        showWithdrawDetails();
        getAmountDetails();
    }

    public static TransactionController getInstance() {
        if (transactionController == null) {
            transactionController = new TransactionController();
        }
        return transactionController;
    }

    // Account Details
    public ArrayList<TransactionDatabase> showDepositDetails() {
        ArrayList<TransactionDatabase> depositDatabase = bankDatabase.getDeposit();
        return depositDatabase;
    }

    public ArrayList<TransactionDatabase> showWithdrawDetails() {
        ArrayList<TransactionDatabase> withdrawDatabase = bankDatabase.getWithdraw();
        return withdrawDatabase;
    }

    public ArrayList<AccountDatabase> accountDetails() {
        ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
        return accountDatabases;
    }

    public ArrayList<TransactionDatabase> getAmountDetails() {
        ArrayList<TransactionDatabase> amountDetails = bankDatabase.getAmount();
        return amountDetails;
    }

    //  Deposit Controller
    public boolean accountCheck(String userId) {
        ArrayList<AccountDatabase> accountDatabases = bankDatabase.getAccount();
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (userId.equals(accountDatabases.get(i).getUserId())) {
                return true;
            }
        }
        return false;
    }

    public void insertDepositAmount(String userId, String depositAmount) throws SQLException {
        String insertDepositAmount = "insert into deposit (user_id,deposit) values ('" + userId + "'" +
                ",'" + depositAmount + "');";
        databaseConnection.executeQuery(insertDepositAmount);
        ArrayList<TransactionDatabase> insertDeposit = bankDatabase.getDeposit();
        insertDeposit.add(new TransactionDatabase());
        insertDeposit.get(insertDeposit.size() - 1).setUserId(userId);
        insertDeposit.get(insertDeposit.size() - 1).setDepositAmount(Integer.parseInt(depositAmount));
        LocalDateTime localDateTime = LocalDateTime.now();
        insertDeposit.get(insertDeposit.size() - 1).setDate(localDateTime.toString());
    }

    public void getDepositAmount(String userId, String userDepositAmount) throws SQLException {
        ArrayList<TransactionDatabase> depositAmount = bankDatabase.getAmount();
        int amountCheck = 0;
        for (int i = 0; i < depositAmount.size(); i++) {
            if (userId.equals(depositAmount.get(i).getUserId())) {
                amountCheck = 1;
                int amount = depositAmount.get(i).getDepositAmount();
                updateDepositAmount(userId, amount, userDepositAmount);
                break;
            }
        }
        if (amountCheck == 0) {
            insertInitialAmount(userId, userDepositAmount);
        }
    }

    private void updateDepositAmount(String userId, int amount, String depositAmount) throws SQLException {
        int totalAmount = amount + Integer.parseInt(depositAmount);
        String updateDeposit = "update amount set deposit='" + totalAmount + "' where user_id='" + userId + "'";
        databaseConnection.executeQuery(updateDeposit);
        ArrayList<TransactionDatabase> getAmount = bankDatabase.getAmount();
        for (int i = 0; i < getAmount.size(); i++) {
            if (userId.equals(getAmount.get(i).getUserId())) {
                getAmount.get(i).setDepositAmount(totalAmount);
                break;
            }
        }
    }

    private void insertInitialAmount(String userId, String depositAmount) throws SQLException {
        String insertAmount = "insert into amount (user_id,deposit) " +
                "values ('" + userId + "','" + depositAmount + "');";
        databaseConnection.executeQuery(insertAmount);
        BankDatabase bankDatabase = BankDatabase.getInstance();
        ArrayList<TransactionDatabase> insertDepositAmount = bankDatabase.getAmount();
        insertDepositAmount.add(new TransactionDatabase());
        insertDepositAmount.get(insertDepositAmount.size() - 1).setUserId(userId);
        insertDepositAmount.get(insertDepositAmount.size() - 1).setDepositAmount(Integer.parseInt(depositAmount));
    }

    // Money Transaction controller
    public String getAccountStatus(String userId) {
        String status = null;
        ArrayList<AccountDatabase> accountDatabase = transactionController.accountDetails();
        for (int i = 0; i < accountDatabase.size(); i++) {
            if (userId.equals(accountDatabase.get(i).getUserId())) {
                status = accountDatabase.get(i).getStatus();
                break;
            }
        }
        return status;
    }

    public int getReceiverDepositAmount(String receiverId) throws SQLException {
        int receiverDeposit = 0;
        ArrayList<TransactionDatabase> depositAmount = getAmountDetails();
        for (int i = 0; i < depositAmount.size(); i++) {
            if (receiverId.equals(depositAmount.get(i).getUserId())) {
                receiverDeposit = Integer.parseInt(String.valueOf(depositAmount.get(i).getDepositAmount()));
                break;
            }
        }
        return receiverDeposit;
    }

    public boolean receiverAccountCheck(String receiverId) {
        ArrayList<TransactionDatabase> transactionDatabases = bankDatabase.getAmount();
        Iterator<TransactionDatabase> iterator = transactionDatabases.iterator();
        while (iterator.hasNext()) {
            if (receiverId.equals(iterator.next().getUserId())) {
                return true;
            }
        }
        return false;
    }

    public void updateReceiverAmount(String receiverId, int transferAmount) throws SQLException {
        ArrayList<TransactionDatabase> depositAmount = bankDatabase.getAmount();
        String updateAmount = "update amount set deposit='" + transferAmount + "' where user_id='" + receiverId + "'";
        databaseConnection.executeQuery(updateAmount);
        for (int i = 0; i < depositAmount.size(); i++) {
            if (receiverId.equals(depositAmount.get(i).getUserId())) {
                depositAmount.get(i).setDepositAmount(transferAmount);
                break;
            }
        }
    }

    public void insertReceiverAmount(String receiverId, int transferAmount) throws SQLException {
        String insertAmount = "insert into amount (user_id,deposit) values ('" + receiverId + "'" +
                ",'" + transferAmount + "');";
        databaseConnection.executeQuery(insertAmount);
        ArrayList<TransactionDatabase> insertDeposit = bankDatabase.getAmount();
        insertDeposit.add(new TransactionDatabase());
        insertDeposit.get(insertDeposit.size() - 1).setUserId(receiverId);
        insertDeposit.get(insertDeposit.size() - 1).setDepositAmount(Integer.parseInt(String.valueOf(transferAmount)));

        String insertDepositAmount = "insert into deposit (user_id,deposit) values ('" + receiverId + "'" +
                ",'" + transferAmount + "');";
        databaseConnection.executeQuery(insertDepositAmount);
        ArrayList<TransactionDatabase> insertReceiverAmount = bankDatabase.getDeposit();
        insertReceiverAmount.add(new TransactionDatabase());
        insertReceiverAmount.get(insertReceiverAmount.size() - 1).setUserId(receiverId);
        insertReceiverAmount.get(insertReceiverAmount.size() - 1).setDepositAmount(Integer.parseInt(String.valueOf(transferAmount)));
        LocalDateTime localDateTime = LocalDateTime.now();
        insertReceiverAmount.get(insertReceiverAmount.size() - 1).setDate(localDateTime.toString());
    }

    public void updateSenderAmount(String userId, int senderDeposit, int transferAmount) throws SQLException {
        ArrayList<TransactionDatabase> depositAmount = bankDatabase.getAmount();
        int senderAmount = senderDeposit - transferAmount;
        String updateSenderAmount = "update amount set deposit='" + senderAmount + "' where user_id='" + userId + "'";
        databaseConnection.executeQuery(updateSenderAmount);
        for (int i = 0; i < depositAmount.size(); i++) {
            if (userId.equals(depositAmount.get(i).getUserId())) {
                depositAmount.get(i).setDepositAmount(senderAmount);
                break;
            }
        }
        insertWithdrawDetails(userId, transferAmount);
    }

    private void insertWithdrawDetails(String userId, int transferAmount) throws SQLException {
        String insertAmount = "insert into withdraw (user_id,withdraw) values ('" + userId + "'," +
                "'" + transferAmount + "');";
        databaseConnection.executeQuery(insertAmount);
        ArrayList<TransactionDatabase> updateWithdraw = bankDatabase.getWithdraw();
        updateWithdraw.add(new TransactionDatabase());
        updateWithdraw.get(updateWithdraw.size() - 1).setUserId(userId);
        updateWithdraw.get(updateWithdraw.size() - 1).setWithdrawAmount(transferAmount);
        LocalDateTime localDateTime = LocalDateTime.now();
        updateWithdraw.get(updateWithdraw.size() - 1).setDate(String.valueOf(localDateTime));
        System.out.println("Transfer successfully...");
    }

    public boolean amountCheck(int senderDeposit, int transferAmount) {
        if (senderDeposit >= transferAmount) {
            return true;
        } else {
            return false;
        }
    }

    // Withdraw controller

    public void getWithdrawAmount(String userId, String depositAmount, String withdrawAmount) throws SQLException {
        int amount = Integer.parseInt(depositAmount) - Integer.parseInt(withdrawAmount);
        String updateDeposit = "update amount set deposit='" + amount + "' where user_id='" + userId + "'";
        databaseConnection.executeQuery(updateDeposit);
        ArrayList<TransactionDatabase> databaseAmount = transactionController.getAmountDetails();
        for (int i = 0; i < databaseAmount.size(); i++) {
            if (userId.equals(databaseAmount.get(i).getUserId())) {
                databaseAmount.get(i).setDepositAmount(amount);
                break;

            }
        }
        String insertAmount = "insert into withdraw (user_id,withdraw) values ('" + userId + "'," +
                "'" + withdrawAmount + "');";
        databaseConnection.executeQuery(insertAmount);
        ArrayList<TransactionDatabase> withdraw = transactionController.showWithdrawDetails();
        withdraw.add(new TransactionDatabase());
        withdraw.get(withdraw.size() - 1).setUserId(userId);
        withdraw.get(withdraw.size() - 1).setWithdrawAmount(Integer.parseInt(withdrawAmount));
        LocalDateTime localDateTime = LocalDateTime.now();
        withdraw.get(withdraw.size() - 1).setDate(String.valueOf(localDateTime));
    }
}
