/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Buttons.ConfirmInputButton;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author andrey
 */
public class EnterKeyPressHandler extends KeyAdapter {
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ((ConfirmInputButton) ((TextField) e.getSource()).getParent()
                    .getComponent(2)).update();
        }
    }
    
}
