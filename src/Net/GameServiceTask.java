/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import Model.GameEngine;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author andrey
 */
public class GameServiceTask implements Runnable {
    
    private GameEngine engine;
    private ClientListener clientlistener;
    
    private DatagramSocket player1;
    private InetAddress player1address;
    private int player1port;

    private DatagramSocket player2;
    private InetAddress player2address;
    private int player2port;
    
    private ByteArrayOutputStream byte_str;
    private ObjectOutputStream frame_str;
    
    
    protected GameServiceTask(
            GameEngine e,
            int comm_port, 
            Socket client1,
            Socket client2) {
        
        engine = e;
        
        try {
            player1 = new DatagramSocket();
            player1port = client1.getPort();
            player1address = client1.getInetAddress();
            player2 = new DatagramSocket();
            player2port = client2.getPort();
            player2address = client2.getInetAddress();
            
            byte_str = new ByteArrayOutputStream();
            frame_str = new ObjectOutputStream(byte_str);
            
            clientlistener = new ClientListener(
                    e, comm_port
            );
        } catch (IOException ex){ ex.printStackTrace();}
    }
    
    
    @Override
    public void run() {
                
        while (true) {
            try {
                new Thread(clientlistener).start();
                new Thread(engine).start();
                EngineOutputDataFrame df = engine.getDataFrame();
                frame_str.writeObject(df);
                byte[] data = byte_str.toByteArray();
                System.out.println(df.masterballcoords[0]);
                
                DatagramPacket packet1 = new DatagramPacket(
                    data, 
                    data.length,
                    player1address,
                    player1port
                );
                
                player1.send(packet1);
                DatagramPacket packet2 = new DatagramPacket(
                    data, 
                    data.length,
                    player2address,
                    player2port
                );
                player2.send(packet2);
                Thread.sleep(20);
                
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
}
