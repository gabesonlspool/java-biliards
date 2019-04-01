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
    
    private static int w = 45;
    private static int h = 45;
       
    public CueBallDrawer(CueBall ball) {
        super("Sprite/Ball" + Integer.toString(ball.number) + ".jpeg");
        int[] tmp = this.calculatePos(ball.x, ball.y);
        x = tmp[0];
        y = tmp[1];
    }
    
    protected void draw(Graphics g) {
        g.setClip(new Ellipse2D.Float(x + 2, y + 2, 39, 39));
        g.drawImage(sprite, x, y, w, h, null);
    }
    
    private int[] calculatePos(double x, double y) {
        int[] result = new int[2];
        result[0] = (int) Math.round(
            ScreenEngine.CANVAS_WIDTH * x / GameObject.max_width
        );
        result[1] = (int) Math.round(
            ScreenEngine.CANVAS_HEIGHT * y / GameObject.max_height
        );
        return result;   
    }

    public void update(double x, double y) {
        int [] pos = new int[2];
        pos = calculatePos(x, y);
        this.setCoords(pos[0], pos[1]);
    }
    
    
}
