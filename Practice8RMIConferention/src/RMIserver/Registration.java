package RMIserver;

import java.rmi.RemoteException;

public class Registration implements Conferention {

    private volatile UserHandler users;

    public Registration() throws RemoteException {
        users = new UserHandler();
    }

    public UserHandler getData() {
        return users;
    }

    @Override
    public synchronized int register(User user) throws RemoteException {
        users.addUser(user);
        return users.getSize();
    }

    @Override
    public String getInfo() {
        return users.toString();
    }

    @Override
    public int getSize() {
        return users.getSize();
    }

}
