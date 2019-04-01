/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CueBall;
import Model.GameEngine;
import Model.Table;
import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author andrey
 */
public class ScreenEngine extends Canvas {
    
    protected static int CANVAS_WIDTH;
    protected static int CANVAS_HEIGHT;
    ArrayList<CueBallDrawer> ball_drawers;
    TableDrawer table_drawer;
    
    ScreenEngine(GameEngine e, int w, int h) {
        CANVAS_HEIGHT = h;
        CANVAS_WIDTH = w;
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        ball_drawers = new ArrayList<>();
        System.out.println();
        
        ArrayList<CueBall> balls = e.getCueBallList();
        for (CueBall b: balls) {
            CueBallDrawer d = new CueBallDrawer(b);
            ball_drawers.add(d);
            b.addDrawer(d);
        }
        
        Table t = e.getTable();
        table_drawer = new TableDrawer(w, h);
        t.addDrawer(table_drawer);
        
    }
    
    public void paint(Graphics g) {
        table_drawer.draw(g);
        for (CueBallDrawer d: ball_drawers) {
            d.draw(g);
        } 
    }
     
    public void update() {
        this.repaint();
    }
    
}