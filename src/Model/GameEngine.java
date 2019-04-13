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

public class GameEngine {
    
    public static final boolean AIMING   = false;
    public static final boolean MOVEMENT = true;
    
    
    public static boolean state;
     //tick - интервал времени в секундах, через который обновляется движок
    protected static final double tick = 0.001;
    // Логично сделать движок singleton-ом
    private static Table t; // Стол
    private static ArrayList<CueBall> balls;
    private static MasterBall master = new MasterBall(0.5, 0.5);// Список шаров на столе
    private static Cue cue;
    private static ScreenEngine screng; // Отрисовщик игорового поля
    public static final GameEngine ENGINE = new GameEngine();   
    
    public GameEngine() {
        state = AIMING;
        balls = new ArrayList<>();
        cue = new Cue();
        t = new Table();
    }
      
    public static void run() {
               
        if (state == MOVEMENT) {
            boolean stop = false;
            while (true) {
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
            
                screng.update();
                boolean tmp = master.update();
                stop = tmp || stop;
            
                if (!stop) break;
                stop = false;
            
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            switchState();
        } 
               
    }
    
    public static void switchState() {
        if (state == AIMING) {
            state = MOVEMENT;
        } else state = AIMING;
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
   
}
