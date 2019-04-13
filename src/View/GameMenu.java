/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.Buttons.GameButton;
import View.Buttons.HelpButton;
import View.Buttons.StartGameButton;
import View.Buttons.OptionsButton;
import View.Buttons.QuitButton;
import Controller.MouseButtonClickHandler;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

        

public class GameMenu extends Container {

    protected GameMenu(int x, int y, int w, int h) {
        super();
        setBounds(x, y, w, h/2);
        
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(grid);
        
        addButton(
                new StartGameButton(new MouseButtonClickHandler()),
                0, grid
        );
        addButton(
                new OptionsButton(new MouseButtonClickHandler()),
                1, grid
        );
        addButton(
                new HelpButton(new MouseButtonClickHandler()),
                2, grid
        );
        addButton(
                new QuitButton(new MouseButtonClickHandler()),
                3, grid
        );
        
        
        setVisible(true);
    }
    
    void addButton(GameButton b, int i, GridBagLayout gbl) {
        GridBagConstraints gbc = gbl.getConstraints(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        gbc.gridx = 0;
        gbc.gridy = i;
        gbc.ipadx = this.getWidth() * 3 / 5;
        gbc.ipady = this.getHeight() / 8;  
        add(b, gbc);        
    }
    
}