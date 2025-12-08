package budget;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages user's budget, transactions, and provides summaries.
 * Demonstrates arrays, method overloading, Math usage, encapsulation, static helpers.
 */
public class UserBudget {

    // constant
    public static final int MONTHS_IN_YEAR = 12;

    // fields
    private double monthlyBudget = 0.0;
    private final List<Transaction> transactions = new ArrayList<>();

    // primitive array to demonstrate requirement #11 (Array). Tracks monthly expense totals by month index (0-11).
    private final double[] monthlyExpenses = new double[MONTHS_IN_YEAR];

    public UserBudget() {
        // initialize array explicitly (not required in Java, but clearer)
        for (int i = 0; i < monthlyExpenses.length; i++) {
            monthlyExpenses[i] = 0.0;
        }
    }

    public void setMonthlyBudget(double amount) {
        this.monthlyBudget = amount;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    /**
     * Overloaded addTransaction methods demonstrate method overloading:
     *  - one accepts type+amount (no description)
     *  - one accepts type+amount+description
     *  - one accepts a Transaction object directly
     */

    // 1) minimal: no description
    public void addTransaction(String type, double amount) {
        addTransaction(type, amount, "(no description)");
    }

    // 2) full signature
    public void addTransaction(String type, double amount, String description) {
        Transaction t;
        if ("Income".equalsIgnoreCase(type)) {
            t = new IncomeTransaction(amount, description);
        } else {
            t = new ExpenseTransaction(amount, description);
            // put into monthlyExpenses using the transaction timestamp month
            int monthIndex = t.getTimestamp().getMonthValue() - 1; // 0-based
            monthlyExpenses[monthIndex] += amount; // accumulate expense into primitive array
        }
        transactions.add(t);
    }

    // 3) accept existing Transaction / FinancialRecord
    public void addTransaction(Transaction t) {
        if (t == null) return;
        transactions.add(t);
        if ("Expense".equalsIgnoreCase(t.getType())) {
            int monthIndex = t.getTimestamp().getMonthValue() - 1;
            monthlyExpenses[monthIndex] += t.getAmount();
        }
    }

    public double getTotalIncome() {
        double sum = 0.0;
        for (Transaction t : transactions) {
            if ("Income".equalsIgnoreCase(t.getType()))
                sum += t.getAmount();
        }
        return sum;
    }

    public double getTotalExpenses() {
        double sum = 0.0;
        for (Transaction t : transactions) {
            if ("Expense".equalsIgnoreCase(t.getType()))
                sum += t.getAmount();
        }
        return sum;
    }

    /**
     * Demonstrates Math class usage and arithmetic operators.
     * remaining = monthlyBudget + totalIncome - totalExpenses
     */
    public double getRemaining() {
        double remaining = monthlyBudget + getTotalIncome() - getTotalExpenses();
        // show usage of Math.round and Math.max as examples
        return Math.round(remaining * 100.0) / 100.0;
    }

    /**
     * Demonstrates use of a primitive array: calculate average monthly expense.
     */
    public double getAverageMonthlyExpense() {
        double total = 0.0;
        for (double v : monthlyExpenses) total += v;
        return monthlyExpenses.length == 0 ? 0.0 : total / monthlyExpenses.length;
    }

    /**
     * Example method showing pass-by-value semantics: takes primitive and returns modified amount.
     * Note: this doesn't mutate the caller's variable.
     */
    public double applyProcessingFee(double amount, double feePercent) {
        double fee = amount * (feePercent / 100.0);
        return amount - fee; // caller must use returned value
    }

    /**
     * Generates a multi-line formatted summary (uses ternary conditional operator).
     */
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monthly Budget: $").append(String.format("%.2f", monthlyBudget)).append("\n");
        sb.append("Total Income: $").append(String.format("%.2f", getTotalIncome())).append("\n");
        sb.append("Total Expenses: $").append(String.format("%.2f", getTotalExpenses())).append("\n");

        double remaining = getRemaining();
        // ternary operator
        String status = remaining >= 0 ? "Surplus" : "Deficit";
        sb.append("Remaining: $").append(String.format("%.2f", remaining)).append(" (").append(status).append(")\n\n");

        sb.append("Average Monthly Expense (from primitive array): $")
                .append(String.format("%.2f", getAverageMonthlyExpense())).append("\n\n");

        sb.append("Transactions:\n");
        if (transactions.isEmpty()) {
            sb.append("No transactions recorded.\n");
        } else {
            for (Transaction t : transactions) {
                sb.append("- ").append(t.toString()).append("\n");
            }
        }
        sb.append("\nTotal Transactions created: ").append(Transaction.getTotalTransactions()).append("\n");
        return sb.toString();
    }
}
