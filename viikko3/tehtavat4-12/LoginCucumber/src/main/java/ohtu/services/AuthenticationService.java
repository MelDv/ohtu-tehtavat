package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (invalid(username, password)) {
            return false;
        } else {
            userDao.add(new User(username, password));
        }

        return true;
    }

    private boolean invalid(String username, String password) {
        User u = userDao.findByName(username);
        if (u != null) {
            return true;
        }
        if (username.length() < 3) {
            return true;
        }
        if (password.length() < 8) {
            return true;
        }
        //test if username contains only a-z and password special characters
        if (!Pattern.matches("[a-zA-Z]+", username)) {
            return true;
        }
        Pattern p = Pattern.compile("[^A-Za-z]");
        Matcher m = p.matcher(password);
        boolean b = m.find();
        if (!b) {
            return true;
        }

        return false;
    }
}
