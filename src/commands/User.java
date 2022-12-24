package commands;

import java.util.HashMap;
import java.util.Map;
public class User {

    private final Map<String, User> users = new HashMap<>();

    public final void addUser(final String string, final User user) {
        this.users.put(string, user);
    }

    public final Map<String, User> getUsers() {
        return this.users;
    }
}
