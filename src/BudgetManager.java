//Carl Owen, Ty Parham, Alex Kiss
//Semester Project BudgetManager .Java class
/*
* The BudgetManager class is responsible for managing financial data,
*including income, expenses, savings, and transactions. It supports adding transactions,
* calculating totals, and saving/loading transaction data from a file.
* */


import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BudgetManager
{
    private double savings = 0; // Tracks the total savings
    private double income = 0; // Tracks the total income.
    private double totalExpenses = 0; // Tracks the total expenses
    private final List<String> transactions = new ArrayList<>();
    private final String filePath = "transactions.txt";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Formats the date

    public BudgetManager()
    {
        // Load any saved transactions from the file
        loadTransactions();
    }

    public void addTransaction(String transaction)
    {
        String timestamp = dateFormatter.format(new Date());
        transactions.add(timestamp + " - " + transaction); // Add the transaction to the list
        saveTransactions(); // Save the updated list of transactions to the file
    }
// Function resets monthly data by clearing all transactions and resetting totals for income, savings, and expenses
    public void resetMonthlyData()
    {
        transactions.clear(); // Clear all transaction records
        income = 0; // Reset total income
        totalExpenses = 0; // Reset total expenses
        savings = 0; // Reset savings
        saveTransactions();  // Optionally, save the empty state to file
    }

    // Function retrieves all transactions recorded in the BudgetManager
    public List<String> getAllTransactions()
    {
        // Return the list of transactions
        return transactions;
    }

    // Function calculates the total income by summing up all income transactions
    public double getTotalIncome()
    {
        double totalIncome = 0;
        for (String transaction : transactions)
        {
            if (transaction.contains("Income:"))
            {
                // Extract and add the income amount
                totalIncome += Double.parseDouble(transaction.split("\\$")[1].split(" ")[0]);
            }
        }
        return totalIncome;
    }

    public double getTotalExpenses()
    {
        double totalExpenses = 0;
        for (String transaction : transactions)
        {
            if (transaction.contains(": $") && !transaction.contains("Income:") && !transaction.contains("Savings:"))
            {
                // Extract and add the expense amount
                totalExpenses += Double.parseDouble(transaction.split("\\$")[1].split(" ")[0]);
            }
        }
        return totalExpenses;
    }

    // Function sets the total income and adds it as a transaction
    public void setIncome(double income)
    {
        this.income = income; // Update the income field
        addTransaction("Income: $" + income); // Record the income as a transaction
    }

    // Function sets the total savings and adds it as a transaction
    public void setSavings(double savings)
    {
        this.savings = savings; // Update the savings field
        addTransaction("Savings: $" + savings); // Record the savings as a transaction
    }

    //Function adds an expense to the total and records it as a transaction
    public void addExpense(double expense, String type)
    {
        // Update the total expenses
        totalExpenses += expense;
        // Record the expense as a transaction
        addTransaction(type + ": $" + expense);
    }

    // Function retrieves the total savings amount
    public double getSavings()
    {
        return savings; // Return the current savings amount
    }

    // Function reset method to clear all transactions and totals
    public void reset()
    {
        income = 0; // Reset income
        savings = 0; // Reset savings
        totalExpenses = 0; // Reset total expenses
        transactions.clear(); // Clear all transactions
        saveTransactions();  // Optionally save the empty state to file if desired
    }

    // Function saves the list of transaction to the file
    private void saveTransactions()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            for (String transaction : transactions)
            {
                writer.write(transaction); // Write each transaction to the file
                writer.newLine(); // Add a new line for each transaction
            }
        } catch (IOException e)
        {
            System.out.println("Error saving transactions: " + e.getMessage()); // Handle save errors
        }
    }

    // Function loads the list of transaction to the list of transactions
    private void loadTransactions()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                transactions.add(line); // Add the transaction to the list

                // Parse income and expenses for totals dynamically
                if (line.contains("Income:"))
                {
                    income += Double.parseDouble(line.split("\\$")[1].split(" ")[0]);
                } else if (line.contains("Savings:"))
                {
                    savings += Double.parseDouble(line.split("\\$")[1].split(" ")[0]);
                } else if (line.contains(": $") && !line.contains("Income:") && !line.contains("Savings:"))
                {
                    totalExpenses += Double.parseDouble(line.split("\\$")[1].split(" ")[0]);
                }
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("No existing transaction file found. Starting fresh.");
        } catch (IOException e)

        {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}
