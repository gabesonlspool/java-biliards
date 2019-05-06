/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import Model.GameEngine;
import Model.StateManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andrey
 */
class ClientListener implements Runnable {
    
    private ServerSocket socket;
    private DataInputStream in;
    private PrintWriter out;
    private GameEngine engine;
    
    
    protected ClientListener(GameEngine e, int port) {
        
        engine = e;
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
                
                BufferedReader in_string = 
                            new BufferedReader(new InputStreamReader(in));
                out = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()));
                
                try {
                    
                    String command = in_string.readLine();
                    System.out.println("Client: " + command);
                
                    switch (command) {
                        case "SWITCH STATE": 
                            StateManager.switchState(in.readInt());
                        
                        case "STRIKE":
                            engine.getMasterBall().setVelocity(
                                    Math.random() * 2, Math.random() * 2);
                            StateManager.switchState(StateManager.MOVEMENT);
                    }
                    
                    out.println("Successfull");
                    out.flush();
                
                } finally {
                    clientSocket.close();
                    in_string.close();
                    in.close();
                    out.close();
                }
               
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }      
    }
    
}
