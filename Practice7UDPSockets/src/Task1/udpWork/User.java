package Task1.udpWork;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {
    private InetAddress address;
    private int port = -1;

    public User(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "User: Address --> " + this.getAddress()
                + " // Port --> " + this.getPort() + ".";
    }
}
