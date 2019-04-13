/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Table;
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
        Table.addDrawer(this);
        w = width;
        h = height;
    }
    
    @Override
    public void update(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void draw(Graphics g) {
        g.drawImage(
                sprite, 0,
                0, ScreenEngine.CANVAS_WIDTH,
                ScreenEngine.CANVAS_HEIGHT, null
        );
    }
}
