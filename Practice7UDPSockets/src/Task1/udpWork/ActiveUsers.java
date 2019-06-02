package Task1.udpWork;

import java.io.Serializable;
import java.util.ArrayList;

public class ActiveUsers implements Serializable {
    private ArrayList<User> users;

    public ActiveUsers() {
        this.users = new ArrayList<>();
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean isEmpty() {
        if (users.isEmpty()) return true;
        return false;
    }

    public int size() {
        return users.size();
    }

    public boolean contains(User u) {
        if (users.contains(u)) return true;
        return false;
    }

    public User get(int ind) {
        return users.get(ind);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (User u : users)
            builder.append(u.toString() + "\n");
        return builder.toString();
    }
}
