package commands;

import java.util.HashMap;
import java.util.Map;
public class User {

    private final Map<String, User> users = new HashMap<>();

    public void addUser(String string, User user) {
        this.users.put(string, user);
    }

    public Map<String, User> getUsers() {
        return this.users;
    }
}
