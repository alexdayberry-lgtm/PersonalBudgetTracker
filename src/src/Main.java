package src;

import model.*;
import service.BudgetManager;
import util.InputValidator;

import java.util.Scanner;

/**
 * Main class for the Personal Budget Tracker application.
 * Integrates Budget Management and Password Validation.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BudgetManager manager = new BudgetManager();
        boolean running = true;

        System.out.println("Welcome to Personal Budget Tracker!");

        // Optionally ask user to set a password first
        System.out.println("\nSet a password for your budget account:");
        boolean passwordSet = false;
        while (!passwordSet) {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (PasswordValidator.isValidPassword(password)) {
                System.out.println("Password is strong. Account created successfully.");
                passwordSet = true;
            } else {
                System.out.println("Password does not meet required conditions. Try again.");
            }
        }

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Budget Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = InputValidator.getInt(scanner);

            switch (choice) {
                case 1 -> manager.addIncome(scanner);
                case 2 -> manager.addExpense(scanner);
                case 3 -> manager.viewSummary();
                case 4 -> {
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}


