package Net;

import View.MainWindow;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Base net client to work with server
 */

public class Client {
    
    public static MainWindow window;
    public static boolean turn;
    public static String game_type;
    public static StateManager state_manager;
    public static String server_comm_IP = null;
    
    private static Socket client_socket = null;
    private static ObjectInputStream in_frame;
    private static BufferedReader in;
    private static DataOutputStream out;
    private static int UDPPort;
    private static int TCPPort;
    
    
    public static void generateTCPPort() {
        try {
            ServerSocket s = new ServerSocket(0);
            TCPPort = s.getLocalPort();
            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void generateUDPPort() {
        try {
            ServerSocket s = new ServerSocket(0);
            UDPPort = s.getLocalPort();
            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    public static void update() {
        window.screng.update(state_manager.state, turn);
    }
    
    
    public Client() {
        
        game_type = "Classic";
        window = new MainWindow();
        state_manager = new StateManager();
        
    }
    
    
    public static void setTurn(boolean t) {
        turn = t;
    }
    
    
    public static InetAddress establishConnection() throws IOException {
        
        InetAddress addr = null;
        
        try {
            
            client_socket = new Socket(server_comm_IP, 20000);
            client_socket.setSoTimeout(10000);
            
            in_frame = new ObjectInputStream(
                    client_socket.getInputStream());
            in = new BufferedReader(
                    new InputStreamReader(in_frame));
            
            out = new DataOutputStream(client_socket.getOutputStream());
            
            try {
                
                String response = in.readLine();                           
                addr = (InetAddress) in_frame.readObject();
                turn = in_frame.readBoolean();
                        
                out.writeInt(UDPPort);
                out.flush();
                out.writeInt(TCPPort);
                out.flush();
            
                response = in.readLine();
                System.out.println("Server: " + response);
                setTurn(turn);
                
            } finally {
                
                client_socket.close();
                in.close();
                in_frame.close();
                out.close();
            }    
            
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        }
            
        return addr;
    
    }
    
    public static void run() {
        
        generateTCPPort();
        generateUDPPort();
        
        try {
            InetAddress addr = establishConnection();
            if (addr != null) {
                new Thread(new ServerUDPListener(addr, UDPPort)).start();
                new Thread(new ServerTCPListener(addr, TCPPort)).start();
            }
            else throw new IOException("Couldn't resolve address");
        } catch (IOException ex) {
            window.showConnectionWarning();
        }                                    
    }
    
}
