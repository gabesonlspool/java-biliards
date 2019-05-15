/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author andrey
 */
public class WindowCloseHandler extends WindowAdapter {
    
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
 
}