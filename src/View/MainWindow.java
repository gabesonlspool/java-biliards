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
              
        GameMenu m = new GameMenu(
            scrsz.width * 3/4,
            0,
            scrsz.width * 1/4,
            scrsz.height
        );
        window.add(m);
                
        GameEngine e = new GameEngine();
        CueBall b = new CueBall(1, 1);
        b.setVelocity(2.5, 0.9);
        e.AddBall(b);
        e.AddBall(new CueBall(1.5, 0.5));
        ScreenEngine s = new ScreenEngine(
            e, scrsz.width * 3/4,
            scrsz.height * 3/4
        );
        e.AddScreenEngine(s);
        s.setLocation(0, 40);
        window.add(s);
        
        window.setFocusable(true);
        window.setVisible(true);       

    }
}

