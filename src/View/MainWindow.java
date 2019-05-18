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

import Controller.MouseButtonClickHandler;
import Controller.WindowCloseHandler;
import Net.ClientCommandProcessor;
import Net.EngineOutputDataFrame;
import View.Buttons.OKButton;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Toolkit;


public class MainWindow {
    
    public ScreenEngine screng;
    public final Frame window;
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

    public void showConnectionWarning() {
        
        screng.setVisible(false);
        window.getComponent(0).setVisible(true);
        Dialog d = new Dialog(window, "Connection Error");
        String msg = "Could not connect to server. Please, try again";
        int x = window.getWidth()/2  - 300;
        int y = window.getHeight()/2 - 70;
        d.setBounds(x, y, 600, 140);
        
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();  
        d.setLayout(grid);
            
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weighty = 1;
        Label l = new Label(msg, Label.CENTER);
        l.setFont(new Font("Ubuntu",Font.PLAIN, 24));
        d.add(l, gbc);
            
        gbc.fill = gbc.NONE;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.4;
        d.add(new OKButton(new MouseButtonClickHandler()), gbc); 

        d.setModal(true);
        d.setVisible(true);
    }
 }

