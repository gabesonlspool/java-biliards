package Model.Server;

import Model.GameEngine.GameEngine;
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
    
    /* After the connection server has info about two client-side sockets
     * TCP-socket is designed to pass the model state or failure status
     * to the client. Information about the model is sent via UDP in
     * 'dataframes'. When engine is not working, information is not sent.
    */
    
    public EngineOutputDataFrame dataframe;
    
    private GameEngine engine;
    private ClientListener clientlistener;
    private ModelStateObserver obs;
    private int comm_port;
    private StateManager state_manager;
    
    private DatagramSocket player1;
    private InetAddress player1address;
    private int player1UDPport;
    private int player1TCPport;

    private DatagramSocket player2;
    private InetAddress player2address;
    private int player2UDPport;
    private int player2TCPport;
    
    private ByteArrayOutputStream byte_str;
    private ObjectOutputStream frame_str;
    
    
    protected GameServiceTask(
            InetAddress p1a,  
            int p1udp,
            int p1tcp,
            InetAddress p2a, 
            int p2udp,
            int p2tcp
    ) {
                
        state_manager = new StateManager();
        engine = new GameEngine(this, state_manager, "DiamondPool");
                     
        player1UDPport = p1udp;
        player1TCPport = p1tcp;
        player1address = p1a;
        
        player2UDPport = p2udp;
        player2TCPport = p2tcp;
        player2address = p2a;
        
        try {
            ServerSocket s = new ServerSocket(0);
            comm_port = s.getLocalPort();
            s.close();
            clientlistener = new ClientListener(
                engine, state_manager, comm_port
            );
            obs = new ModelStateObserver(
                    state_manager, p1a, p1tcp, p2a, p2tcp
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
                   
    }
    
    private void syncDataframeAndPort(int port, InetAddress addr) {
        
        /* Synchronization is requested for client to get information about 
         * where to send it's commands, and what is the ball configuration,
         * in order to initialize it's ball drawers.
        */
                    
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
    
    private void sendModelData() {
        
        try {
            
            byte_str = new ByteArrayOutputStream();
            frame_str = new ObjectOutputStream(byte_str);
            frame_str.writeObject(dataframe);
            byte[] data = byte_str.toByteArray();
            frame_str.close();
            byte_str.close();
                
            DatagramSocket player1 = new DatagramSocket();
            DatagramPacket packet1 = new DatagramPacket(
                data, data.length,
                player1address, player1UDPport
            );
            
            player1.send(packet1);
            player1.close();
                    
            DatagramSocket player2 = new DatagramSocket();
            DatagramPacket packet2 = new DatagramPacket(
                data, data.length,
                player2address, player2UDPport
            );
                    
            player2.send(packet2);
            player2.close();
                  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        
        syncDataframeAndPort(player1TCPport, player1address);
        syncDataframeAndPort(player2TCPport, player2address);
        
        new Thread(clientlistener).start();
        new Thread(engine).start();
        new Thread(obs).start();
        
        while (true) {
            
            long startTime = System.currentTimeMillis();
            
            if (state_manager.state == StateManager.MOVEMENT) {
                sendModelData();
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
