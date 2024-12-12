//Carl Owen, Ty Parham, Alex Kiss
//Semester Project LoginForm.Java class
/*
*The LoginForm class provides a simple graphical user interface for logging into the application
*It verifies the user's credentials and transitions to the Dashboard upon successful login
* */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginForm
{
    // Hardcoded valid username for login validation
    private final String validUsername = "admin"; // Replace with actual validation logic
    private final String validPassword = "password"; // Replace with actual validation logic

    public void show()
    {
        JFrame frame = new JFrame("Login"); // Create the main login window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
        frame.setSize(300, 200); // Set the size of the frame

        JPanel panel = new JPanel(); // Create a panel to hold the components
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Arrange components vertically in the panel

        JLabel usernameLabel = new JLabel("Username:"); // Label for the username field
        JTextField usernameField = new JTextField(15); // Input field for the username (15 columns wide)
        JLabel passwordLabel = new JLabel("Password:"); // Label for the password field
        JPasswordField passwordField = new JPasswordField(15); // Input field for the password (masked, 15 columns wide)

        JButton loginButton = new JButton("Login");

        // Login Button Action
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Retrieve the entered username and password from the input fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // Convert the password from char[] to String

                // Validate the entered credentials against the hardcoded values
                if (username.equals(validUsername) && password.equals(validPassword))
                {
                    JOptionPane.showMessageDialog(frame, "Login successful!");

                    // Create an instance of BudgetManager and pass it to the Dashboard for further operations
                    BudgetManager budgetManager = new BudgetManager();
                    Dashboard dashboard = new Dashboard(budgetManager);
                    dashboard.show(); // Show the dashboard

                    // Close the login frame
                    frame.dispose(); // Close the login frame after successful login
                } else
                {
                    // Display an error message if the credentials are invalid
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });

        // Add components to the panel in sequence
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        // Add the panel to the frame and make the frame visible
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        // Create an instance of LoginForm
        LoginForm loginForm = new LoginForm();
        // Display the login form
        loginForm.show();
    }
}
