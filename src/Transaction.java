//
//

import java.time.LocalDate;

public class Transaction {
    private String description;
    private double amount;
    private LocalDate date;
    private String category;
    private boolean isIncome;

    public Transaction(String description, double amount, LocalDate date, String category, boolean isIncome) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.isIncome = isIncome;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIncome() {
        return isIncome;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f | %s | %s",
                date, description, amount, category, isIncome ? "Income" : "Expense");
    }
}

