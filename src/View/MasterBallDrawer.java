/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;


public class MasterBallDrawer extends CueBallDrawer {
    
    static int coord1;
    static int coord2;
    
    MasterBallDrawer() {
        super();
        coord1 = x;
        coord2 = y;
    }
   
    public static Point getCoords() {
        return new Point(coord1, coord2);        
    }
    
    @Override
    public void update(double x, double y) {
        super.update(x, y);
        coord1 = this.x;
        coord2 = this.y;
    }
    
    public void draw (Graphics g) {
        g.setClip(new Ellipse2D.Float(x, y, r * 2, r * 2));
        g.drawImage(sprite, x, y, r * 2, r * 2, null);
    }
    
}
