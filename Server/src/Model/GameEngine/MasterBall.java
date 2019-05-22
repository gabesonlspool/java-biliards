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
public class MasterBall extends CueBall {
        
    public MasterBall(double coord1, double coord2) {
        super(coord1, coord2);
    }
       
    public boolean update() {
        boolean result = this.Move();
        return result;
    }
    
}
