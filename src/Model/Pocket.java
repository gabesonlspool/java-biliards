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

public class Pocket extends GameObject {
    
    public static final double r = 82e-3;
    
    Pocket(double coord1, double coord2){
        super(coord1, coord2, true);
    }

    void update() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
