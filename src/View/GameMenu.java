/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import View.Buttons.StartGameButton;
import View.Buttons.OptionsButton;
import View.Buttons.QuitButton;
import Controller.MouseButtonClickHandler;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

        

public final class GameMenu extends Container {

    protected GameMenu(Dimension screensize) {
        super();
        setBounds(
                screensize.width/4, screensize.height/4,
                screensize.width/2, screensize.height/2);
        
        GridLayout grid = new GridLayout(3, 1);
        setLayout(grid);
        
        add(new StartGameButton(new MouseButtonClickHandler()));
        add(new OptionsButton(new MouseButtonClickHandler()));
        add(new QuitButton(new MouseButtonClickHandler()));
        
        setVisible(true);
    }
       
}