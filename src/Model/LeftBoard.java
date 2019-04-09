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
public class LeftBoard extends GameObject {
    
    LeftBoard() {
        super(0, max_height/2);
    }  
    
    @Override
    boolean interactionCheck(CueBall b) {
        if (b.x <= CueBall.r) {
            return true;
        }
        return false;
    }
      
}
