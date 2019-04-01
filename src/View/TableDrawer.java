/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import View.GameObjectDrawer;
import java.awt.Graphics;
import java.io.IOException;

/**
 *
 * @author andrey
 */
public class TableDrawer extends GameObjectDrawer {
    
    private static int w;
    private static int h;
       
    public TableDrawer(int width, int height) {
        super("Sprite/Table.jpeg");
        w = width;
        h = height;
    }
    
    public void draw(Graphics g) {
        try {
            boolean result = g.drawImage(sprite, x, y, w, h, null);
            if (!result) throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @Override
    public void update(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
