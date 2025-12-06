package budget;

import security.PasswordValidator;
import java.util.Scanner;

/**
 * Main entry point for the Personal Budget Tracker application.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message FIRST
        System.out.println("Welcome to your personal budget tracker.");

        // Password setup/login
        System.out.print("Create or enter your password: ");
        String password = scanner.nextLine();

        while (!PasswordValidator.isValid(password)) {
            System.out.println("Password does not meet the required conditions.");
            System.out.print("Enter a valid password: ");
            password = scanner.nextLine();
        }

        System.out.println("Password accepted. Access granted!");

        // Start budget manager
        BudgetManager manager = new BudgetManager();
        manager.run();
    }
}
