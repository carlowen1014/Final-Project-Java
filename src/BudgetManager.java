import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BudgetManager {
    private double savings = 0;
    private double income = 0;
    private double totalExpenses = 0;
    private final List<String> transactions = new ArrayList<>();
    private final String filePath = "transactions.txt";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BudgetManager() {
        loadTransactions();
    }

    public void addTransaction(String transaction) {
        String timestamp = dateFormatter.format(new Date());
        transactions.add(timestamp + " - " + transaction);
        saveTransactions();
    }

    public void resetMonthlyData() {
        transactions.clear();
        income = 0;
        totalExpenses = 0;
        savings = 0;
        saveTransactions();  // Optionally, save the empty state to file
    }


    public List<String> getAllTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (String transaction : transactions) {
            if (transaction.contains("Income:")) {
                totalIncome += Double.parseDouble(transaction.split("\\$")[1].split(" ")[0]);
            }
        }
        return totalIncome;
    }

    public double getTotalExpenses() {
        double totalExpenses = 0;
        for (String transaction : transactions) {
            if (transaction.contains(": $") && !transaction.contains("Income:") && !transaction.contains("Savings:")) {
                totalExpenses += Double.parseDouble(transaction.split("\\$")[1].split(" ")[0]);
            }
        }
        return totalExpenses;
    }

    public void setIncome(double income) {
        this.income = income;
        addTransaction("Income: $" + income);
    }

    public void setSavings(double savings) {
        this.savings = savings;
        addTransaction("Savings: $" + savings);
    }

    public void addExpense(double expense, String type) {
        totalExpenses += expense;
        addTransaction(type + ": $" + expense);
    }

    public double getSavings() {
        return savings;
    }

    // Reset method to clear all transactions and totals
    public void reset() {
        income = 0;
        savings = 0;
        totalExpenses = 0;
        transactions.clear();
        saveTransactions();  // Optionally save the empty state to file if desired
    }

    private void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String transaction : transactions) {
                writer.write(transaction);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);

                // Parse income and expenses for totals dynamically
                if (line.contains("Income:")) {
                    income += Double.parseDouble(line.split("\\$")[1].split(" ")[0]);
                } else if (line.contains("Savings:")) {
                    savings += Double.parseDouble(line.split("\\$")[1].split(" ")[0]);
                } else if (line.contains(": $") && !line.contains("Income:") && !line.contains("Savings:")) {
                    totalExpenses += Double.parseDouble(line.split("\\$")[1].split(" ")[0]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing transaction file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}
