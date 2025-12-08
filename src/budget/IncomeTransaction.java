package budget;

/** Subclass specifically representing income. Demonstrates inheritance and super(). */
public class IncomeTransaction extends Transaction {
    public IncomeTransaction(double amount, String description) {
        super("Income", amount, description);
    }
}