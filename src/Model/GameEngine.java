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
    
    public static boolean is_working;
    private static Timer timer;
     //tick - интервал времени в секундах, через который обновляется движок
    protected static final double tick = 0.001;
    // Логично сделать движок singleton-ом
    private static Table t; // Стол
    private static ArrayList<CueBall> balls;
    private static MasterBall master = new MasterBall(0.5, 0.5);// Список шаров на столе
    private static ScreenEngine screng; // Отрисовщик игорового поля
    public static final GameEngine Engine = new GameEngine();   
    
    public GameEngine() {
        is_working = false;
        balls = new ArrayList<>();
        t = new Table();
    }
      
    public static void run() {
       
        is_working = true;
        boolean stop = false;
        
        while (true) {
            
            for (CueBall b: balls) {
                if (master.interactionCheck(b)) master.interact(b);
                
                for (CueBall b2: balls) {
                    if (!b.equals(b2)) {
                        if (b2.interactionCheck(b)) b.interact(b2);
                    }
                }
            
                for (Pocket p: t.pocket_list) {
                    if (p.interactionCheck(b)) b.interact(p);
                }
            
                if (t.bb.interactionCheck(b)) b.interact(t.bb);
                if (t.tb.interactionCheck(b)) b.interact(t.tb);
                if (t.lb.interactionCheck(b)) b.interact(t.lb);
                if (t.rb.interactionCheck(b)) b.interact(t.rb);
            
                boolean tmp = b.update();
                stop = tmp || stop;
                
            }
            
            if (t.bb.interactionCheck(master)) master.interact(t.bb);
            if (t.tb.interactionCheck(master)) master.interact(t.tb);
            if (t.lb.interactionCheck(master)) master.interact(t.lb);
            if (t.rb.interactionCheck(master)) master.interact(t.rb);
            
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
    }
    
    public static void pause() {
        is_working = false;
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
    
    
}
