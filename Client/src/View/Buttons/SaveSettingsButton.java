/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;
import Model.Client;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;

/**
 *
 * @author andrey
 */
public class SaveSettingsButton extends GameButton {

    public SaveSettingsButton(MouseButtonClickHandler l) {
        super(l, "Save");
    }
    
    @Override
    public void update() {
        Dialog parent = (Dialog) this.getParent().getParent();
        for (Component c: ((Container)parent.getComponent(0)).getComponents()) 
        {
            System.out.println(c);
            if (c instanceof Checkbox) {
                if (((Checkbox) c).getState() == true) {
                    Client.game_type = ((Checkbox) c).getLabel();
                    System.out.println(Client.game_type);
                }
            }
        }
    }
    
}
