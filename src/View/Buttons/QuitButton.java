/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.MouseListener;

/**
 *
 * @author andrey
 */
public class QuitButton extends GameButton {
    
    public QuitButton(MouseListener l) {
        super(l, "Quit");
    }
    
    @Override
    public void update() {
        Frame parent = (Frame) this.getParent().getParent();
                         
        Dialog d = new Dialog(parent, "Quit");
        String msg = "Are you sure you want to quit?";
        int x = parent.getWidth()/2  - 200;
        int y = parent.getHeight()/2 - 100;
        d.setBounds(x, y, 400, 200);
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

        gbc.fill = gbc.HORIZONTAL;
        gbc.anchor = gbc.PAGE_END;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        d.add(new QuitAcceptButton(new MouseButtonClickHandler()), gbc); 

        gbc.fill = gbc.HORIZONTAL;
        gbc.anchor = gbc.PAGE_END;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        d.add(new QuitRejectButton(new MouseButtonClickHandler()), gbc);
        d.setModal(true);
        d.setVisible(true);

    }
    
}
