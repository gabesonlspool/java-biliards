/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;

/**
 *
 * @author andrey
 */
public class BackButton extends GameButton {
    
    public BackButton(MouseButtonClickHandler l) {
        super(l, "Back");
    }
    
    public void update() {
        this.getParent().setVisible(false);
    }
    
}
