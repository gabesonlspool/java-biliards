package Net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author andrey
 */
public class ClientCommandProcessor {
    
    private static InetAddress comm_address = null;
    private static int comm_port = -1;
    private static Socket client_socket = null;
    private static BufferedReader in;
    private static DataOutputStream out; 
    
    public static final int SWITCH_STATE = 0;
    public static final int STRIKE = 1;
        
    public ClientCommandProcessor() {}
    
    protected void setServerParams(InetAddress addr, int p) {
        comm_address = addr;
        comm_port = p;    
    }
    
    public void sendCommand(int command) {
        
        try {
            
            client_socket = new Socket(comm_address, comm_port);
            //client_socket.setSoTimeout(10000);
            
            try {
                                
                in = new BufferedReader(
                        new InputStreamReader(client_socket.getInputStream()));
                out = new DataOutputStream(
                        client_socket.getOutputStream());
                PrintWriter out_str = new PrintWriter(
                        new OutputStreamWriter(out));
                
                             
                switch (command) {
                    case SWITCH_STATE:
                        out_str.println("SWITCH STATE");
                        out_str.flush();
                    case STRIKE:
                        out_str.println("STRIKE");
                        out_str.flush();
                        out.writeDouble(1.6);
                        out.flush();
                        out.writeDouble(1.6);
                        out.flush();
                }
                
                String response = in.readLine();
                System.out.println("Server:" + response);
                
                
            } finally {
                client_socket.close();
                in.close();
                out.close();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
