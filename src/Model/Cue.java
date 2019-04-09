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
public class Cue extends GameObject {
    
    Cue(double coord1, double coord2) {
        super(coord1, coord2);
    }
   
    
    public void update() {}

    @Override
    boolean interactionCheck(CueBall b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
