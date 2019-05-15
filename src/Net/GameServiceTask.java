/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import Model.GameEngine;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andrey
 */
public class GameServiceTask implements Runnable {
    
    public EngineOutputDataFrame dataframe;
    
    private GameEngine engine;
    private ClientListener clientlistener;
    private int comm_port;
    private StateManager state_manager;
    
    private DatagramSocket player1;
    private InetAddress player1address;
    private int player1port;

    private DatagramSocket player2;
    private InetAddress player2address;
    private int player2port;
    
    private ByteArrayOutputStream byte_str;
    private ObjectOutputStream frame_str;
    
    
    protected GameServiceTask(
            InetAddress pa1, 
            int pp1,
            InetAddress pa2, 
            int pp2
    ) {
        
        state_manager = new StateManager();
        engine = new GameEngine(this, state_manager, "DiamondPool");
                     
        player1port = pp1;
        player1address = pa1;
        player2port = pp2;
        player2address = pa2;
        
        try {
            ServerSocket s = new ServerSocket(0);
            comm_port = s.getLocalPort();
            
            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        clientlistener = new ClientListener(
                engine, state_manager, comm_port
        );
                   
    }
    
    private void syncDataframeAndPort(int port, InetAddress addr) {
                    
        try {
            
            Socket socket = new Socket(addr, port);
            socket.setSoTimeout(10000);
            ObjectOutputStream out = new ObjectOutputStream(
                socket.getOutputStream()
            );
            
            try {
                dataframe = engine.getDataFrame();
                out.writeObject(dataframe);
                out.flush();
                out.writeInt(comm_port);
                out.flush();
                
            } finally {
                socket.close();
                out.close();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    @Override
    public void run() {
        
        syncDataframeAndPort(player1port, player1address);
        syncDataframeAndPort(player2port, player2address);
        
        new Thread(clientlistener).start();
        new Thread(engine).start();
        
        while (true) {
            
            long startTime = System.currentTimeMillis();
                                     
            try {
                
                byte_str = new ByteArrayOutputStream();
                frame_str = new ObjectOutputStream(byte_str);   
                frame_str.writeObject(dataframe);
                byte[] data = byte_str.toByteArray();
                frame_str.close();
                byte_str.close();
                
                DatagramSocket player1 = new DatagramSocket();
                player1.connect(player1address, player1port);
                DatagramPacket packet1 = new DatagramPacket(
                    data, data.length,
                    player1address, player1port
                );
                player1.send(packet1);
                player1.close();
                    
                DatagramSocket player2 = new DatagramSocket();
                player2.connect(player2address, player2port);
                DatagramPacket packet2 = new DatagramPacket(
                    data, data.length,
                    player2address, player2port
                );
                player2.send(packet2);
                player2.close();
                                    
            } catch (IOException e) {
                e.printStackTrace();
            }
        
            long estimatedTime = System.currentTimeMillis() - startTime;            
            if (estimatedTime < 20) {
                try {
                    Thread.sleep((20 - estimatedTime));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
            }
        }
    }
    
}
