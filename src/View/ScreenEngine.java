/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CueMouseListener;
import Controller.CueMouseMotionListener;
import Model.Cue;
import Model.CueBall;
import Model.GameEngine;
import Model.MasterBall;
import Model.StateManager;
import Model.Table;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author andrey
 */
public class ScreenEngine extends Canvas {
    
    private ArrayList<CueBallDrawer> balldrawers;
    protected MasterBallDrawer masterdrawer;
    private TableDrawer tabledrawer;
    private CueDrawer cuedrawer;
    private BufferedImage background;
    private BufferedImage blank;
    
    protected static int CANVAS_WIDTH;
    protected static int CANVAS_HEIGHT;   
    
    ScreenEngine(GameEngine engine, int w, int h) {
        
        try {
            blank = ImageIO.read(
                new File(getClass().getResource("Sprite/blank.jpeg").toURI())
            );
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        
        try {
            background = ImageIO.read(
                new File(getClass().getResource("Sprite/Background.jpeg").toURI())
            );
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        
        CANVAS_HEIGHT = h;
        CANVAS_WIDTH = w;
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        addMouseListener(new CueMouseListener());
        addMouseMotionListener(new CueMouseMotionListener());
        
        tabledrawer = new TableDrawer();
        Table.addDrawer(tabledrawer);
        
        MasterBall mb = engine.getMasterBall();
        masterdrawer = new MasterBallDrawer(mb);
        mb.addDrawer(masterdrawer);                                   
        
        cuedrawer = new CueDrawer();
        Cue cue = engine.getCue();
        cue.addDrawer(cuedrawer);
        
        balldrawers = new ArrayList<>(); 
        ArrayList<CueBall> balls = engine.getCueBallList();
        for (CueBall b: balls) {
            CueBallDrawer d = new CueBallDrawer(b);
            balldrawers.add(d);
            b.addDrawer(d);                   
        }
        
    }
    
    
    public CueDrawer getCueDrawer() {
        return cuedrawer;
    }
        
    public void update() {
        paint(this.getGraphics());
    }
               
    
    @Override
    public void paint(Graphics g) {
        
        Graphics bar = blank.getGraphics();
        bar.drawImage(background, 0, 0,
                CANVAS_WIDTH, CANVAS_HEIGHT, null);
        tabledrawer.draw(bar);     
        masterdrawer.draw(bar);
        for (CueBallDrawer d: balldrawers) {
            d.draw(bar);
        }
        if (StateManager.state == StateManager.AIMING) {
            cuedrawer.draw(bar);
        }
        
        g.drawImage(
                blank, 0, 0,
                CANVAS_WIDTH, CANVAS_HEIGHT, null
        );
        
    }
}