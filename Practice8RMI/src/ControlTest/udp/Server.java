package ControlTest.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        // TODO code application logic
        //connection
        DatagramSocket socket = new DatagramSocket(1001);

        //pkt buffers
        byte[] inServer = new byte[1024];
        byte[] outServer = new byte[1024];

        //receive pkt
        DatagramPacket rcvPkt = new DatagramPacket(inServer,inServer.length);
        socket.receive(rcvPkt);
        //display receive
        System.out.println("Packet Received!");


        //retrive pkt info to send response to same sender
        InetAddress IP = rcvPkt.getAddress();
        int port = rcvPkt.getPort();

        //process data
        String temp = new String(rcvPkt.getData());
        temp = temp.toUpperCase();
        outServer = temp.getBytes();

        //send response packet to sender
        DatagramPacket sndPkt = new DatagramPacket(outServer, outServer.length, IP, port);
        socket.send(sndPkt);

    }
}
