/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MasterBallMouseHandler;
import Model.CueBall;
import Model.GameEngine;
import Model.MasterBall;
import Model.Table;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 *
 * @author andrey
 */
public class ScreenEngine extends Canvas {
    
    protected static int CANVAS_WIDTH;
    protected static int CANVAS_HEIGHT;
    ArrayList<CueBallGraphicProperties> ballprop;
    MasterBallGraphicProperties masterprop;
    TableGraphicProperties tableprop;
    
    ScreenEngine(GameEngine e, int w, int h) {
        
        CANVAS_HEIGHT = h;
        CANVAS_WIDTH = w;
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
          
        MasterBall mb = e.getMasterBall();
        masterprop = new MasterBallGraphicProperties(
                mb,
                new MasterBallMouseHandler()
        );
        mb.addGraphicProperties(masterprop);
        System.out.println(masterprop.getMouseListeners());
        
        ballprop = new ArrayList<>(); 
        ArrayList<CueBall> balls = e.getCueBallList();
        for (CueBall b: balls) {
            CueBallGraphicProperties d = new CueBallGraphicProperties(b);
            ballprop.add(d);
            b.addGraphicProperties(d);                   
        }
        
        Table t = e.getTable();
        tableprop = new TableGraphicProperties(w, h);
        t.addGraphicProperties(tableprop);
        
    }
    
    public void paintComponent(Graphics g) {
        
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics bar = bs.getDrawGraphics();
        bar.drawImage(
                tableprop.sprite, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, null
        );
        bar.dispose();
        
        bar = bs.getDrawGraphics();
        bar.setClip(new Ellipse2D.Float(
                masterprop.x,
                masterprop.y,
                masterprop.r,
                masterprop.r
        ));
        bar.drawImage(
                masterprop.sprite,
                masterprop.x,
                masterprop.y,
                masterprop.r,
                masterprop.r, null
        );
        bar.dispose();
        for (CueBallGraphicProperties d: ballprop) {
            bar = bs.getDrawGraphics();
            bar.setClip(new Ellipse2D.Float(d.x, d.y, d.r, d.r));
            bar.drawImage(d.sprite, d.x, d.y, d.r, d.r, null);
            bar.dispose();
        }
                
        bs.show();
    }
    
    public void update() {
        Graphics g = this.getGraphics();
        this.paintComponent(g);
    }
        
}