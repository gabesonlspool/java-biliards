package Model.Server;

import Model.GameEngine.GameEngine;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/* Class designed to notify client about the changes in model that must be
 * reacted, such as exceptions or state switches
*/

class ModelStateObserver implements Runnable {
    
    private int state = -1;
    
    private StateManager manager;
    private final int player1TCPPort;
    private final InetAddress player1address;
    
    private final int player2TCPPort;
    private final InetAddress player2address;

    public ModelStateObserver(
            StateManager mng,
            InetAddress p1a,
            int p1tcp,
            InetAddress p2a,
            int p2tcp
    ) {
       
        manager = mng;
        player1TCPPort = p1tcp;
        player1address = p1a;
        player2TCPPort = p2tcp;
        player2address = p2a;
       
    }
    
    private void sendModelStateSwitch (
            int state,
            InetAddress address, 
            int port
    ) {
        
        try {
            
            Socket s = new Socket(address, port);
            s.setSoTimeout(10000);   
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            
            try {  
                out.writeInt(state);
                out.flush();          
            } finally {
                s.close();
                out.close();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    } 
    
    
    @Override
    public void run() {
        
        while (true) {
            
            long startTime = System.currentTimeMillis();
            
            if (state != manager.state) {
                sendModelStateSwitch(
                        manager.state, player1address, player1TCPPort
                );
                sendModelStateSwitch(
                        manager.state, player2address, player2TCPPort
                );
            } 
            
            state = manager.state;
            long estimatedTime = System.currentTimeMillis() - startTime;            
            if (estimatedTime < 100) {
                try {
                    Thread.sleep((100 - estimatedTime));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
            }
        }
    }
    
}
