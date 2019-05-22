package Model.Server;

import Model.GameEngine.GameEngine;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class for acquiring events from client on the server side
 */
class ClientListener implements Runnable {
    
    private GameEngine engine;
    private StateManager state_manager;
    
    private ServerSocket socket;
    private DataInputStream in;   
    
    
    protected ClientListener(
            GameEngine e, StateManager mng, int port
    ) throws IOException {
        
        engine = e;
        state_manager = mng;
        try {
            socket = new ServerSocket(port);
        } catch (IOException ex) {
            throw new IOException(ex);
        }
        
    }
    
    @Override
    public void run() {
        
        while (true) {
            
            try {
                
                Socket clientSocket = socket.accept();  
                in = new DataInputStream(clientSocket.getInputStream());
                                
                try {
                                                         
                    double v_x = in.readDouble();
                    double v_y = in.readDouble();
                    engine.getMasterBall().setVelocity(v_x, v_y);
                    state_manager.switchState(StateManager.MOVEMENT);
                                 
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
