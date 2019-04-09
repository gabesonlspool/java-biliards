/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import View.GameButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Model.GameEngine;

public class MouseButtonClickHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent me) {
        GameButton btn = (GameButton) me.getSource();
        if (GameEngine.is_working) {
            GameEngine.pause();
        } else {
            GameEngine.run();
        }
        
        btn.repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
        if (GameEngine.is_working) {
            GameEngine.pause();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (!GameEngine.is_working) {
            GameEngine.run();
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {} 
    
}

