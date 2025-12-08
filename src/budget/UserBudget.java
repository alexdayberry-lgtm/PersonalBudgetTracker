package budget;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores user's monthly budget and transactions.
 */
public class UserBudget {

    private double monthlyBudget = 0.0;
    private final List<Transaction> transactions = new ArrayList<>();

    public void setMonthlyBudget(double amount) {
        this.monthlyBudget = amount;
    }

    public double getMonthlyBudget() { return monthlyBudget; }

    /**
     * Add transaction: for income provide type "Income" and positive amount;
     * for expense provide type "Expense" and positive amount.
     */
    public void addTransaction(String type, double amount, String description) {
        transactions.add(new Transaction(type, amount, description));
    }

    public double getTotalIncome() {
        double sum = 0.0;
        for (Transaction t : transactions) if ("Income".equals(t.getType())) sum += t.getAmount();
        return sum;
    }

    public double getTotalExpenses() {
        double sum = 0.0;
        for (Transaction t : transactions) if ("Expense".equals(t.getType())) sum += t.getAmount();
        return sum;
    }

    /**
     * Remaining = monthlyBudget + totalIncome - totalExpenses
     */
    public double getRemaining() {
        return monthlyBudget + getTotalIncome() - getTotalExpenses();
    }

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
