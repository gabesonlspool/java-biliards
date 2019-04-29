/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GameEngine;
import Model.StateManager;
import View.ScreenEngine;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
            GameEngine.getMasterBall().setVelocity(v[0], v[1]);
            Thread working_thread = new Thread(GameEngine.getInstance());
            working_thread.run();
            StateManager.switchState(StateManager.AIMING);
        }
    }
    
}
