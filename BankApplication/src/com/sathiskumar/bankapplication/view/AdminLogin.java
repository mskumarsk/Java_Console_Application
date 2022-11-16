package view;

import controller.LoginController;

import java.util.Scanner;

public class AdminLogin {
    private Scanner scanner = new Scanner(System.in);

    public void adminPage() {
        while (true) {
            System.out.println("================================>> LOGIN PAGE  <<================================");
            System.out.println("Enter the admin name:");
            String adminName = scanner.nextLine();
            boolean nameCheck = new LoginController().checkAdminName(adminName);
            if (nameCheck) {
                System.out.println("Enter the password:");
                String adminPassword = scanner.nextLine();
                boolean passwordCheck = new LoginController().checkAdminPassword(adminPassword);
                if (passwordCheck) {
                    new AdminPage().adminPage();
                    break;
                }
            }
        }
    }
}
