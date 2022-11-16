package view;

import controler.LoginController;

import java.util.Scanner;

public class AdminLoginPage {
    public void adminLogin() {
        Scanner scanner = new Scanner(System.in);
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
                    new AdminMainPage().adminPage();
                    break;
                }
            }
        }
    }
}
