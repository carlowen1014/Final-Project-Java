//Carl Owen, Ty Parham, Alex Kiss
//Semester Project Transaction.java class
/*
*The Transaction class represents a single financial transaction.
*It contains details such as description, amount, date, category, and whether it is income or an expense.
*/

import java.time.LocalDate;


public class Transaction
{
    private String description; // Description of the transaction (e.g., "Grocery shopping")
    private double amount; // Amount of the transaction
    private LocalDate date; // Date when the transaction occurred
    private String category; // Category of the transaction (e.g., "Food", "Utilities")
    private boolean isIncome; // Flag to indicate if the transaction is income (true) or an expense (false)
// Function initaliazes a transaction object with specific details
    public Transaction(String description, double amount, LocalDate date, String category, boolean isIncome)
    {
        this.description = description; // Assign the description
        this.amount = amount; // Assign the amount
        this.date = date; // Assign the date
        this.category = category; // Assign the category
        this.isIncome = isIncome; // Assign the income/expense flag
    }
//Function gets the description of the transaction
    public String getDescription()
    {
        // Return the description
        return description;
    }

    public double getAmount()
    {
        // Return the transaction amount
        return amount;
    }

    public LocalDate getDate()
    {
        // Return the transaction date
        return date;
    }

    public String getCategory()
    {
        // Return the category
        return category;
    }

    public boolean isIncome()
    {
        // Return whether it's income
        return isIncome;
    }
// Converts the transaction details into a formatted string representation
    @Override
    public String toString()
    {
        // Format the transaction details for easy readability
        return String.format("%s | %s | %.2f | %s | %s",
                date, description, amount, category, isIncome ? "Income" : "Expense");
    }
}

