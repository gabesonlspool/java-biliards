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
class BottomBoard extends GameObject {
    
    BottomBoard() {
        super(max_width/2, max_height);
    }
    
    void update() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean interactionCheck(CueBall b) {
        
        double [] pc = b.predictCoords();
        return true && (
            Math.abs(pc[1] - max_height + BOARD_OFFSET_Y) <= CueBall.r &&
            pc[0] > Pocket.r + BOARD_OFFSET_X &&
            pc[0] < max_width - Pocket.r - BOARD_OFFSET_X && 
            (pc[0] > max_width/2 + Pocket.r || pc[0] < max_width/2 - Pocket.r)
        );
    }

    @Override
    void interact(CueBall b) {
        b.setVelocity(b.V[0], -b.V[1]);
    }

}
