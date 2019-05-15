/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import Model.GameEngine;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andrey
 */
class ClientListener implements Runnable {
    
    private GameEngine engine;
    private StateManager state_manager;
    
    private ServerSocket socket;
    private DataInputStream in;   
    
    
    protected ClientListener(GameEngine e, StateManager mng, int port) {
        
        engine = e;
        state_manager = mng;
        try {
            socket = new ServerSocket(port);
        } catch (IOException ex) {ex.printStackTrace();}
        
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
