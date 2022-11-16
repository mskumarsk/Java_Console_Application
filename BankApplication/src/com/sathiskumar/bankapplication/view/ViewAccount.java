package view;

import controller.TransactionController;
import model.AccountDatabase;
import model.TransactionDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ViewAccount {
    private TransactionController transactionController = TransactionController.getInstance();

    public String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void view() {
        System.out.println("================================>> VIEW ACCOUNTS <<================================");
        try {
            showAccountDetails();
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Please check your details...");
        }
    }

    private void showAccountDetails() throws SQLException {
        ArrayList<AccountDatabase> accountDatabases = transactionController.accountDetails();
        System.out.println("========================================================================" +
                "=============>>  ACCOUNTS DETAILS  <<============================================" +
                "============================");
        System.out.print(" =============================================================================" +
                "==========================================================================================" +
                "==================================================================================");
        System.out.printf("\n | " + centerString(15, "ACCOUNT NUMBER") + " | " + centerString(20,
                "PIN NUMBER") + " | " + centerString(20, "USER NAME") + " | " + centerString(20,
                "DATE OF BIRTH") + " | " + centerString(20, "PHONE NO") + " | " + centerString(20,
                "GENDER") + " | " + centerString(20, "COUNTRY") + " | " + centerString(20,
                "STATE") + " | " + centerString(20, "AADHAAR NUMBER") + " | " + centerString(20,
                "STATUS") + " | " + centerString(20, "DATE") + " | ");
        System.out.print("\n ==============================================================================" +
                "==========================================================================================" +
                "=================================================================================");
        for (int i = 0; i < accountDatabases.size(); i++) {
            System.out.printf("\n | " + centerString(15, accountDatabases.get(i).getUserId()) + " | "
                    + centerString(20, accountDatabases.get(i).getPinNumber()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserName()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserDateOfBirth()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserPhoneNumber()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserGender()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserCountry()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserState()) + " | "
                    + centerString(20, accountDatabases.get(i).getUserAadhaarNumber()) + " | "
                    + centerString(20, accountDatabases.get(i).getStatus()) + " | "
                    + centerString(20, accountDatabases.get(i).getDate()) + " | ");
        }
        System.out.print("\n ==============================================================================" +
                "==========================================================================================" +
                "=================================================================================");
        System.out.println();
        showAmountDetails();
    }

    private void showAmountDetails() {
        System.out.println("==================================================>>  AMOUNT DETAILS  <<=======" +
                "=========================================");
        System.out.print(" =================================================================");
        System.out.printf("\n | " + centerString(15, "ID") + " | " + centerString(20,
                "NAME") + " | " + centerString(20, "AMOUNT") + " | ");
        System.out.print("\n =================================================================");
        ArrayList<TransactionDatabase> transactionDatabases = transactionController.getAmountDetails();
        ArrayList<AccountDatabase> accountDatabases = transactionController.accountDetails();
        for (int i = 0; i < transactionDatabases.size(); i++) {
            String id = transactionDatabases.get(i).getUserId();
            for (int j = 0; j < accountDatabases.size(); j++) {
                if (id.equals(accountDatabases.get(j).getUserId())) {
                    System.out.printf("\n | " + centerString(15, transactionDatabases.get(i).getUserId())
                            + " | " + centerString(20, accountDatabases.get(j).getUserName())
                            + " | " + centerString(20, String.valueOf(transactionDatabases.get(i).getDepositAmount())) + " | ");
                    break;
                }
            }
        }
        System.out.print("\n =================================================================");
        System.out.println();
    }

    public void showBlockAccounts() {
        ArrayList<AccountDatabase> accountDatabases = transactionController.accountDetails();
        System.out.println("========================================================================" +
                "=============>>  ACCOUNTS DETAILS  <<============================================" +
                "============================");
        System.out.print(" =============================================================================" +
                "==========================================================================================" +
                "==================================================================================");
        System.out.printf("\n | " + centerString(15, "ACCOUNT NUMBER") + " | " + centerString(20,
                "PIN NUMBER") + " | " + centerString(20, "USER NAME") + " | " + centerString(20,
                "DATE OF BIRTH") + " | " + centerString(20, "PHONE NO") + " | " + centerString(20,
                "GENDER") + " | " + centerString(20, "COUNTRY") + " | " + centerString(20,
                "STATE") + " | " + centerString(20, "AADHAAR NUMBER") + " | " + centerString(20,
                "STATUS") + " | " + centerString(20, "DATE") + " | ");
        System.out.print("\n ==============================================================================" +
                "==========================================================================================" +
                "=================================================================================");
        for (int i = 0; i < accountDatabases.size(); i++) {
            if (accountDatabases.get(i).getStatus().equals("BLOCK")) {
                System.out.printf("\n | " + centerString(15, accountDatabases.get(i).getUserId()) + " | "
                        + centerString(20, accountDatabases.get(i).getPinNumber()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserName()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserDateOfBirth()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserPhoneNumber()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserGender()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserCountry()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserState()) + " | "
                        + centerString(20, accountDatabases.get(i).getUserAadhaarNumber()) + " | "
                        + centerString(20, accountDatabases.get(i).getStatus()) + " | "
                        + centerString(20, accountDatabases.get(i).getDate()) + " | ");
            }
        }
        System.out.print("\n ==============================================================================" +
                "==========================================================================================" +
                "=================================================================================");
        System.out.println();
    }

    public void userAmountDetails(String accountId) {
        System.out.println("==================================================>>  AMOUNT DETAILS  <<=======" +
                "=========================================");
        System.out.print(" ==========================================");
        System.out.printf("\n | " + centerString(15, "ID") + " | " + centerString(20, "AMOUNT") + " | ");
        System.out.print("\n ==========================================");
        ArrayList<TransactionDatabase> transactionDatabases = transactionController.getAmountDetails();
        boolean amountCheck = false;
        for (int j = 0; j < transactionDatabases.size(); j++) {
            if (accountId.equals(transactionDatabases.get(j).getUserId())) {
                System.out.printf("\n | " + centerString(15, transactionDatabases.get(j).getUserId())
                        + " | " + centerString(20, String.valueOf(transactionDatabases.get(j).getDepositAmount())) + " | ");
                amountCheck = true;
                break;
            }
        }
        System.out.print("\n ==========================================");
        System.out.println();
        if (!amountCheck) {
            System.out.println("No deposit amount...");
        }
    }
}
