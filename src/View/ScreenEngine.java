/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CueMouseListener;
import Controller.CueMouseMotionListener;
import Model.StateManager;
import Net.EngineOutputDataFrame;
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
    
    protected EngineOutputDataFrame dataframe = null;
    protected static int CANVAS_WIDTH;
    protected static int CANVAS_HEIGHT;   
    
    ScreenEngine(int w, int h) {
        
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
        masterdrawer = new MasterBallDrawer();                              
        cuedrawer = new CueDrawer();
        
        balldrawers = new ArrayList<>();    
    }
    
    
    public void setDataFrame(EngineOutputDataFrame f) {
        dataframe = f;
    } 
    
    
    public CueDrawer getCueDrawer() {
        return cuedrawer;
    }
        
    public void update() {
        masterdrawer.update(
                dataframe.masterballcoords[0],
                dataframe.masterballcoords[1]
        );
        if (dataframe.ballcoords != null) {
            for (int i = 0; i < dataframe.ballcoords.size(); i++) {
                balldrawers.get(i).update(
                        dataframe.ballcoords.get(i)[0],
                        dataframe.ballcoords.get(i)[1]
                );   
            }
        }
        cuedrawer.update();
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