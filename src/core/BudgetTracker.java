package core;

import model.Transaction;
import java.util.*;

/**
 * Core budget tracker logic.
 */
public class BudgetTracker {

    private final List<Transaction> transactions = new ArrayList<>();

    public void addIncome(double amount, String category, String note) {
        transactions.add(new Transaction("INCOME", amount, category, note, System.currentTimeMillis()));
    }

    public void addExpense(double amount, String category, String note) {
        transactions.add(new Transaction("EXPENSE", amount, category, note, System.currentTimeMillis()));
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public double getBalance() {
        double bal = 0.0;
        for (Transaction t : transactions) {
            if ("INCOME".equalsIgnoreCase(t.getType())) bal += t.getAmount();
            else bal -= t.getAmount();
        }
        return bal;
    }

    public Map<String, Double> totalsByCategory() {
        Map<String, Double> totals = new HashMap<>();
        for (Transaction t : transactions) {
            String key = t.getType() + ":" + t.getCategory();
            totals.put(key, totals.getOrDefault(key, 0.0) + t.getAmount());
        }
        return totals;
    }

    public List<String> toCSVLines() {
        List<String> lines = new ArrayList<>();
        for (Transaction t : transactions) lines.add(t.toCSV());
        return lines;
    }

    public void loadFromCSV(List<String> lines) {
        transactions.clear();
        for (String l : lines) {
            if (l != null && !l.trim().isEmpty()) transactions.add(Transaction.fromCSV(l));
        }
    }
}
