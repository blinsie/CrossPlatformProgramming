package RMIserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Conferention extends Remote {

    int register(User user) throws RemoteException;

    String getInfo() throws RemoteException;

    int getSize() throws RemoteException;

}
