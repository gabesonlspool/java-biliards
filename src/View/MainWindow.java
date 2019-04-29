/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author andrey
 */

import Controller.WindowCloseHandler;
import Model.CueBall;
import Model.GameEngine;
import Model.StateManager;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;


public class MainWindow {
    public static void main(String[] args){
        
        Frame window = new Frame("Billiard");
        window.setLayout(null);
        
        Dimension scrsz = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(scrsz);
            
        WindowCloseHandler w = new WindowCloseHandler();
        window.addWindowListener(w);
              
        GameMenu m = new GameMenu(scrsz);
        window.add(m);
                
        StateManager mng = StateManager.getInstance();       
        GameEngine e = new GameEngine();
        CueBall b = new CueBall(2, 1);
        e.AddBall(b);
        e.AddBall(new CueBall(1.5, 0.5));
        ScreenEngine s = new ScreenEngine(e, scrsz.width, scrsz.height);
        e.AddScreenEngine(s);
        s.setVisible(false);
        window.add(s);
        
        window.setFocusable(true);
        window.setVisible(true);       

    }
}

