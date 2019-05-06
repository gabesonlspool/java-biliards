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
import Net.ClientCommandProcessor;
import Net.EngineOutputDataFrame;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;


public class MainWindow {
    
    private Frame window;
    public ScreenEngine screng;
    
    public ClientCommandProcessor ccp;
    
    public MainWindow() {
        
        ccp = new ClientCommandProcessor();
              
        window = new Frame("Billiard");
        window.setLayout(null);
        
        Dimension scrsz = Toolkit.getDefaultToolkit().getScreenSize();
        window.setSize(scrsz);
            
        WindowCloseHandler w = new WindowCloseHandler();
        window.addWindowListener(w);
              
        GameMenu m = new GameMenu(scrsz);
        window.add(m);
                
        screng = new ScreenEngine(scrsz.width, scrsz.height);
        screng.setVisible(false);
        window.add(screng);
        
        window.setFocusable(true);
        window.setVisible(true);       
    }
    
    public void setDataFrame(EngineOutputDataFrame df) {
        screng.dataframe = df;
    }
}

