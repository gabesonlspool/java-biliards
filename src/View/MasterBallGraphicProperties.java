/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MasterBallMouseHandler;
import Model.CueBall;


public class MasterBallGraphicProperties extends CueBallGraphicProperties {
           
    MasterBallGraphicProperties(CueBall b, MasterBallMouseHandler h) {
        super(b);
        addMouseListener(h);
    }
   
    @Override
    public void update(double x, double y) {
        super.update(x, y);
    }
}
