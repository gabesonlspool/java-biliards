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
    
    protected static int r = 
            (int) Math.round(GameObjectDrawer.FIELD_WIDTH * 0.0448) / 2;
    
    public CueBallDrawer(CueBall ball) {
        super("Sprite/Ball" + Integer.toString(ball.number) + ".jpeg");
        ball.addDrawer(this);
        int[] tmp = this.calculatePos(ball.x, ball.y);
        x = tmp[0];
        y = tmp[1];
    }
       
    private int[] calculatePos(double x, double y) {
        int[] result = new int[2];
        int offset_w = (
                ScreenEngine.CANVAS_WIDTH - 
                GameObjectDrawer.FIELD_WIDTH
        )/2;
        int offset_h = (
                ScreenEngine.CANVAS_HEIGHT - 
                GameObjectDrawer.FIELD_HEIGHT
        )/2;
        result[0] = offset_w - r +
                (int) Math.round(GameObjectDrawer.FIELD_WIDTH * x /
                GameObject.max_width
        );
        result[1] = offset_h - r + 
                (int) Math.round(GameObjectDrawer.FIELD_HEIGHT * y / 
                GameObject.max_height
        );
        return result;   
    }

    @Override
    public void update(double x, double y) {
        int [] pos = new int[2];
        pos = calculatePos(x, y);
        this.setCoords(pos[0], pos[1]);
    }
    
    @Override
    protected void draw(Graphics g) {
        g.setClip(new Ellipse2D.Float(x, y, r * 2, r * 2));
        g.drawImage(sprite, x, y, r * 2, r * 2, null);
    } 
}
