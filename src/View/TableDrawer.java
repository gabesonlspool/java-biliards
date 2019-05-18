/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.GameObject;
import Model.Pocket;
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
        POCKET_R = (int) Math.round((double) TableDrawer.TABLE_WIDTH *
            Pocket.r / GameObject.max_width);
    }
    
    @Override
    public void update(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int[] calculatePocketPos(double x, double y) {
        int[] result = new int[2];
                
        result[0] =  TableDrawer.offset_x + 
                (int) Math.round(TableDrawer.TABLE_WIDTH * x /
                    GameObject.max_width
                ) - POCKET_R;
        result[1] = TableDrawer.offset_y + 
                (int) Math.round(TableDrawer.TABLE_HEIGHT * y / 
                    GameObject.max_height 
                ) - POCKET_R;
        
        return result;   
    }
    
    protected void draw(Graphics g) {
        g.drawImage(
                sprite, offset_x, offset_y,
                TABLE_WIDTH, TABLE_HEIGHT, null
        );
    }
}
