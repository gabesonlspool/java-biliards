package Net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
          
    public ClientCommandProcessor() {}
    
    protected void setServerParams(InetAddress addr, int p) {
        comm_address = addr;
        comm_port = p;    
    }
    
    public void sendCommand(double v_x, double v_y) {
        
        try {
            
            client_socket = new Socket(comm_address, comm_port);
            client_socket.setSoTimeout(10000);   
            try {
                                
                in = new BufferedReader(
                        new InputStreamReader(client_socket.getInputStream()));
                out = new DataOutputStream(
                        client_socket.getOutputStream());
         
                out.writeDouble(v_x);
                out.flush();
                out.writeDouble(v_y);
                out.flush();               
                
            } finally {
                client_socket.close();
                in.close();
                out.close();
            }
            
        } catch (IOException ex) {
            Client.window.showConnectionWarning();
        }
        
    }
}
