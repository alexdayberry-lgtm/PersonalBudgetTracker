package budget;

import java.util.Scanner;

/**
 * Menu system that interacts with the user's budget.
 */
public class BudgetManager {

    private final UserBudget userBudget = new UserBudget();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        int choice;

        do {
            System.out.println("\n=== Budget Menu ===");
            System.out.println("1. Set Monthly Budget");
            System.out.println("2. Add Transaction");
            System.out.println("3. View Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = getIntInput();

            switch (choice) {
                case 1 -> setBudget();
                case 2 -> addTransaction();
                case 3 -> showSummary();
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 4);
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void setBudget() {
        System.out.print("Enter your monthly budget: $");
        double amount = getDoubleInput();
        userBudget.setMonthlyBudget(amount);
        System.out.println("Monthly budget set to $" + amount);
    }

    private void addTransaction() {
        scanner.nextLine(); // flush
        System.out.print("Enter transaction description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: $");
        double amount = getDoubleInput();

        userBudget.addTransaction(description, amount);
        System.out.println("Transaction added.");
    }

    private void showSummary() {
        System.out.println("\n=== Budget Summary ===");
        System.out.println(userBudget.getSummary());
    }

    private double getDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid amount: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
