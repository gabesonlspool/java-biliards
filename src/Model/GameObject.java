/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


/**
 *
 * @author andrey
 */
abstract public class GameObject {
    
    public static final double max_width = 2.54;
    public static final double max_height = 1.27;
    
    public double x;
    public double y;
    
    /* Интерактивные объекты могут взаимодействовать во время итераций движка
     * с другими интерактивными объектами */
    protected boolean interactive;
     
    GameObject(double coord1, double coord2,
               boolean behaviour) {
        if (coord1 < 0 || coord1 > max_width ||
            coord2 < 0 || coord2 > max_height)
            throw new IllegalArgumentException("Out of bounds");
        
        x = coord1;
        y = coord2;
        interactive = behaviour;
    }
    
    abstract void update();    
        
}
