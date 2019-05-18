/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CueBall;
import Model.GameObject;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;


public class CueBallDrawer extends GameObjectDrawer {
    
    protected boolean is_inactive;
    
    protected static final int r = 
            (int) Math.round((double) TableDrawer.TABLE_WIDTH *
                    CueBall.r / GameObject.max_width);
        
    public CueBallDrawer(int i) {
        super("Sprite/Ball" + Integer.toString(i) + ".jpeg");        
        x = 0;
        y = 0;
        is_inactive = true;
    }
       
    private int[] calculatePos(double x, double y) {
        int[] result = new int[2];
                
        result[0] =  TableDrawer.offset_x + 
                (int) Math.round(TableDrawer.TABLE_WIDTH * x /
                    GameObject.max_width
                ) - r;
        result[1] = TableDrawer.offset_y + 
                (int) Math.round(TableDrawer.TABLE_HEIGHT * y / 
                    GameObject.max_height 
                ) - r;
        
        return result;   
    }

    public void update(double x, double y, boolean s) {
        int [] pos = calculatePos(x, y);
        this.setCoords(pos[0], pos[1]);
        this.is_inactive = s;
    }
    
    @Override
    protected void draw(Graphics g) {
        if (!this.is_inactive) {
            g.setClip(new Ellipse2D.Float(x, y, r * 2, r * 2));
            g.drawImage(sprite, x, y, r * 2, r * 2, null);
        }
    } 

    @Override
    public void update(double x, double y) {}
    
}
