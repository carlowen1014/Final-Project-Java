import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private double savings = 0;
    private double income = 0;
    private double totalExpenses = 0;
    private final List<String> transactions = new ArrayList<>();
    private final String filePath = "transactions.txt";

    public BudgetManager() {
        loadTransactions();
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    public List<String> getAllTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        return income;
    }

    public double getTotalExpenses() {
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

                // Parse income and expenses for totals
                if (line.startsWith("Income:")) {
                    income += Double.parseDouble(line.split("\\$")[1]);
                } else if (line.startsWith("Savings:")) {
                    savings += Double.parseDouble(line.split("\\$")[1]);
                } else if (line.contains(": $")) {
                    totalExpenses += Double.parseDouble(line.split("\\$")[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing transaction file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}


