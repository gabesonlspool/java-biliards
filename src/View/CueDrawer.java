/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CueMouseListener;
import Model.GameEngine;
import Model.GameObject;
import static View.CueBallDrawer.r;
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
    
    private double theta = 0.0;

    public CueDrawer() {
        super("Sprite/Cue.jpeg");
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
        
              
        this.setCoords(
            (int) Math.round(CueBallDrawer.r * (1 + cos) + 13 * sin) + master.x,
            (int) Math.round(CueBallDrawer.r * (1 + sin) - 13 * cos) + master.y 
        );
        
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

    @Override
    public void update(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
