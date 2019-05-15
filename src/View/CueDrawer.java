/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CueBall;
import Model.GameEngine;
import Net.Client;
import Net.StateManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

/**
 *
 * @author andrey
 */
public class CueDrawer extends GameObjectDrawer {
    
    protected double[] V;
    
    private double theta = 0.0;
    private final int PULL_RANGE = 200;
    private ScreenEngine screng;
    

    public CueDrawer(ScreenEngine scr) {
        super("Sprite/Cue.jpeg");
        screng = scr;
        V = new double[] {0.0, 0.0};
    }    
       
    
    @Override
    protected void draw(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        double[] clip_points = {
            4, 12,  
            1120, 4,
            1120, 22,
            4, 17
        };
        
        double[] new_clip_points = new double[8];
        AffineTransform trans = new AffineTransform();
        trans.translate(x, y);
        trans.rotate(theta);
        trans.transform(clip_points, 0, new_clip_points, 0, 4);
        
        Polygon tmp = new Polygon();
        for (int i = 0; i < 4; i++) {
            tmp.addPoint(
                (int) Math.round(new_clip_points[2 * i]),
                (int) Math.round(new_clip_points[2 * i + 1])
            );
        }
        
        g2d.setClip(tmp);
        g2d.drawImage(sprite, trans, null);
    }   

    public void update() {
        
        if (Client.state_manager.state == StateManager.AIMING) {
                     
            Point mouse  = MouseInfo.getPointerInfo().getLocation();
            Point master = MasterBallDrawer.getCoords();
            double sin = ((double) (mouse.y - master.y - CueBallDrawer.r)) /
                (Math.hypot(
                        ((double)(mouse.y - CueBallDrawer.r -  master.y)),
                        ((double)(mouse.x - CueBallDrawer.r - master.x))
                    )
            );
        
            double cos = ((double) (mouse.x - CueBallDrawer.r - master.x)) /
                (Math.hypot(
                        ((double)(mouse.y - CueBallDrawer.r -  master.y)),
                        ((double)(mouse.x - CueBallDrawer.r - master.x))
                    )
                );
            
            
            double projection = 
                    (
                        ((double) mouse.x - 
                        Math.round (
                            CueBallDrawer.r * (1 + Math.cos(theta)) +
                            13 * Math.sin(theta)
                            + master.x
                        )) * (double) Math.cos(theta)
                    ) 
                    +
                    (
                        ((double) mouse.y - 
                        Math.round (
                            CueBallDrawer.r * (1 + Math.sin(theta)) +
                            13 * Math.cos(theta)
                            + master.y
                        )) * (double) Math.sin(theta)
                    );
            
            int shift = Math.min((int) projection, (int) PULL_RANGE);
  
            
            this.setCoords(
                (int) Math.round (
                    CueBallDrawer.r * (1 + Math.cos(theta)) +
                    shift * Math.cos(theta) + 13 * Math.sin(theta)
                ) + master.x,
                (int) Math.round(
                    CueBallDrawer.r * (1 + Math.sin(theta)) +
                    shift * Math.sin(theta) - 13 * Math.cos(theta)
                ) + master.y 
            );
            
            
            V[0] =
                -(double) shift/PULL_RANGE * CueBall.V_MAX * Math.cos(theta);
            V[1] = 
                -(double) shift/PULL_RANGE * CueBall.V_MAX * Math.sin(theta);
        
            double offset = (cos > 0) ? 0 : Math.PI;
            if (Math.abs(cos) > 1e-5) {
                theta = Math.atan(sin/cos) + offset;
            } else {
                if (sin > 0) theta = Math.PI / 2;
                if (sin < 0) theta = Math.PI * 3 / 2;
            }
        
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }           
        } 
    }
    
    
    public double[] getStrikeVelocity() {
        return V;
    }

    @Override
    public void update(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
