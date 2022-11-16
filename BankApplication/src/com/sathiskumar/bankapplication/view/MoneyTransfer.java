package view;

import controller.TransactionController;
import model.DatabaseConnection;
import model.TransactionDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MoneyTransfer {
    private String userId;
    private String receiverId;
    private int transferAmount;
    private int amount;
    private int senderDeposit;
    public int receiverDeposit;
    private TransactionController transactionController = TransactionController.getInstance();
    private ArrayList<TransactionDatabase> depositAmount = transactionController.getAmountDetails();

    public void moneyTransfer() {
        System.out.println("================================>> MONEY TRANSFER <<================================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account id:");
        userId = scanner.nextLine();
        if (getAccountStatus()) {
            System.out.println("Receiver account id:");
            receiverId = scanner.nextLine();
            try {
                getSenderDepositAmount();
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter amount:");
                transferAmount = scan.nextInt();
                boolean amountCheck = transactionController.amountCheck(senderDeposit, transferAmount);
                if (amountCheck) {
                    getReceiverDepositAmount();
                    System.out.println("0  -> OK");
                    System.out.println("1  -> CANCEL");
                    System.out.println("Enter the number:");
                    int option = scanner.nextInt();
                    if (option == 0) {
                        setReceiverAmount();
                    } else if (option == 1) {
                        System.out.println("Cancel...");
                        moneyTransfer();
                    }
                } else {
                    System.out.println("Please check your deposit amount...");
                    callMethod();
                }
            } catch (InputMismatchException | SQLException | NullPointerException e) {
                System.out.println("Please check details...");
                callMethod();
            }
        }
    }

    private void callMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) MONEY TRANSFER");
        System.out.println("2) EXIT");
        System.out.println("Enter the number:");
        int choice = scanner.nextInt();
        if (choice == 1) {
            moneyTransfer();
        }
    }

    private void getSenderDepositAmount() throws SQLException {
        for (int i = 0; i < depositAmount.size(); i++) {
            if (userId.equals(depositAmount.get(i).getUserId())) {
                System.out.println("YOUR DEPOSIT AMOUNT   :  " + depositAmount.get(i).getDepositAmount());
                senderDeposit = Integer.parseInt(String.valueOf(depositAmount.get(i).getDepositAmount()));
                break;
            }
        }
    }

    private boolean getAccountStatus() throws NullPointerException {
        String status = transactionController.getAccountStatus(userId);
        if (status.equals("ACTIVE")) {
            return true;
        } else {
            System.out.println("ACCOUNT STATUS:     " + status);
            return false;
        }
    }

    private void getReceiverDepositAmount() throws SQLException {
        receiverDeposit = transactionController.getReceiverDepositAmount(receiverId);
        amount = receiverDeposit + transferAmount;
    }

    private void setReceiverAmount() throws SQLException {
        if (transactionController.receiverAccountCheck(receiverId)) {
            transactionController.updateReceiverAmount(receiverId, amount);
        } else {
            transactionController.insertReceiverAmount(receiverId, amount);
        }
        transactionController.updateSenderAmount(userId, senderDeposit, transferAmount);
    }
}
