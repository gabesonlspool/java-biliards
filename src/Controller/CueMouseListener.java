/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Net.StateManager;
import Net.Client;
import Net.ClientCommandProcessor;
import View.MainWindow;
import View.ScreenEngine;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author andrey
 */
public class CueMouseListener extends MouseAdapter {
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
        int st = Client.state_manager.state;
        if (st == StateManager.AIMING) {
            if (Client.turn) {
                Client.state_manager.switchState(StateManager.MOVEMENT);
                double [] v = 
                    ((ScreenEngine) me.getSource()).getCueDrawer().getStrikeVelocity();
               
                Client.window.ccp.sendCommand(v[0], v[1]);
            } else {
                
            }
        }
    }
    
}
