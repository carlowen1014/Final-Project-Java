import java.util.ArrayList;
import java.util.Scanner;

public class BudgetManager {
    private double savings = 0;
    private double income = 0;
    private double totalExpenses = 0;
    private final ArrayList<String> transactions = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    public void manageBudget() {
        System.out.println("Welcome to Budget Manager!");

        // Enter Income
        System.out.print("Enter your income: ");
        income = getPositiveInput();
        transactions.add("Income: $" + income);

        // Enter Savings
        System.out.print("Enter your savings: ");
        savings = getPositiveInput();
        transactions.add("Savings: $" + savings);

        // Enter Current Expenses
        System.out.print("Enter your current expenses: ");
        double currentExpense = getPositiveInput();
        totalExpenses += currentExpense;
        transactions.add("Current Expenses: $" + currentExpense);

        // Enter Future Expense
        System.out.print("Enter a future expense: ");
        double futureExpense = getPositiveInput();
        if (futureExpense > savings) {
            System.out.println("I would advise against this transaction.");
        }
        totalExpenses += futureExpense;
        transactions.add("Future Expense: $" + futureExpense);

        // Generate Report
        generateReport();
    }

    private double getPositiveInput() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= 0) {
                    return input;
                } else {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public void generateReport() {
        System.out.println("\n--- Transaction Report ---");
        for (String transaction : transactions) {
            System.out.println(transaction); // Stacked formatting
        }

        // Advisory Notes
        if (totalExpenses > income) {
            System.out.println("\nAdvisory: You should save a little more this upcoming week.");
        } else if (income > totalExpenses) {
            System.out.println("\nAdvisory: You are very productive this week!");
        }

        System.out.println("\n--- End of Report ---");
    }
}
