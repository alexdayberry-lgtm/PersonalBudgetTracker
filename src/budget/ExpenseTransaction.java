package budget;

/** Subclass specifically representing expense. Demonstrates inheritance and super(). */
public class ExpenseTransaction extends Transaction {
    public ExpenseTransaction(double amount, String description) {
        super("Expense", amount, description);
    }
}
