/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MouseButtonClickHandler;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

        

public class GameMenu extends Container {

    protected GameMenu(int x, int y, int w, int h) {
        super();
        setBounds(x, y, w, h/2);
        
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(grid);
        
        ArrayList<String> button_names = new ArrayList<String>();
        button_names.add("Start Game");
        button_names.add("Options");
        button_names.add("Help");
        button_names.add("Quit");
        
        int i = 0;
        for (String button: button_names) {
        //add buttons to menu
            gbc.fill = GridBagConstraints.HORIZONTAL;        
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.ipadx = this.getWidth() * 3 / 5;
            gbc.ipady = this.getHeight() / 8;  
        
            add(new GameButton(
                    new MouseButtonClickHandler(),
                    button),
                gbc);
            i++;
        }
        
        setVisible(true);
    }
    
}