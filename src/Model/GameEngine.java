/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import View.ScreenEngine;
import java.util.ArrayList;
import Net.EngineOutputDataFrame;

/**
 *
 * @author andrey
 */

public class GameEngine implements Runnable {
         
    protected static final double tick = 0.02;
    
    private Table t;
    private ArrayList<CueBall> balls;
    private MasterBall master = new MasterBall(0.5, 0.5);
    private Cue cue;
    private ScreenEngine screng;
    private EngineOutputDataFrame df;
       
    public GameEngine() {
        balls = new ArrayList<>();
        cue = new Cue();
        t = new Table();
        master.setVelocity(2.0, 1.5);
        df = new EngineOutputDataFrame();
        df.setData(new double[] {master.x, master.y}, null);
    }
          
    @Override
    public void run() {
        if (StateManager.state == StateManager.MOVEMENT) {
            
            long startTime = System.currentTimeMillis();
            
            ArrayList<double[]> new_coords = new ArrayList<double[]>();
                
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
            
                b.update();
                new_coords.add(new double[] {b.x, b.y});
            }
            
            if (t.bb.interactionCheck(master)) t.bb.interact(master);
            if (t.tb.interactionCheck(master)) t.tb.interact(master);
            if (t.lb.interactionCheck(master)) t.lb.interact(master);
            if (t.rb.interactionCheck(master)) t.rb.interact(master);
                
            for (Pocket p: t.pocket_list) {
                if (p.interactionCheck(master)) p.interact(master);
            }
            
            master.update();            
            df.setData(new double[] {master.x, master.y}, new_coords);
                
            long estimatedTime = System.currentTimeMillis() - startTime;
                
            if (estimatedTime < 20) {
                try {
                    Thread.sleep((20 - estimatedTime));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
            }
                
        } 
      
    }
    
    
    public void AddBall(CueBall b) {
        balls.add(b);
    }
    
    
    public ArrayList<CueBall> getCueBallList() {
        return balls;
    }
    
    public Table getTable() {
        return t;
    }
    
    public void AddScreenEngine(ScreenEngine e) {
        screng = e;
    }

    public MasterBall getMasterBall() {
        return master;
    }
    
    public Cue getCue() {
        return cue;
    }

    public EngineOutputDataFrame getDataFrame() {
        return df;
    }
   
}
