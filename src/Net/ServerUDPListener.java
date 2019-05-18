package Net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/*
 * A listener for UDP datagramms with information about model state, which can 
 * be missed without any problems
 */

public class ServerUDPListener implements Runnable {
   
    private DatagramSocket socket;
    private final int port;
    private final InetAddress address;
        
    
    protected ServerUDPListener(InetAddress addr, int p) {
        address = addr;
        port = p;
    }
    
    
    private void create_socket() {
         try {
            socket = new DatagramSocket(port, address);
        } catch (IOException ex) { ex.printStackTrace();}
    }
    
    
    @Override
    public void run() {
        
        create_socket();
        
        synchronized(Client.window) {
            try {
                Client.window.wait();
            } catch (InterruptedException ex) {ex.printStackTrace();}
        }
        
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
