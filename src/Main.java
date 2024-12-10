package main;

import model.User;
import view.LoginForm;

public class Main {
    public static void main(String[] args) {
        User user = new User("admin", "password");
        new LoginForm(user).setVisible(true);
    }
}
