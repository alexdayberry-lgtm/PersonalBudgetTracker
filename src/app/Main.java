package app;

import core.BudgetTracker;
import model.Transaction;
import storage.Storage;
import util.PasswordValidator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * CLI entrypoint.
 */
public class Main {

    private static final String DATA_PATH = "data/ledger.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Personal Budget Tracker ===");
        System.out.println("Enter a password (>=8 chars, include digit and special char):");
        String pwd = sc.nextLine().trim();
        if (!PasswordValidator.isValid(pwd)) {
            System.out.println("Password does not meet the required conditions. Exiting.");
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
        } catch (IOException e) {
            System.out.println("No existing ledger found (or failed to load).");
        }

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:\n1) Add Income\n2) Add Expense\n3) List Transactions\n4) Summary\n5) Save\n6) Load\n0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
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
                    } catch (IOException e) {
                        System.out.println("Save failed: " + e.getMessage());
                    }
                }
                case "6" -> {
                    try {
                        tracker.loadFromCSV(storage.load());
                        System.out.println("Reloaded from " + DATA_PATH);
                    } catch (IOException e) {
                        System.out.println("Load failed: " + e.getMessage());
                    }
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
        sc.close();
        System.out.println("Goodbye!");
    }

    private static double safeDouble(String s) {
        try { return Double.parseDouble(s.trim()); }
        catch (Exception e) { System.out.println("Invalid number; using 0."); return 0.0; }
    }
}
