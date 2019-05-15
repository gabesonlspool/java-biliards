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
public class OKButton extends GameButton {

    public OKButton(MouseButtonClickHandler l) {
        super(l, "OK");
    }
    
    public void update() {
        this.getParent().setVisible(false);
    }
    
}