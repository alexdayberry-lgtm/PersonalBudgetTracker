package budget;

/**
 * Represents a single transaction (income or expense).
 */
public class Transaction {
    private final String type; // "Income" or "Expense"
    private final double amount; // positive number
    private final String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return type + ": $" + String.format("%.2f", amount) + " - " + description;
    }
}
