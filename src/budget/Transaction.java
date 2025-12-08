package budget;

/**
 * Represents a single financial transaction, either an income or expense,
 * including its type, amount, and description.
 */
public class Transaction {

    private final String type;        // "Income" or "Expense"
    private final double amount;      // positive number
    private final String description;

    /**
     * Constructs a transaction with the specified details.
     *
     * @param type        the transaction type ("Income" or "Expense")
     * @param amount      the monetary value of the transaction
     * @param description a brief explanation or label for the transaction
     */
    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    /**
     * @return the type of transaction
     */
    public String getType() {
        return type;
    }

    /**
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the description of the transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a formatted string representation of the transaction.
     *
     * @return transaction details as a readable string
     */
    @Override
    public String toString() {
        return type + ": $" + String.format("%.2f", amount) + " - " + description;
    }
}
