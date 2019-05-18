package Net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.net.Socket;

/*
 * Net server, which creates a new thread with it's own model for each two 
 * connected clients
*/
public class Server implements Runnable {
    
    protected final static int PREFERRED_PORT = 20000;
    
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<Integer> clientUDPPorts;
    private ArrayList<Integer> clientTCPPorts;
    private ServerSocket serverSocket;
    private ArrayList<Thread> gameThreads;
        
    private static ObjectOutputStream out_frame;
    private static PrintWriter out;
    private static DataInputStream in;
       
    public Server() {
        
        clientAddresses = new ArrayList<>();
        clientUDPPorts  = new ArrayList<>();
        clientTCPPorts  = new ArrayList<>();
        
        try {
            serverSocket = new ServerSocket(PREFERRED_PORT);
        }
        catch (IOException ex) { 
            System.err.println(ex.getMessage());
            System.exit(-1);
        }       
    }
    
    
    private void connectToClient() {
        
        try {
                
            Socket clientSocket = serverSocket.accept();  
            out_frame = new ObjectOutputStream(
                clientSocket.getOutputStream()
            );
            
            out = new PrintWriter(
                new OutputStreamWriter(out_frame)
            );
            
            in = new DataInputStream(clientSocket.getInputStream());
                                 
            out.write("Connection accepted");
            out.flush();
                
            out_frame.writeObject(clientSocket.getInetAddress());
            out.flush();
            if (clientAddresses.size() % 2 == 0) {
                out_frame.writeBoolean(false);
            } else {
                out_frame.writeBoolean(true);
            }
            
            out.flush();
               
                
            clientAddresses.add(clientSocket.getInetAddress());
            clientUDPPorts.add((Integer) in.readInt());
            clientTCPPorts.add((Integer) in.readInt());
                               
                
            if (clientAddresses.size()% 2 == 0) {
                out.println(
                    "Your game will start in a couple of seconds"
                );
            } else {
                out.println("Wait for the second player");
            } 
                
            out.flush();
               
            out.close();
            out_frame.close();
            in.close();
            clientSocket.close();
            
        } catch (IOException ex) {
                ex.printStackTrace();   
        } 
    }

    @Override
    public void run() {
        
        while (true) {
            
            connectToClient();
            if (clientAddresses.size() % 2 == 0) {
                new Thread(new GameServiceTask(
                    clientAddresses.get(clientAddresses.size() - 2),
                    clientUDPPorts.get(clientUDPPorts.size() - 2),
                    clientTCPPorts.get(clientTCPPorts.size() - 2),
                    clientAddresses.get(clientAddresses.size() - 1),
                    clientUDPPorts.get(clientUDPPorts.size() - 1),
                    clientTCPPorts.get(clientTCPPorts.size() - 1)
                )).start();
            }
        }
    }
    
}
