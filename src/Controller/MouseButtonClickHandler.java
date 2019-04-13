/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import View.Buttons.GameButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MouseButtonClickHandler extends MouseAdapter {

    public void mouseClicked(MouseEvent me) {
        GameButton btn =(GameButton) me.getSource();       
        btn.update();
    }
    
}

