//
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Dashboard extends JFrame {
    private BudgetManager budgetManager;

    public Dashboard() {
        this.budgetManager = new BudgetManager();

        setTitle("Personal Finance Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea transactionsArea = new JTextArea();
        transactionsArea.setEditable(false);
        add(new JScrollPane(transactionsArea), BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        JButton addButton = new JButton("Add Transaction");
        JButton reportButton = new JButton("Generate Report");

        controlsPanel.add(addButton);
        controlsPanel.add(reportButton);
        add(controlsPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = JOptionPane.showInputDialog("Description:");
                String category = JOptionPane.showInputDialog("Category:");
                double amount = Double.parseDouble(JOptionPane.showInputDialog("Amount:"));
                boolean isIncome = JOptionPane.showConfirmDialog(null, "Is it Income?") == JOptionPane.YES_OPTION;
                LocalDate date = LocalDate.now();

                Transaction t = new Transaction(desc, amount, date, category, isIncome);
                budgetManager.addTransaction(t);

                transactionsArea.setText(budgetManager.getAllTransactions().toString());
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double income = budgetManager.getTotalIncome();
                double expenses = budgetManager.getTotalExpenses();
                JOptionPane.showMessageDialog(null, String.format("Income: $%.2f\nExpenses: $%.2f", income, expenses));
            }
        });
    }
}
