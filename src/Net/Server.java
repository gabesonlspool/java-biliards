package Net;

import Model.GameEngine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.net.Socket;

/**
 *
 * @author andrey
 */
public class Server implements Runnable {
    
    protected final static int PREFERRED_PORT = 20000;
    
    private ArrayList<Socket> waiting_room;
    private ServerSocket serverSocket;
    private GameEngine engine;
    private static ObjectOutputStream out_frame;
    private static PrintWriter out;
    private static BufferedReader in;
       
    public Server() {
        engine = new GameEngine();
        waiting_room = new ArrayList<>();       
        try {
            serverSocket = new ServerSocket(PREFERRED_PORT);
        }
        catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void run() {
        
        while (true) {            
            try {
                Socket clientSocket = serverSocket.accept();  
                out_frame = new ObjectOutputStream(
                        clientSocket.getOutputStream()
                );
                out = new PrintWriter(
                        new OutputStreamWriter(out_frame)
                );
                in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                 
                
                ServerSocket s = new ServerSocket(0);
                int port = s.getLocalPort();
                
                out.write("Connection accepted");
                out.flush();
                out_frame.writeObject(engine.getDataFrame());
                out.flush();
                out_frame.writeObject(clientSocket.getInetAddress());
                out.flush();
                out_frame.writeInt(clientSocket.getPort());
                out.flush();
                out_frame.writeInt(port);
                out.flush();
                s.close();
                
                waiting_room.add(clientSocket);
                
                if (waiting_room.size() == 2) {
                    out.println(
                        "Your game will start in a couple of seconds"
                    );
                } else {
                    out.println("Wait for the second player");
                } 
                
                out.flush();
                
                String response = in.readLine();
                System.out.println("Client: " + response);
                
                clientSocket.close();
                out.close();
                out_frame.close();
                in.close();
                if (waiting_room.size() == 2) {
                    new Thread(new GameServiceTask(
                        engine, port,  
                        waiting_room.get(0), waiting_room.get(1)
                    )).start();
                }
            } catch (IOException ex) {
                ex.printStackTrace();   
            } 
        }
    }
    
}
