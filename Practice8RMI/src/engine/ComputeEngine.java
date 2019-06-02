package engine;


import compute.Compute;
import compute.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine implements Compute {
    public ComputeEngine() {
        super();
    }

    public <T> T executeTask(Task<T> t) throws RemoteException {
        return t.execute();
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "program.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Compute engine = new ComputeEngine();
        try {
            Compute stub = (Compute) UnicastRemoteObject.
                    exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            String name = "Compute";
            registry.rebind(name, stub);
            System.out.println("ComputeEngine is ready to work");
        } catch (RemoteException e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}

