/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CueMouseListener;
import Controller.CueMouseMotionListener;
import Net.Client;
import Net.StateManager;
import Net.EngineOutputDataFrame;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author andrey
 */
public class ScreenEngine extends Canvas {
    
    public EngineOutputDataFrame dataframe = null;
    
    protected MasterBallDrawer masterdrawer;
    protected static int CANVAS_WIDTH;
    protected static int CANVAS_HEIGHT;  
    
    private MainWindow mainwindow;
    private ArrayList<CueBallDrawer> balldrawers;
    private TableDrawer tabledrawer;
    private CueDrawer cuedrawer;
    private BufferedImage background;
    private BufferedImage blank;
        
    ScreenEngine(int w, int h) {
        
        CANVAS_HEIGHT = h;
        CANVAS_WIDTH = w;
                
        try {
            blank = ImageIO.read(
                getClass().getResourceAsStream("Sprite/blank.jpeg")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            background = ImageIO.read(
                getClass().getResourceAsStream("Sprite/Background.jpeg")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
    
    public void initBallDrawers() {
        for (int i = 1; i < dataframe.ball_num + 1; i++) {
            balldrawers.add(new CueBallDrawer(i));
        }
    }
    
    public void update(int state, boolean turn) {
        
        System.out.println(turn);
        
        masterdrawer.update(
                dataframe.master_ball_info.x,
                dataframe.master_ball_info.y,
                dataframe.master_ball_info.is_scored
        );
        
        if (dataframe.ball_info != null) {
            for (int i = 0; i < dataframe.ball_info.size(); i++) {
                balldrawers.get(i).update(
                        dataframe.ball_info.get(i).x,
                        dataframe.ball_info.get(i).y,
                        dataframe.ball_info.get(i).is_scored
                );   
            }
        }
        
        if (state == StateManager.AIMING && turn) cuedrawer.update();
        paint(this.getGraphics(), state, turn);
    }
               
    
    public void paint(Graphics g, int state, boolean turn) {
        
        Graphics bar = blank.getGraphics();
        bar.drawImage(background, 0, 0,
                CANVAS_WIDTH, CANVAS_HEIGHT, null);
        tabledrawer.draw(bar);     
        masterdrawer.draw(bar);
        for (CueBallDrawer d: balldrawers) {
            d.draw(bar);
        }
        
        if (state == StateManager.AIMING && turn) {
            cuedrawer.draw(bar);
        }
        
        g.drawImage(
                blank, 0, 0,
                CANVAS_WIDTH, CANVAS_HEIGHT, null
        );
    }
    
    public CueDrawer getCueDrawer() {
        return cuedrawer;
    }
    
}