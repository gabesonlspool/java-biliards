package Model;

import Content.EngineOutputDataFrame;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listener for events that must be processed on the client side
 */
public class ServerTCPListener implements Runnable {
    
    private final int port;
    private final InetAddress address;
    private DataInputStream in;
    private ServerSocket serverSocket;
    
    
    protected ServerTCPListener(InetAddress addr, int p) {
        address = addr;
        port = p;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {ex.printStackTrace();}
    }
    
    
    private void sync_data_frame() {
        
        try {
            
            
            Socket s = serverSocket.accept();
            
            ObjectInputStream in_frame = new ObjectInputStream(
                s.getInputStream()
            );
            
            try {
                
                synchronized(Client.window) {
                
                    Client.window.screng.setDataFrame(
                        (EngineOutputDataFrame) in_frame.readObject()
                    );
                    Client.commandProcessor.setServerParams(
                           s.getInetAddress(), in_frame.readInt());
                    Client.window.screng.initBallDrawers();
                    Client.window.window.getComponent(0).setVisible(false);
                    Client.window.screng.setVisible(true);
                    Client.update();
                    Client.window.notify();
                    
                }
                
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

    
    @Override
    public void run() {
        
        sync_data_frame();
        while (true) {
            
            try {
                
                Socket clientSocket = serverSocket.accept();  
                in = new DataInputStream(clientSocket.getInputStream());              
                try {
                    
                    int tmp = in.readInt();
                    Client.state_manager.switchState(tmp);
                    if (tmp == StateManager.AIMING)  {
                        Client.turn = !Client.turn;
                    }
                } finally {
                    clientSocket.close();
                    in.close();
                }
               
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }      
    }
}
