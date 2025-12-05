package core;

import model.Transaction;

import java.util.*;

/**
 * Core budget tracker logic.
 *
 * Handles in-memory list of transactions, summary calculations,
 * and provides methods to export and import CSV lines.
 */
public class BudgetTracker {

    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Add income to the ledger. Amount must be > 0.
     *
     * @param amount   positive amount
     * @param category brief category
     * @param note     optional note
     * @throws InvalidDataException if amount <= 0
     */
    public void addIncome(double amount, String category, String note) throws InvalidDataException {
        if (amount <= 0) throw new InvalidDataException("Income amount must be positive.");
        transactions.add(new Transaction("INCOME", amount, category, note, System.currentTimeMillis()));
    }

    /**
     * Add expense to the ledger. Amount must be > 0.
     *
     * @param amount   positive amount
     * @param category brief category
     * @param note     optional note
     * @throws InvalidDataException if amount <= 0
     */
    public void addExpense(double amount, String category, String note) throws InvalidDataException {
        if (amount <= 0) throw new InvalidDataException("Expense amount must be positive.");
        transactions.add(new Transaction("EXPENSE", amount, category, note, System.currentTimeMillis()));
    }

    /**
     * Returns a copy of the current transaction list.
     */
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    /**
     * Returns the current balance (income - expenses).
     */
    public double getBalance() {
        double bal = 0.0;
        for (Transaction t : transactions) {
            if ("INCOME".equalsIgnoreCase(t.getType())) bal += t.getAmount();
            else bal -= t.getAmount();
        }
        return bal;
    }

    /**
     * Returns totals grouped by "TYPE:CATEGORY" (e.g. INCOME:Paycheck).
     */
    public Map<String, Double> totalsByCategory() {
        Map<String, Double> totals = new HashMap<>();
        for (Transaction t : transactions) {
            String key = t.getType() + ":" + t.getCategory();
            totals.put(key, totals.getOrDefault(key, 0.0) + t.getAmount());
        }
        return totals;
    }

    /**
     * Convert transactions to CSV lines (one transaction per line).
     */
    public List<String> toCSVLines() {
        List<String> lines = new ArrayList<>();
        for (Transaction t : transactions) lines.add(t.toCSV());
        return lines;
    }

    /**
     * Replace in-memory transactions with those parsed from CSV lines.
     *
     * @param lines CSV formatted lines
     */
    public void loadFromCSV(List<String> lines) {
        transactions.clear();
        for (String l : lines) {
            if (l != null && !l.trim().isEmpty()) transactions.add(Transaction.fromCSV(l));
        }
    }
}

