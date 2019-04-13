/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CueMouseListener;
import Model.Cue;
import Model.CueBall;
import Model.GameEngine;
import static Model.GameEngine.ENGINE;
import Model.MasterBall;
import Model.Table;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 *
 * @author andrey
 */
public class ScreenEngine extends Canvas {
    
    private ArrayList<CueBallDrawer> balldrawers;
    protected MasterBallDrawer masterdrawer;
    private TableDrawer tabledrawer;
    private CueDrawer cuedrawer;
    
    protected static int CANVAS_WIDTH;
    protected static int CANVAS_HEIGHT;
   
    
    ScreenEngine(GameEngine e, int w, int h) {
        
        CANVAS_HEIGHT = h;
        CANVAS_WIDTH = w;
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        addMouseMotionListener(new CueMouseListener());
        
        MasterBall mb = e.getMasterBall();
        masterdrawer = new MasterBallDrawer(mb);
        mb.addDrawer(masterdrawer);
        
        cuedrawer = new CueDrawer();
        Cue cue = e.getCue();
        cue.addDrawer(cuedrawer);
        
        balldrawers = new ArrayList<>(); 
        ArrayList<CueBall> balls = e.getCueBallList();
        for (CueBall b: balls) {
            CueBallDrawer d = new CueBallDrawer(b);
            balldrawers.add(d);
            b.addDrawer(d);                   
        }
        
        tabledrawer = new TableDrawer(w, h);
        Table.addDrawer(tabledrawer);
                                      
    }
    
    public CueDrawer getCueDrawer() {
        return cuedrawer;
    }
    
    
    @Override
    public void paint(Graphics g) {
        
        tabledrawer.draw(g);
        masterdrawer.draw(g);
        for (CueBallDrawer d: balldrawers) {
            d.draw(g);
        }
        cuedrawer.draw(g);
        
    }
              
    public void update() {
               
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        
        Graphics bar = bs.getDrawGraphics();
        tabledrawer.draw(bar);
        bar.dispose();
        
        if (ENGINE.state == ENGINE.AIMING) {
            bar = bs.getDrawGraphics();
            cuedrawer.draw(bar);
            bar.dispose();
        }
        
        bar = bs.getDrawGraphics();
        masterdrawer.draw(bar);
        bar.dispose();
        for (CueBallDrawer d: balldrawers) {
            bar = bs.getDrawGraphics();
            d.draw(bar);
            bar.dispose();
        }
        bs.show();
        
    }
}