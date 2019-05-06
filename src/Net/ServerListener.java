/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import View.MainWindow;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author andrey
 */
public class ServerListener implements Runnable {
   
    private DatagramSocket socket;
    private int port;
    private InetAddress address;
    private MainWindow window;
    
    
    protected ServerListener(InetAddress addr, int p, MainWindow w) {
        address = addr;
        port = p;
        try {
            socket = new DatagramSocket(port, address);
        } catch (IOException ex) { ex.printStackTrace();}
        window = w;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                byte[] data = new byte[200];
                DatagramPacket packet = new DatagramPacket(
                        data, data.length, address, port
                );
                
                socket.receive(packet);
                ByteArrayInputStream byte_str = new ByteArrayInputStream(data);
                ObjectInputStream frame_str = new ObjectInputStream(byte_str);
                EngineOutputDataFrame df = 
                        (EngineOutputDataFrame) frame_str.readObject();
                System.out.println(df.masterballcoords[0]);
                window.setDataFrame(df);
                
                window.screng.update();
                Thread.sleep(20);
            } catch (
                IOException | ClassNotFoundException |
                InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
}
