package RMIserver;

import java.util.ArrayList;

public class UserHandler {
    private ArrayList<User> users = null;
    private ArrayList<DataParticipantListener> listeners = new ArrayList<>();

    public UserHandler() {
        this.users = new ArrayList<>();
    }

    public int addUser(User user){
        users.add(user);
        return users.size();
    }

    public int getSize() {
        return users.size();
    }

    public UserHandler(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("\tParticipants: \n");
        for (User u: users)
            b.append(u.toString() + "\n");
        return b.toString();
    }

    public void clear() {
        users.clear();
    }

    public void addDataParticipantListener(DataParticipantListener listener) {
        listeners.add(listener);
    }
}
