/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import View.CueDrawer;

/**
 *
 * @author andrey
 */
public class Cue extends GameObject {
    
    private CueDrawer drawer;
    
    Cue() {
        super(0, 0);
    }
   
    
    public void update() {
        drawer.update();
    }
    
    public void addDrawer(CueDrawer d) {
        drawer = d;
    }

    @Override
    boolean interactionCheck(CueBall b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void interact(CueBall b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
