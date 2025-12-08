package budget;

import java.util.Scanner;

/**
 * Manages the main flow of the budgeting application, including menu navigation,
 * user input handling, and interactions with UserBudget.
 *
 * Demonstrates: Scanner, switch, loops, String methods, printf, this, constants.
 */
public class BudgetManager {

    private final Scanner scanner;
    private final UserBudget userBudget;

    // constant example
    private static final String PROMPT = "Choose an option: ";

    public BudgetManager(Scanner scanner) {
        this.scanner = scanner;
        this.userBudget = new UserBudget();
    }

    public void run() {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> setMonthlyBudget();
                case "2" -> transactionMenu();
                case "3" -> viewSummary();
                case "4" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.\n");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("===== Budget Menu =====");
        System.out.println("1. Set Monthly Budget");
        System.out.println("2. Transactions (Income / Expense)");
        System.out.println("3. View Monthly Balance & Summary");
        System.out.println("4. Exit");
        System.out.print(PROMPT);
    }

    private void setMonthlyBudget() {
        System.out.print("Enter desired monthly budget amount: ");
        String raw = scanner.nextLine().replace("$", "").trim();
        try {
            double amount = Double.parseDouble(raw);
            if (amount < 0) throw new NumberFormatException();
            userBudget.setMonthlyBudget(amount);
            // demonstrate printf
            System.out.printf("Monthly budget set to $%.2f%n%n", amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Monthly budget not changed.\n");
        }
    }

    private void transactionMenu() {
        System.out.println("\n--- Transactions ---");
        System.out.println("1. Income");
        System.out.println("2. Expense");
        System.out.print("Choose type: ");

        String typeChoice = scanner.nextLine().trim();
        if (!typeChoice.equals("1") && !typeChoice.equals("2")) {
            System.out.println("Invalid type selection.\n");
            return;
        }

        System.out.print("Enter amount: ");
        String amtRaw = scanner.nextLine().replace("$", "").trim();

        double amount;
        try {
            amount = Double.parseDouble(amtRaw);
            if (amount < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.\n");
            return;
        }

        System.out.print("Enter description/reason (press Enter to skip): ");
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) desc = "(no description)";

        if (typeChoice.equals("1")) {
            // demonstrate overloaded method usage
            userBudget.addTransaction("Income", amount, desc);
            System.out.printf("Income added: $%.2f - %s%n%n", amount, desc);
        } else {
            userBudget.addTransaction("Expense", amount, desc);
            System.out.printf("Expense added: $%.2f - %s%n%n", amount, desc);
        }
    }

    private void viewSummary() {
        System.out.println("\n--- Monthly Balance & Summary ---");
        System.out.println(userBudget.getSummary());
    }
}
