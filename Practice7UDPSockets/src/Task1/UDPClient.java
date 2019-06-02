package Task1;

import Task1.udpWork.ActiveUsers;
import Task1.udpWork.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class UDPClient {
    private ActiveUsers userList = null;
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private InetAddress serverAddress = null;
    private int serverPort = -1;

    public UDPClient(String address, int port) {
        userList = new ActiveUsers();
        this.serverPort = port;
        try {
            serverAddress = InetAddress.getByName(address);
            socket = new DatagramSocket();
            socket.setSoTimeout(1000);
        } catch (UnknownHostException e) {
            System.out.println("Server Error1: " + e);
        } catch (SocketException e) {
            System.out.println("Server Error2: " + e);
        }
    }

    public void work(int bufferSize) throws ClassNotFoundException {
        byte[] buffer = new byte[bufferSize];
        try {
            packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
            socket.send(packet);
            System.out.println("Sending request...");
            while(true) {
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                if (packet.getLength()==0)
                    break;
                ObjectInputStream in = new ObjectInputStream(
                        new ByteArrayInputStream(
                                packet.getData(), 0, packet.getLength()));
                User usr = (User) in.readObject();
                userList.add(usr);
            }
        } catch(SocketTimeoutException e) {
            System.out.println("Server is unreachable: " + e);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        finally {
            socket.close();
        }
        System.out.println("Registered users: " + userList.size());
        System.out.println(userList);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        (new UDPClient("127.0.0.1", 1501)).work(256);
        (new UDPClient("127.0.0.1", 1501)).work(256);
        (new UDPClient("127.0.0.1", 1501)).work(256);
    }

}
