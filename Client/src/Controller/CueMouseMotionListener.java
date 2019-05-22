/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.StateManager;
import Model.Client;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author andrey
 */
public class CueMouseMotionListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent me) {};

    @Override
    public void mouseMoved(MouseEvent me) {
        if (Client.state_manager.state == StateManager.AIMING && Client.turn) {
            Client.update();
        }
    }
    
}
