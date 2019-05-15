/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.EnterKeyPressHandler;
import Controller.MouseButtonClickHandler;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseListener;

/**
 *
 * @author andrey
 */
public class StartGameButton extends GameButton {
    
    public StartGameButton(MouseListener l) {
        super(l, "Start Game");
    }
   
    public void update() {
                  
        Frame parent = (Frame) this.getParent().getParent();
        Dialog d = new Dialog(parent, "Start Game");
        String msg = "Enter server IP address";
        int x = parent.getWidth()/2  - 250;
        int y = parent.getHeight()/2 - 120;
        d.setBounds(x, y, 500, 240);
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
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        TextField f = new TextField(30);
        f.setFont(new Font("Ubuntu",Font.PLAIN, 24));
        f.setText("127.0.0.1");
        f.addKeyListener(new EnterKeyPressHandler());
        grid.setConstraints(f, gbc);
        d.add(f);

        gbc.fill = gbc.HORIZONTAL;
        gbc.anchor = gbc.PAGE_END;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        d.add(new ConfirmInputButton(new MouseButtonClickHandler()), gbc); 

        gbc.fill = gbc.HORIZONTAL;
        gbc.anchor = gbc.PAGE_END;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        d.add(new BackButton(new MouseButtonClickHandler()), gbc);
        d.setModal(true);
        d.setVisible(true);
        
    }
    
}
