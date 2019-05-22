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
public class LeftBoard extends GameObject {
    
    LeftBoard() {
        super(0, max_height/2);
    }  
    
    @Override
    boolean interactionCheck(CueBall b) {
        double [] pc = b.predictCoords();
        
        return true && (
            pc[0] <= CueBall.r + BOARD_OFFSET_X &&
            (!b.is_scored) && 
            pc[1] > Pocket.r + BOARD_OFFSET_Y &&
            pc[1] < max_height - Pocket.r - BOARD_OFFSET_Y
        );
    }

    @Override
    void interact(CueBall b) {
        b.setVelocity(-b.V[0], b.V[1]);
    }
      
}
