package budget;

import java.time.LocalDateTime;

/**
 * Abstract base for any financial record (income or expense).
 * Demonstrates abstraction and provides common fields + behavior.
 */
public abstract class FinancialRecord {
    protected final double amount;
    protected final String description;
    protected final LocalDateTime timestamp;

    public FinancialRecord(double amount, String description) {
        this.amount = amount;
        this.description = description == null || description.isBlank() ? "(no description)" : description;
        this.timestamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /** Concrete subclasses must define their type, e.g. "Income" or "Expense". */
    public abstract String getType();

    @Override
    public String toString() {
        return getType() + ": $" + String.format("%.2f", amount) + " - " + description + " (" + timestamp + ")";
    }
}
