/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.GameEngine;

import java.io.Serializable;

/*
 * Default data type describing information about the ball in the model
 */
public class BallInfo implements Serializable {
    public double x;
    public double y;
    public boolean is_scored;
    
    public BallInfo(double coord1, double coord2, boolean s) {
        x = coord1;
        y = coord2;
        is_scored = s;
    }
    
}
