/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import View.ScreenEngine;
import java.util.ArrayList;
import java.util.Timer;

/**
 *
 * @author andrey
 */

public class GameEngine implements Runnable {
         
    protected static final double tick = 0.02;
    
    private static Table t; // Стол
    private static ArrayList<CueBall> balls;
    private static MasterBall master = new MasterBall(0.5, 0.5);
    private static Cue cue;
    private static ScreenEngine screng;

    static ScreenEngine getScreenEngine() {
        return screng;
    }
       
    public GameEngine() {
        balls = new ArrayList<>();
        cue = new Cue();
        t = new Table();
    }
          
    @Override
    public void run() {
        if (StateManager.state == StateManager.MOVEMENT) {
            boolean stop = false;
            
            while (true) {
                
                long startTime = System.currentTimeMillis();
                
                for (CueBall b: balls) {
                    if (master.interactionCheck(b)) b.interact(master);
                
                    for (CueBall b2: balls) {
                        if (!b.equals(b2)) {
                            if (b2.interactionCheck(b)) b2.interact(b);
                        }
                    }
            
                    for (Pocket p: t.pocket_list) {
                        if (p.interactionCheck(b)) p.interact(b);
                    }
            
                    if (t.bb.interactionCheck(b)) t.bb.interact(b);
                    if (t.tb.interactionCheck(b)) t.tb.interact(b);
                    if (t.lb.interactionCheck(b)) t.lb.interact(b);
                    if (t.rb.interactionCheck(b)) t.rb.interact(b);
            
                    boolean tmp = b.update();
                    stop = tmp || stop;
                
                }
            
                if (t.bb.interactionCheck(master)) t.bb.interact(master);
                if (t.tb.interactionCheck(master)) t.tb.interact(master);
                if (t.lb.interactionCheck(master)) t.lb.interact(master);
                if (t.rb.interactionCheck(master)) t.rb.interact(master);
                
                for (Pocket p: t.pocket_list) {
                    if (p.interactionCheck(master)) p.interact(master);
                }
            
                boolean tmp = master.update();
                stop = tmp || stop;
                screng.update();
                
                long estimatedTime = System.currentTimeMillis() - startTime;
                
                if (estimatedTime < 20) {
                    try {
                        Thread.sleep((20 - estimatedTime));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } 
                }
                
                if (!stop) break;
                stop = false;
            }
        } 
      
    }
    
    
    public void AddBall(CueBall b) {
        balls.add(b);
    }
    
    
    public static ArrayList<CueBall> getCueBallList() {
        return balls;
    }
    
    public static Table getTable() {
        return t;
    }
    
    public void AddScreenEngine(ScreenEngine e) {
        screng = e;
    }

    public static MasterBall getMasterBall() {
        return master;
    }
    
    public static Cue getCue() {
        return cue;
    }
   
}
