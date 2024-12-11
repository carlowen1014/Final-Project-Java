import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {

    private BudgetManager budgetManager;

    public Dashboard(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    public void show() {
        JFrame frame = new JFrame("Personal Finance Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Input fields for income, savings, and expenses
        JTextField incomeField = new JTextField(10);
        JTextField savingsField = new JTextField(10);
        JTextField expenseField = new JTextField(10);
        JTextField expenseTypeField = new JTextField(10);

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
        addIncomeButton.addActionListener(e -> {
            try {
                double income = Double.parseDouble(incomeField.getText());
                budgetManager.setIncome(income);
                JOptionPane.showMessageDialog(frame, "Income added!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for income.");
            }
        });
        panel.add(addIncomeButton);

        JButton addSavingsButton = new JButton("Add Savings");
        addSavingsButton.addActionListener(e -> {
            try {
                double savings = Double.parseDouble(savingsField.getText());
                budgetManager.setSavings(savings);
                JOptionPane.showMessageDialog(frame, "Savings added!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for savings.");
            }
        });
        panel.add(addSavingsButton);

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.addActionListener(e -> {
            try {
                double expense = Double.parseDouble(expenseField.getText());
                String type = expenseTypeField.getText();
                budgetManager.addExpense(expense, type);

                // Advisory note for future expenses
                if (type.equalsIgnoreCase("Future Expense") && expense > budgetManager.getSavings()) {
                    JOptionPane.showMessageDialog(frame, "Advisory: I would advise against this transaction.");
                }

                JOptionPane.showMessageDialog(frame, "Expense added!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for expense.");
            }
        });
        panel.add(addExpenseButton);

        // Report Section
        JTextArea reportArea = new JTextArea(15, 30);
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        panel.add(scrollPane);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(e -> {
            StringBuilder report = new StringBuilder("--- Transaction Report ---\n");
            for (String transaction : budgetManager.getAllTransactions()) {
                report.append(transaction).append("\n");
            }
            report.append("\nTotal Income: $").append(budgetManager.getTotalIncome());
            report.append("\nTotal Expenses: $").append(budgetManager.getTotalExpenses());

            // Advisory Notes
            if (budgetManager.getTotalExpenses() > budgetManager.getTotalIncome()) {
                report.append("\nAdvisory: You should save a little more this upcoming week.");
            } else {
                report.append("\nAdvisory: You are very productive this week!");
            }

            reportArea.setText(report.toString());
        });
        panel.add(generateReportButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}