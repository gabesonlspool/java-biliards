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
public class QuitAcceptButton extends GameButton {

    public QuitAcceptButton(MouseButtonClickHandler l) {
        super(l, "OK");
    }
    
    public void update() {
        System.exit(0);
    }
    
}
