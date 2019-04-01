/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import Model.GameObject;
import java.awt.Graphics;
import javax.imageio.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 *
 * @author andrey
 */
abstract class GameObjectDrawer {
    
    Image sprite;
    protected int x = 0;
    protected int y = 0;
    
    GameObjectDrawer(String path_to_sprite) {
        if (path_to_sprite != null) {
            try {
                sprite = ImageIO.read(
                    new File(getClass().getResource(path_to_sprite).toURI())
                );
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    
    void setCoords(int coord1, int coord2) {
        x = coord1;
        y = coord2;
    }
    
    abstract protected void draw(Graphics g);
   
    abstract public void update(double x, double y);     
    
}
