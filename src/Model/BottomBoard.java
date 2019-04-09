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
        if (Math.abs(b.y - max_height) <= CueBall.r) {
            return true;
        }
        return false;
    }

}
