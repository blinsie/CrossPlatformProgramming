package ControlTest.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        System.setProperty("java.security.policy", "program.policy");
        Socket s = new Socket("localhost", 2019);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("hello");
        pr.flush();
    }
}
