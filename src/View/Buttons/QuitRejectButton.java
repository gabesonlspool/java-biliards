/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;
import java.awt.Component;

/**
 *
 * @author andrey
 */
public class QuitRejectButton extends GameButton {

    public QuitRejectButton(MouseButtonClickHandler l) {
        super(l, "No");
    }
    
    public void update() {
        this.getParent().setVisible(false);        
    }
    
}
