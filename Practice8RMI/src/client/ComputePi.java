package client;

import compute.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;


public class ComputePi {
    public static void main(String args[]) {
        System.setProperty("java.security.policy", "program.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
        Registry registry = LocateRegistry.getRegistry(args[0]);
        String name = "Compute";
        Compute comp = (Compute) registry.lookup(name);
        Pi task = new Pi(Integer.parseInt(args[1]));
        BigDecimal pi = comp.executeTask(task);
        System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:"); e.printStackTrace();
        }
    }
}

