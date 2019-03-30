/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;

/**
 *
 * @author andrey
 */
public class CueBallDrawer extends GameObjectDrawer {
    
    public CueBallDrawer(int number) {
        super("Sprite/Ball" + Integer.toString(number) + ".jpeg");
    }
    

    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
