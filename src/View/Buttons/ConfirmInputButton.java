/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;
import Net.StateManager;
import Net.Client;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author andrey
 */
public class ConfirmInputButton extends GameButton {

    public ConfirmInputButton(MouseButtonClickHandler l) {
        super(l, "OK");
    }
    
    @Override
    public void update() {
        
        String text = ((TextField) this.getParent().getComponent(1)).getText();
                     
        try {
            InetAddress addr = InetAddress.getByName(text);
        } catch (IOException ex) {
                        
            Frame parent = (Frame) this.getParent().getParent();
            Dialog d = new Dialog(parent, "Erroneous IP");
            String msg = "Erroneous IP";
            int x = parent.getWidth()/2  - 120;
            int y = parent.getHeight()/2 - 70;
            d.setBounds(x, y, 240, 140);
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
            d.add(new OKButton(new MouseButtonClickHandler()), gbc); 
            d.setModal(true);
            d.setVisible(true);
            return;
        }
            
        Client.server_comm_IP = text;    
        this.getParent().setVisible(false);
                
        Client.state_manager.switchState(StateManager.AIMING);
        Client.run();
        
    }
}
