package budget;

import java.util.Scanner;

/**
 * Manages the main flow of the budgeting application, including menu
 * navigation, user input handling, and interactions with {@link UserBudget}.
 * <p>
 * This class is responsible for reading user choices, directing users to
 * budget setup, transaction entry, and summary viewing.
 */
public class BudgetManager {

    private final Scanner scanner;
    private final UserBudget userBudget;

    /**
     * Constructs a {@code BudgetManager} using a shared {@link Scanner} instance.
     *
     * @param scanner the scanner used for reading user input
     */
    public BudgetManager(Scanner scanner) {
        this.scanner = scanner;
        this.userBudget = new UserBudget();
    }

    /**
     * Runs the main application loop and processes menu selections until
     * the user chooses to exit.
     */
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

    /**
     * Prints the main menu options for the user.
     */
    private void printMainMenu() {
        System.out.println("===== Budget Menu =====");
        System.out.println("1. Set Monthly Budget");
        System.out.println("2. Transactions (Income / Expense)");
        System.out.println("3. View Monthly Balance & Summary");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Prompts the user to enter a monthly budget amount and updates the
     * {@link UserBudget} instance with the new value.
     */
    private void setMonthlyBudget() {
        System.out.print("Enter desired monthly budget amount: ");
        String raw = scanner.nextLine().replace("$", "").trim();
        try {
            double amount = Double.parseDouble(raw);
            if (amount < 0) throw new NumberFormatException();
            userBudget.setMonthlyBudget(amount);
            System.out.println("Monthly budget set to $" + String.format("%.2f", amount) + "\n");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Monthly budget not changed.\n");
        }
    }

    /**
     * Displays the transaction menu, validates the user’s selection,
     * and collects transaction data such as amount and description.
     * Adds the transaction as either income or expense.
     */
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

        System.out.print("Enter description/reason: ");
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) desc = "(no description)";

        if (typeChoice.equals("1")) {
            userBudget.addTransaction("Income", amount, desc);
            System.out.println("Income added: $" + String.format("%.2f", amount) + " - " + desc + "\n");
        } else {
            userBudget.addTransaction("Expense", amount, desc);
            System.out.println("Expense added: $" + String.format("%.2f", amount) + " - " + desc + "\n");
        }
    }

    /**
     * Displays a full financial summary including monthly budget,
     * total income, total expenses, and remaining balance.
     */
    private void viewSummary() {
        System.out.println("\n--- Monthly Balance & Summary ---");
        System.out.println(userBudget.getSummary());
    }
}