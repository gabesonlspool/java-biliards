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
class RightBoard extends GameObject {
    
    RightBoard() {
        super(max_width, max_width/2);
    }

    @Override
    boolean interactionCheck(CueBall b) {
        double [] pc = b.predictCoords();
        return true && (
            (!b.is_scored) && 
            Math.abs(pc[0] - max_width + BOARD_OFFSET_X) <= CueBall.r &&
            pc[1] > Pocket.r + BOARD_OFFSET_Y &&
            pc[1] < max_height - Pocket.r - BOARD_OFFSET_Y
        );
    }  

    @Override
    void interact(CueBall b) {
        b.setVelocity(-b.V[0], b.V[1]);
    }
}
