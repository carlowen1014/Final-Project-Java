//Carl Owen, Ty Parham, Alex Kiss
//Semester Project User.Java class
/* The User class represents a user in the system with a username and password.
This class provides methods to retrieve the username and validate the user's password.
It serves as a core component for managing user authentication.
*/

public class User
{
    // Private fields to store the username and password of the user.
    private String username; // The username associated with the user.
    private String password; // The password for the user, stored securely.

    public User(String username, String password)
    {
        // Assign the provided username to the class's username field.
        this.username = username;
        // Assign the provided password to the class's password field.
        this.password = password;
    }

    public String getUsername()
    {
        // Return the value of the username field.
        return username;
    }

    public boolean validatePassword(String password)
    {
        // Check if the provided password matches the stored password using the equals() method.
        return this.password.equals(password);
    }
}

