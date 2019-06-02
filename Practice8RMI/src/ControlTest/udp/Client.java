package ControlTest.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        //get input from user
        BufferedReader user_in = new BufferedReader(
                new InputStreamReader(System.in));

        //create ControlTest.udp socket connection
        DatagramSocket socket = new DatagramSocket();

        //creat buffers to process data
        byte[] inData = new byte[1024];
        byte[] outData = new byte[1024];

        //get ip destination wanted
        InetAddress IP = InetAddress.getByName("localhost");

        //read data from user
        System.out.println("Enter Data to send to server: ");
        outData = user_in.readLine().getBytes();


        /*
         * make pkts for interaction
         */
        //send pkts
        DatagramPacket sendPkt = new DatagramPacket(outData, outData.length, IP, 9876);
        socket.send(sendPkt);

        //receive pkts
        DatagramPacket recievePkt = new DatagramPacket(inData, inData.length);
        socket.receive(recievePkt);

        System.out.println("Replay from Server: "+recievePkt.getData());

    }
}
