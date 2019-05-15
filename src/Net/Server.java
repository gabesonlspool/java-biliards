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

/**
 *
 * @author andrey
 */
public class Server implements Runnable {
    
    protected final static int PREFERRED_PORT = 20000;
    
    private ArrayList<InetAddress> client_addresses;
    private ArrayList<Integer> client_ports;
    private ServerSocket serverSocket;
        
    private static ObjectOutputStream out_frame;
    private static PrintWriter out;
    private static DataInputStream in;
       
    public Server() {
        
        client_addresses = new ArrayList<>();
        client_ports = new ArrayList<>();
        
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
                in = new DataInputStream(clientSocket.getInputStream());
                                 
                out.write("Connection accepted");
                out.flush();
                out_frame.writeObject(clientSocket.getInetAddress());
                out.flush();
                if (client_addresses.size() % 2 == 0) {
                    out_frame.writeBoolean(false);
                } else {
                    out_frame.writeBoolean(true);
                }
                out.flush();
                                              
                int client_port = in.readInt();                
                client_addresses.add(clientSocket.getInetAddress());
                client_ports.add((Integer) client_port);
                
                if (client_addresses.size() == 2) {
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

                
                if (client_addresses.size() == 2) {
                    new Thread(new GameServiceTask(
                        client_addresses.get(0), client_ports.get(0),
                        client_addresses.get(1), client_ports.get(1)
                    )).start();
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();   
            } 
        }
    }
    
}
