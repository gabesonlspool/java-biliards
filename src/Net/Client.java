/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import View.MainWindow;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
/**
 *
 * @author andrey
 */

public class Client {
    
    public static final MainWindow window = new MainWindow();
    private static Socket client_socket = null;
    private static ObjectInputStream in_frame;
    private static BufferedReader in;
    private static PrintWriter out;
    
    private static final String server_comm_IP = "127.0.0.1";
    private static int server_comm_port = -1;
    
    
    public void run() {
        try {
            client_socket = new Socket(server_comm_IP, 20000);
            client_socket.setSoTimeout(10000);
            
            try {
                
                in_frame = new ObjectInputStream(
                        client_socket.getInputStream());
                in = new BufferedReader(
                        new InputStreamReader(in_frame));
                out = new PrintWriter(
                    new OutputStreamWriter(client_socket.getOutputStream()));
            
                String response = in.readLine();
                System.out.println("Server: " + response);
            
                window.setDataFrame(
                        (EngineOutputDataFrame) in_frame.readObject());
                
                InetAddress addr = (InetAddress) in_frame.readObject();
                int port = in_frame.readInt();
                server_comm_port = in_frame.readInt();
                window.ccp.setServerParams(
                        client_socket.getInetAddress(), server_comm_port);
                     
                response = in.readLine();
                System.out.println("Server: " + response);
                out.println("OK");
                out.flush();
                new Thread(new ServerListener(addr, port, window)).start();            
                
            } finally {
                client_socket.close();
                in.close();
                in_frame.close();
                out.close();
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
          
    }
    //    new Thread(new ServerListener()).start();      
    
    
}
