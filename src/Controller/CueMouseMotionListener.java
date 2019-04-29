/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GameEngine;
import Model.StateManager;
import View.ScreenEngine;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author andrey
 */
public class CueMouseMotionListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent me) {
        if (StateManager.state == StateManager.AIMING) {
            ((ScreenEngine) me.getSource()).getCueDrawer().update();
            ((ScreenEngine) me.getSource()).update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
    };    
}
