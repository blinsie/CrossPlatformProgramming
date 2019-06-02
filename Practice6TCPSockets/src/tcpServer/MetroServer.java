package tcpServer;

import tcpWork.MetroCardBank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
    MetroCardBank mcb;
    private ServerSocket servSock;
    private int serverPort = -1;
    private boolean work = true;

    public MetroServer(int port) {
        this.mcb = new MetroCardBank();
        this.serverPort = port;
    }

    public int getServerPort() {
        return serverPort;
    }

    public MetroCardBank getMcb() {
        return mcb;
    }

    public ServerSocket getServSock() {
        return servSock;
    }

    @Override
    public void run() {
        work = true;
        try {
            this.servSock = new ServerSocket(serverPort);
//            servSock.setSoTimeout(10000);
            System.out.println("Metro Server started: " + this.getServSock());
            while (work) {
                System.out.println("New Client Waiting...");
                Socket sock = servSock.accept();
                System.out.println("New client: " + sock);
                ClientHandler ch = new ClientHandler(this.getMcb(), sock);
                ch.start();
            }
            mcb = null;
        } catch (IOException e) {
            System.out.println("IOError: " + e);
            work = false;
        } finally {
            try {
                servSock.close();
                System.out.println("Metro Server stopped");
            } catch (IOException ex) {
                System.out.println("22Error: " + ex);
            }
        }
    }

    public static void main(String[] args) {
        MetroServer srv = new MetroServer(80);
        srv.start();
    }
}