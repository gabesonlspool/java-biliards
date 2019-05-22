/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.awt.Graphics;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author andrey
 */
abstract class GameObjectDrawer {
    
    BufferedImage sprite;
    protected int x = 0;
    protected int y = 0;
    
    GameObjectDrawer(String path_to_sprite) {
        super();
        if (path_to_sprite != null) {
            try {
                sprite = ImageIO.read(
                    getClass().getResourceAsStream(path_to_sprite)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
                  
    }
    
    void setCoords(int coord1, int coord2) {
        x = coord1;
        y = coord2;
    }
   
    abstract public void update(double x, double y);
    abstract protected void draw(Graphics g);
    
}
