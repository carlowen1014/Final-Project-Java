import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private final String validUsername = "admin"; // Replace with actual validation logic
    private final String validPassword = "password"; // Replace with actual validation logic

    public void show() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");

        // Login Button Action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals(validUsername) && password.equals(validPassword)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");

                    // Create BudgetManager instance and pass it to Dashboard
                    BudgetManager budgetManager = new BudgetManager();
                    Dashboard dashboard = new Dashboard(budgetManager);
                    dashboard.show();

                    // Close the login frame
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.show();
    }
}


