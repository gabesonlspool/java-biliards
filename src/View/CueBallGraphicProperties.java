/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CueBall;
import Model.GameObject;


public class CueBallGraphicProperties extends GameObjectGraphicProperties {
    
    protected static int r = 
            (int) Math.round(GameObjectGraphicProperties.FIELD_WIDTH * 0.0448);
    
    public CueBallGraphicProperties(CueBall ball) {
        super("Sprite/Ball" + Integer.toString(ball.number) + ".jpeg");
        int[] tmp = this.calculatePos(ball.x, ball.y);
        x = tmp[0];
        y = tmp[1];
        setSize(r, r);
    }
       
    private int[] calculatePos(double x, double y) {
        int[] result = new int[2];
        int offset_w = (
                ScreenEngine.CANVAS_WIDTH - 
                GameObjectGraphicProperties.FIELD_WIDTH
        )/2;
        int offset_h = (
                ScreenEngine.CANVAS_HEIGHT - 
                GameObjectGraphicProperties.FIELD_HEIGHT
        )/2;
        result[0] = offset_w - r/2 + (int) Math.round(
                GameObjectGraphicProperties.FIELD_WIDTH * x /
                GameObject.max_width
        );
        result[1] = offset_h - r/2 + (int) Math.round(
                GameObjectGraphicProperties.FIELD_HEIGHT * y / 
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
    
    
}
