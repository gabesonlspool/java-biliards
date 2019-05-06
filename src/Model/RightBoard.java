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
        return true && (
            Math.abs(b.x - max_width + BOARD_OFFSET_X) <= CueBall.r &&
            b.y > Pocket.r + BOARD_OFFSET_Y &&
            b.y < max_height - Pocket.r - BOARD_OFFSET_Y
        );
    }  

    @Override
    void interact(CueBall b) {
        b.setVelocity(-b.V[0], b.V[1]);
    }
}
