package model;

/**
 * Represents a single immutable financial transaction in the budget tracker.
 */
public class Transaction {
    private final String type;     // INCOME or EXPENSE
    private final double amount;
    private final String category;
    private final String note;
    private final long timestamp;

    public Transaction(String type, double amount, String category, String note, long timestamp) {
        this.type = type;
        this.amount = amount;
        this.category = category == null ? "" : category;
        this.note = note == null ? "" : note;
        this.timestamp = timestamp;
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public long getTimestamp() { return timestamp; }

    public String toCSV() {
        return String.format("%s,%f,%s,%s,%d",
                type, amount,
                category.replace(",", ";"),
                note.replace(",", ";"),
                timestamp);
    }

    public static Transaction fromCSV(String csv) {
        String[] parts = csv.split(",", 5);
        if (parts.length < 5) throw new IllegalArgumentException("Bad CSV: " + csv);
        return new Transaction(
                parts[0],
                Double.parseDouble(parts[1]),
                parts[2],
                parts[3],
                Long.parseLong(parts[4])
        );
    }

    @Override
    public String toString() {
        return String.format("%s | $%.2f | %s | %s | %d", type, amount, category, note, timestamp);
    }
}

