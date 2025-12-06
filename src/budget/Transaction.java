package budget;

/**
 * Represents a single spending transaction.
 */
public class Transaction {

    private final String description;
    private final double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return description + " - $" + amount;
    }
}
