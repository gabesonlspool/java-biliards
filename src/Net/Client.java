/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
/**
 *
 * @author andrey
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
    private static int PREFERRED_PORT;
    
    public static void generate_preferred_port() {
        try {
            ServerSocket s = new ServerSocket(0);
            PREFERRED_PORT = s.getLocalPort();
            s.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void update() {
        window.screng.update();
        if (state_manager.state != window.screng.dataframe.state) {
            if (window.screng.dataframe.state == StateManager.MOVEMENT) {
                turn = !turn;
            }
            state_manager.state = window.screng.dataframe.state;   
        };
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
                System.out.println("Server: " + response);
                           
                addr = (InetAddress) in_frame.readObject();
                boolean turn = in_frame.readBoolean();
                        
                out.writeInt(PREFERRED_PORT);
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
        
        generate_preferred_port();
        try {
            InetAddress addr = establishConnection();
            if (addr != null)
                new Thread(new ServerListener(
                    addr, PREFERRED_PORT, window
                )).start();
            else throw new IOException("Couldn't resolve address");
        } catch (IOException ex) {
            window.showConnectionWarning();
        }                                    
    }
    
}
