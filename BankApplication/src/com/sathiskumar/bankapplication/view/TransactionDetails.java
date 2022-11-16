package view;

import controller.TransactionController;
import model.TransactionDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDetails {
    TransactionController transactionController = TransactionController.getInstance();

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void transaction(String accountNumber) {
        System.out.println("================================>> TRANSACTION DETAILS <<================================");
        try {
            showDepositDetails(accountNumber);
        } catch (SQLException e) {
            System.out.println("Please check your name and account id...");
        }
    }

    private void showDepositDetails(String accountNumber) throws SQLException {
        ArrayList<TransactionDatabase> depositDatabase = transactionController.showDepositDetails();
        System.out.println("==================================================>>  DEPOSIT DETAILS  <<=========" +
                "=======================================");
        System.out.print(" ======================================================================");
        System.out.printf("\n | " + centerString(20, "ACCOUNT NUMBER") + " | " + centerString(20,
                "DEPOSIT") + " | " + centerString(20, "DATE") + " | ");
        System.out.print("\n ======================================================================");
        for (int i = 0; i < depositDatabase.size(); i++) {
            if (accountNumber.equals(depositDatabase.get(i).getUserId())) {
                System.out.printf("\n | " + centerString(20, depositDatabase.get(i).getUserId()) +
                        " | " + centerString(20, String.valueOf(depositDatabase.get(i).getDepositAmount())) +
                        " | " + centerString(20, depositDatabase.get(i).getDate()) + " | ");
            }
        }
        System.out.print("\n ======================================================================");
        System.out.println();
        showWithdrawDetails(accountNumber);
    }

    private void showWithdrawDetails(String accountNumber) {
        ArrayList<TransactionDatabase> withdrawDatabase = transactionController.showWithdrawDetails();
        System.out.println("==================================================>>  WITHDRAW DETAILS  <<=======" +
                "=========================================");
        System.out.print(" ======================================================================");
        System.out.printf("\n | " + centerString(20, "ACCOUNT NUMBER") + " | " + centerString(20,
                "WITHDRAW") + " | " + centerString(20, "DATE") + " | ");
        System.out.print("\n ======================================================================");
        for (int i = 0; i < withdrawDatabase.size(); i++) {
            if (accountNumber.equals(withdrawDatabase.get(i).getUserId())) {
                System.out.printf("\n | " + centerString(20, withdrawDatabase.get(i).getUserId()) +
                        " | " + centerString(20, String.valueOf(withdrawDatabase.get(i).getWithdrawAmount())) +
                        " | " + centerString(20, withdrawDatabase.get(i).getDate()) + " | ");
            }
        }
        System.out.print("\n ======================================================================");
        System.out.println();
    }
}
