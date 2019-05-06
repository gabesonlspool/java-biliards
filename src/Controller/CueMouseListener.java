/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.StateManager;
import Net.Client;
import Net.ClientCommandProcessor;
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
        int st = StateManager.state;
        if (st == StateManager.AIMING) {
            StateManager.switchState(StateManager.MOVEMENT);
            double [] v = 
                ((ScreenEngine) me.getSource()).getCueDrawer().getStrikeVelocity();
               
            Client.window.ccp.sendCommand(ClientCommandProcessor.STRIKE);
        }
    }
    
}
