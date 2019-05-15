/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Buttons;

import Controller.MouseButtonClickHandler;
import Net.Client;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.MouseListener;

/**
 *
 * @author andrey
 */
public class OptionsButton extends GameButton {

    public OptionsButton(MouseListener l) {
        super(l, "Options");
    }
    
    @Override
    public void update() {
        
        Frame f = (Frame) this.getParent().getParent();
        
        Dialog d = new Dialog(f, "Options");          
        d.setBounds(0, 0, f.getWidth(), f.getHeight());
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();  
        d.setLayout(grid);
                    
        gbc.fill = gbc.REMAINDER;
        gbc.gridwidth = 1;
        gbc.anchor = gbc.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        
        Container tmp_checkbox_container = new Container();
        
        tmp_checkbox_container.setLayout(new GridLayout(3, 2));
        tmp_checkbox_container.setBounds(
            0, 0, f.getWidth(), f.getHeight() * 3/4
        );
        Label l = new Label("Ball Layout", Label.CENTER);
        l.setFont(new Font("Ubuntu",Font.BOLD, 24));
        tmp_checkbox_container.add(l);
        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox checkBox1 = new Checkbox("Classic", cbg, true);
        checkBox1.setSize(400, 200);
        checkBox1.setFont(new Font("Ubuntu",Font.PLAIN, 24));
        tmp_checkbox_container.add(checkBox1);
        Checkbox checkBox2 = new Checkbox("Diamond", cbg, false);
        checkBox2.setFont(new Font("Ubuntu",Font.PLAIN, 24));
        checkBox2.setSize(400, 200);
        tmp_checkbox_container.add(checkBox2);
        tmp_checkbox_container.setVisible(true);
        d.add(tmp_checkbox_container, gbc);   
        
        Container tmp_button_container = new Container();
        tmp_button_container.setBounds(
            0, f.getHeight() * 3/4, f.getWidth(), f.getHeight() * 1/4
        );
        tmp_button_container.setLayout(new FlowLayout());
        tmp_button_container.add(
            new SaveSettingsButton(new MouseButtonClickHandler())
        );
        tmp_button_container.add(
            new BackButton(new MouseButtonClickHandler())
        );
        
        gbc.fill = gbc.REMAINDER;
        gbc.gridwidth = 1;
        gbc.anchor = gbc.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        d.add(tmp_button_container, gbc);
        d.setVisible(true);
        
    }
    
}
