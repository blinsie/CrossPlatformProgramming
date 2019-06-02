package tcpServer;

import tcpServer.operations.*;
import tcpWork.User;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private int port = -1;
    private String server = null;
    private Socket socket = null;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public ObjectInputStream getIs() {
        return this.is;
    }

    public ObjectOutputStream getOs() {
        return this.os;
    }

    public void setIs(ObjectInputStream is) {
        this.is = is;
    }

    public void setOs(ObjectOutputStream os) {
        this.os = os;
    }

    public Client(String server, int port) {
        this.port = port;
        this.server = server;
        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 10000);
            this.setOs(new ObjectOutputStream(socket.getOutputStream()));
            this.setIs(new ObjectInputStream(socket.getInputStream()));
        } catch (InterruptedIOException e) {
            System.out.println("1Error: " + e);

        } catch (IOException e) {
            System.out.println("2Error: " + e);
        }
    }

    public void finish() {
        try{
            os.writeObject(new StopOperation());
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException e) {
            System.out.println("F1Error: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("F2Error: " + e);
        }
    }

    public void applyOperation(CardOperation op) {
        try {
            this.getOs().writeObject(op);
            this.getOs().flush();
            System.out.println(is.readObject());
        } catch (IOException e) {
            System.out.println("AError: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("BError: " + e);
        }
    }

    public static void main(String[] args) {
        Client cl = new Client("localhost", 80);
        AddMetroCardOperation op = new AddMetroCardOperation();
        op.getCrd().setUser(new User(
                "Jonh", "Jason", "M", "12.03.2000"));
        op.getCrd().setIdCard("00001");
        op.getCrd().setColledge("KhNU");
        op.getCrd().setBalance(25);
        cl.applyOperation(op);
        cl.finish();

        cl = new Client("localhost", 80);
        cl.applyOperation(new AddMoneyOperation("00001", 100));
        cl.applyOperation(new ShowBalanceOperation("00001"));
        cl.finish();
    }
}
