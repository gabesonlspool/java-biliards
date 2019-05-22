/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.GameEngine;


/**
 *
 * @author andrey
 */
abstract public class GameObject {
    
    public static final double max_width = 2.54;
    public static final double max_height = 1.436;
    protected static final double BOARD_OFFSET_X = 
            (double) 60 / (double) 1327 * max_width;
    protected static final double BOARD_OFFSET_Y = 
            (double) 60 / (double) 750 * max_height;
    
    public double x;
    public double y;
       
    GameObject(double coord1, double coord2) {
        if (coord1 < 0 || coord1 > max_width ||
            coord2 < 0 || coord2 > max_height) {
            throw new IllegalArgumentException("Out of bounds");
        }
        
        x = coord1;
        y = coord2;
    }
    
    abstract boolean interactionCheck(CueBall b);
    abstract void interact(CueBall b);
        
}
