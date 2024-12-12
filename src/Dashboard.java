//Carl Owen, Ty Parham, Alex Kiss
//Semester Project Dashboard.Java class
/*
*The Dashboard class represents the main interface for the Personal Finance Manager application
*It allows users to manage income, savings, expenses, and view financial reports
* */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

// Large function represents the main interface for the Personal Finance Manager application
public class Dashboard
{

    // A reference to the BudgetManager instance to manage financial data
    private BudgetManager budgetManager;

    // Constructs a Dashboard instance with the provided BudgetManager
    public Dashboard(BudgetManager budgetManager)
    {
        this.budgetManager = budgetManager;
    }

    //Displays the Dashboard interface to the user
    public void show()
    {
        JFrame frame = new JFrame("Personal Finance Manager"); // Main application window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        frame.setSize(400, 700); // Set the size of the window

        JPanel panel = new JPanel(); // Main container for GUI components
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Arrange components vertically in the panel

        JLabel titleLabel = new JLabel("Dashboard"); // Main title for the dashboard
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font style and size for the title
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the title
        panel.add(titleLabel);

        // Input fields for income, savings, and expenses
        JTextField incomeField = new JTextField(10); // Field for entering income
        JTextField savingsField = new JTextField(10); // Field for entering savings
        JTextField expenseField = new JTextField(10); // Field for entering an expense amount
        JTextField expenseTypeField = new JTextField(10); // Field for entering the type/category of an expense

        // Labels
        panel.add(new JLabel("Enter Income:"));
        panel.add(incomeField);
        panel.add(new JLabel("Enter Savings:"));
        panel.add(savingsField);
        panel.add(new JLabel("Enter Expense Amount:"));
        panel.add(expenseField);
        panel.add(new JLabel("Enter Expense Type:"));
        panel.add(expenseTypeField);

        // Buttons
        JButton addIncomeButton = new JButton("Add Income");
        addIncomeButton.addActionListener(e ->
        {
            try {
                double income = Double.parseDouble(incomeField.getText()); // Parse income input
                budgetManager.setIncome(income); // Update the budget manager with the income
                JOptionPane.showMessageDialog(frame, "Income added!"); // Notify the user
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for income."); // Error message for invalid input
            }
        });
        panel.add(addIncomeButton);

        // Button to add savings
        JButton addSavingsButton = new JButton("Add Savings");
        addSavingsButton.addActionListener(e ->
        {
            try
            {
                double savings = Double.parseDouble(savingsField.getText()); // Parse savings input
                budgetManager.setSavings(savings); // Update the budget manager with the savings
                JOptionPane.showMessageDialog(frame, "Savings added!"); // Notify the user
            } catch (NumberFormatException ex)

            {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for savings."); // Error message for invalid input
            }
        });
        panel.add(addSavingsButton);

        // Button to add an expense
        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e ->
        {
            try
            {
                double expense = Double.parseDouble(expenseField.getText()); // Parse expense input
                String type = expenseTypeField.getText(); // Retrieve expense type
                budgetManager.addExpense(expense, type); // Update the budget manager with the expense

                // Display advisory note if expense exceeds savings for future expenses
                if (type.equalsIgnoreCase("Future Expense") && expense > budgetManager.getSavings())
                {
                    JOptionPane.showMessageDialog(frame, "Advisory: I would advise against this transaction.");
                }

                JOptionPane.showMessageDialog(frame, "Expense added!"); // Notify the user
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for expense."); // Error message for invalid input
            }
        });
        panel.add(addExpenseButton);

        // Reset Button
        JButton resetButton = new JButton("Reset for New Month");
        resetButton.addActionListener(e ->
        {
            int confirmed = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reset for the new month?", "Reset Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION)
            {
                budgetManager.resetMonthlyData(); // Reset the monthly financial data in the budget manager
                JOptionPane.showMessageDialog(frame, "You have reset. You are now ready for your next month."); // Notify the user
            }
        });
        panel.add(resetButton);

        // Report Section
        JTextArea reportArea = new JTextArea(15, 30); // Area to display financial reports
        reportArea.setEditable(false); // Make the report area read-only
        JScrollPane scrollPane = new JScrollPane(reportArea); // Add a scroll pane for the report area
        panel.add(scrollPane);

        // Button to generate a financial report
        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(e -> {
            StringBuilder report = new StringBuilder("--- Transaction Report ---\n");

            // Retrieve all transactions and append to the report
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            for (String transaction : budgetManager.getAllTransactions())
            {
                report.append(transaction).append("\n");
            }

            // Append total income and expenses to the report
            report.append("\nTotal Income: $").append(budgetManager.getTotalIncome());
            report.append("\nTotal Expenses: $").append(budgetManager.getTotalExpenses());

            // Advisory note based on financial performance
            if (budgetManager.getTotalExpenses() > budgetManager.getTotalIncome())
            {
                report.append("\nAdvisory: You should save a little more this upcoming week.");
            } else

            {
                report.append("\nAdvisory: You are very productive this week!");
            }

            reportArea.setText(report.toString()); // Display the report in the text area
        });
        panel.add(generateReportButton);

        frame.add(panel); // Add the panel to the frame
        frame.setVisible(true); // Make the frame visible
    }
}
