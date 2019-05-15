/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import View.MainWindow;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andrey
 */
public class ServerListener implements Runnable {
   
    private DatagramSocket socket;
    private final int port;
    private final InetAddress address;
        
    
    protected ServerListener(InetAddress addr, int p, MainWindow w) {
        address = addr;
        port = p;
    }
    
    private void sync_data_frame() {
        
        try {
            
            ServerSocket server_socket = new ServerSocket(port);
            Socket s = server_socket.accept();
            
            ObjectInputStream in_frame = new ObjectInputStream(
                s.getInputStream()
            );
            
            try {
                Client.window.screng.setDataFrame(
                    (EngineOutputDataFrame) in_frame.readObject()
                );
                Client.window.ccp.setServerParams(
                       s.getInetAddress(), in_frame.readInt());
                Client.window.screng.initBallDrawers();
                Client.window.window.getComponent(0).setVisible(false);
                Client.window.screng.setVisible(true);
                
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                s.close();
                in_frame.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void create_socket() {
         try {
            socket = new DatagramSocket(port, address);
        } catch (IOException ex) { ex.printStackTrace();}
    }
    
    @Override
    public void run() {
        
        sync_data_frame();
        create_socket();
        
        while (true) {
            
            long startTime = System.currentTimeMillis();
            
            try {
                
                byte[] data = new byte[1000];
                DatagramPacket packet = new DatagramPacket(
                        data, data.length, address, port
                );
                
                socket.receive(packet);
                ByteArrayInputStream byte_str = new ByteArrayInputStream(data);
                ObjectInputStream frame_str = new ObjectInputStream(byte_str);
                EngineOutputDataFrame df = 
                        (EngineOutputDataFrame) frame_str.readObject();
                
                Client.window.setDataFrame(df);
                Client.update();
                
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
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
