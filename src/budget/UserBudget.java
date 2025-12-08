package budget;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a user’s monthly budget and all associated financial transactions.
 * <p>
 * This class stores income, expenses, and the overall monthly budget while
 * providing detailed summaries and calculations.
 */
public class UserBudget {

    private double monthlyBudget = 0.0;
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Sets the user’s monthly budget.
     *
     * @param amount the budget amount for the month
     */
    public void setMonthlyBudget(double amount) {
        this.monthlyBudget = amount;
    }

    /**
     * @return the current monthly budget
     */
    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    /**
     * Adds a new transaction, either income or expense, to the log.
     *
     * @param type        "Income" or "Expense"
     * @param amount      a positive amount representing the transaction
     * @param description a brief explanation of the transaction
     */
    public void addTransaction(String type, double amount, String description) {
        transactions.add(new Transaction(type, amount, description));
    }

    /**
     * Calculates the total recorded income.
     *
     * @return the sum of all income transactions
     */
    public double getTotalIncome() {
        double sum = 0.0;
        for (Transaction t : transactions)
            if ("Income".equals(t.getType()))
                sum += t.getAmount();
        return sum;
    }

    /**
     * Calculates the total recorded expenses.
     *
     * @return the sum of all expense transactions
     */
    public double getTotalExpenses() {
        double sum = 0.0;
        for (Transaction t : transactions)
            if ("Expense".equals(t.getType()))
                sum += t.getAmount();
        return sum;
    }

    /**
     * Computes the remaining balance:
     * <p>
     * {@code remaining = monthlyBudget + totalIncome - totalExpenses}
     *
     * @return the amount remaining for the month
     */
    public double getRemaining() {
        return monthlyBudget + getTotalIncome() - getTotalExpenses();
    }

    /**
     * Generates a full summary of the user’s budget, including all transactions,
     * totals, and remaining balance.
     *
     * @return a multi-line formatted budget summary
     */
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monthly Budget: $").append(String.format("%.2f", monthlyBudget)).append("\n");
        sb.append("Total Income: $").append(String.format("%.2f", getTotalIncome())).append("\n");
        sb.append("Total Expenses: $").append(String.format("%.2f", getTotalExpenses())).append("\n");
        sb.append("Remaining: $").append(String.format("%.2f", getRemaining())).append("\n\n");

        sb.append("Transactions:\n");
        if (transactions.isEmpty()) {
            sb.append("No transactions recorded.\n");
        } else {
            for (Transaction t : transactions) {
                sb.append("- ").append(t.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
