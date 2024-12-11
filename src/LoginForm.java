//
//

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    private User user;

    public LoginForm(User user) {
        this.user = user;

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 80, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 150, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 80, 30);
        add(loginButton);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20, 140, 250, 30);
        add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUsername().equals(usernameField.getText()) &&
                        user.validatePassword(new String(passwordField.getPassword()))) {
                    statusLabel.setText("Login successful!");
                    new Dashboard().setVisible(true);
                    dispose();
                } else {
                    statusLabel.setText("Invalid credentials. Try again.");
                }
            }
        });
    }
}

