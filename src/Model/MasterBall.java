/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.MasterBallDrawer;

/**
 *
 * @author andrey
 */
public class MasterBall extends CueBall {
    
    private MasterBallDrawer gprop;
    
    public MasterBall(double coord1, double coord2) {
        super(coord1, coord2);
    }
    
    public void addDrawer(MasterBallDrawer d) {
        gprop = d;
    }
    
    public boolean update() {
        boolean result = this.Move();
        gprop.update(x, y);
        return result;
    }
    
}
