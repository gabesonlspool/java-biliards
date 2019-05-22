/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.Parameters;
import java.awt.Graphics;

/**
 *
 * @author andrey
 */
public class TableDrawer extends GameObjectDrawer {
    
    protected static int TABLE_WIDTH;
    protected static int TABLE_HEIGHT;
    protected static int offset_x; 
    protected static int offset_y;
    
    private static int POCKET_R; 
           
    public TableDrawer() {
        super("Sprite/Table.jpeg");
        TABLE_WIDTH = sprite.getWidth();
        TABLE_HEIGHT = sprite.getHeight();
        offset_x = (ScreenEngine.CANVAS_WIDTH - TABLE_WIDTH)/2;
        offset_y = (ScreenEngine.CANVAS_HEIGHT - TABLE_HEIGHT)/2;
    }
    
    @Override
    public void update(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    protected void draw(Graphics g) {
        g.drawImage(
                sprite, offset_x, offset_y,
                TABLE_WIDTH, TABLE_HEIGHT, null
        );
    }
}
