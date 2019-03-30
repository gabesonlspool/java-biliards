/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import java.awt.Image;
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
    
    abstract void draw();
    
}
