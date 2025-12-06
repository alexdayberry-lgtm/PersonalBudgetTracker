package budget;

import java.util.ArrayList;

/**
 * Stores and manages user budget data and transactions.
 */
public class UserBudget {

    private double monthlyBudget = 0;
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public void setMonthlyBudget(double amount) {
        this.monthlyBudget = amount;
    }

    public void addTransaction(String description, double amount) {
        transactions.add(new Transaction(description, amount));
    }

    public double getTotalSpent() {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }

    public double getRemaining() {
        return monthlyBudget - getTotalSpent();
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monthly Budget: $").append(monthlyBudget).append("\n");
        sb.append("Total Spent: $").append(getTotalSpent()).append("\n");
        sb.append("Remaining: $").append(getRemaining()).append("\n\n");

        sb.append("Transactions:\n");
        if (transactions.isEmpty()) {
            sb.append("No transactions recorded.\n");
        } else {
            for (Transaction t : transactions) {
                sb.append("- ").append(t).append("\n");
            }
        }

        return sb.toString();
    }
}
