/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;
import java.awt.Component;
import java.awt.Dialog;

/**
 *
 * @author andrey
 */
public class OKButton extends GameButton {

    public OKButton(MouseButtonClickHandler l) {
        super(l, "OK");
    }
    
    public void update() {
        Component tmp = this.getParent();
        while (!(tmp instanceof Dialog)) {
            tmp = tmp.getParent();
        }
        tmp.setVisible(false);
    }
    
}