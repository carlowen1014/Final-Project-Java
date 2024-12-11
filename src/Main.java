//Carl Owen, Ty Parham, Alex Kiss
//Semester Project main
//The purpose of the program



public class Main {
    public static void main(String[] args) {
        User user = new User("admin", "password");
        new LoginForm(user).setVisible(true);
    }
}
