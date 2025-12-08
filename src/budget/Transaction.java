package budget;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

/**
 * General transaction implementation that extends FinancialRecord.
 * Demonstrates inheritance, use of super, static variables/methods, and Random.
 */
public class Transaction extends FinancialRecord {

    // static counter shared across all transactions
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    // unique ID for this transaction (demonstrates Random + constructor logic)
    private final String id;
    private final String type; // "Income" or "Expense"

    /**
     * Constructs a Transaction. Uses super() to initialize FinancialRecord fields.
     */
    public Transaction(String type, double amount, String description) {
        super(amount, description);
        this.type = type;
        // generate a simple pseudo-random id combined with an incrementing counter
        this.id = "TX-" + COUNTER.incrementAndGet() + "-" + new Random().nextInt(9000 + 1000);
    }

    @Override
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    /**
     * Static method exposing how many Transaction objects have been created.
     */
    public static int getTotalTransactions() {
        return COUNTER.get();
    }

    @Override
    public String toString() {
        return "[" + id + "] " + super.toString();
    }
}
