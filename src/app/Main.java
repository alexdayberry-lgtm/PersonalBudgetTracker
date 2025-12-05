package app;

import core.BudgetTracker;
import core.InvalidDataException;
import core.StorageException;
import model.Transaction;
import storage.Storage;
import util.PasswordValidator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main CLI entrypoint for the Personal Budget Tracker.
 * <p>
 * This program requires a valid password at startup (see Task 2).
 * It supports adding incomes/expenses, listing transactions,
 * saving/loading ledger to CSV, and showing summary totals.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final String DATA_PATH = "data/ledger.csv";

    public static void main(String[] args) {
        logger.info("Starting Personal Budget Tracker");
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Personal Budget Tracker ===");
        System.out.println("Enter a password (>=8 chars, include digit and special char):");
        String pwd = sc.nextLine().trim();
        if (!PasswordValidator.isValid(pwd)) {
            System.out.println("Password does not meet the required conditions. Exiting.");
            logger.info("Invalid password provided; exiting.");
            sc.close();
            return;
        }
        System.out.println("Password accepted.");

        BudgetTracker tracker = new BudgetTracker();
        Storage storage = new Storage(DATA_PATH);

        try {
            List<String> lines = storage.load();
            tracker.loadFromCSV(lines);
            if (!lines.isEmpty()) System.out.println("Loaded " + lines.size() + " transactions.");
            logger.info("Loaded " + lines.size() + " transactions from " + DATA_PATH);
        } catch (IOException e) {
            System.out.println("No existing ledger found (or failed to load).");
            logger.log(Level.WARNING, "Could not load ledger: " + e.getMessage(), e);
        } catch (StorageException e) {
            System.out.println("Storage error while loading: " + e.getMessage());
            logger.log(Level.SEVERE, "Storage exception while loading", e);
        }

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:\n1) Add Income\n2) Add Expense\n3) List Transactions\n4) Summary\n5) Save\n6) Load\n0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Amount: ");
                        double amt = safeDouble(sc.nextLine());
                        System.out.print("Category: ");
                        String cat = sc.nextLine();
                        System.out.print("Note: ");
                        String note = sc.nextLine();
                        tracker.addIncome(amt, cat, note);
                        System.out.println("Income added.");
                        logger.info("Income added: " + amt + " | " + cat);
                    }
                    case "2" -> {
                        System.out.print("Amount: ");
                        double amt = safeDouble(sc.nextLine());
                        System.out.print("Category: ");
                        String cat = sc.nextLine();
                        System.out.print("Note: ");
                        String note = sc.nextLine();
                        tracker.addExpense(amt, cat, note);
                        System.out.println("Expense added.");
                        logger.info("Expense added: " + amt + " | " + cat);
                    }
                    case "3" -> {
                        List<Transaction> txs = tracker.getTransactions();
                        if (txs.isEmpty()) System.out.println("(No transactions)");
                        else {
                            int i = 1;
                            for (Transaction t : txs) System.out.println((i++) + ") " + t);
                        }
                    }
                    case "4" -> {
                        System.out.printf("Balance: $%.2f%n", tracker.getBalance());
                        Map<String, Double> totals = tracker.totalsByCategory();
                        if (totals.isEmpty()) System.out.println("(No totals)");
                        else totals.forEach((k, v) -> System.out.printf("%s => $%.2f%n", k, v));
                    }
                    case "5" -> {
                        try {
                            storage.save(tracker.toCSVLines());
                            System.out.println("Saved to " + DATA_PATH);
                            logger.info("Saved ledger to " + DATA_PATH);
                        } catch (IOException e) {
                            System.out.println("Save failed: " + e.getMessage());
                            logger.log(Level.SEVERE, "Save failed", e);
                        } catch (StorageException e) {
                            System.out.println("Storage error while saving: " + e.getMessage());
                            logger.log(Level.SEVERE, "Storage exception on save", e);
                        }
                    }
                    case "6" -> {
                        try {
                            tracker.loadFromCSV(storage.load());
                            System.out.println("Reloaded from " + DATA_PATH);
                            logger.info("Reloaded ledger from " + DATA_PATH);
                        } catch (IOException e) {
                            System.out.println("Load failed: " + e.getMessage());
                            logger.log(Level.SEVERE, "Load failed", e);
                        } catch (StorageException e) {
                            System.out.println("Storage error while loading: " + e.getMessage());
                            logger.log(Level.SEVERE, "Storage exception on load", e);
                        }
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (InvalidDataException ide) {
                System.out.println("Invalid input: " + ide.getMessage());
                logger.log(Level.WARNING, "InvalidDataException: " + ide.getMessage(), ide);
            } catch (Exception ex) {
                System.out.println("An unexpected error occurred: " + ex.getMessage());
                logger.log(Level.SEVERE, "Unexpected error", ex);
            }
        }
        sc.close();
        logger.info("Exiting application.");
        System.out.println("Goodbye!");
    }

    /**
     * Safely parse a double; prints a message and returns 0.0 if parsing fails.
     */
    private static double safeDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            System.out.println("Invalid number; using 0.");
            return 0.0;
        }
    }
}

