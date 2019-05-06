/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Model.StateManager;
import View.GameMenu;
import View.ScreenEngine;
import java.awt.event.MouseListener;

/**
 *
 * @author andrey
 */
public class StartGameButton extends GameButton {

    public StartGameButton(MouseListener l) {
        super(l, "Start Game");
    }
   
    public void update() {
        
        GameMenu menu = (GameMenu) this.getParent();
        menu.setVisible(false);
        ScreenEngine screng  =
                (ScreenEngine) this.getParent().getParent().getComponent(1);
        
        screng.setVisible(true); 
                
        StateManager.switchState(StateManager.AIMING);
    }
    
}
