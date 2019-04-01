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
    
    public boolean is_working;
    private Timer timer;
     //tick - интервал времени в секундах, через который обновляется движок
    protected static final double tick = 0.001;
    //poll - список объектов движка, унаследованный от TimerTask, c переопределенным методом run;
    private EnginePoll poll;
    // Логично сделать движок singleton-ом
    public static final GameEngine Engine = new GameEngine();   
    
    public GameEngine() {
        is_working = false;
        timer = new Timer(true);
        poll = new EnginePoll();
    }
      
    public void run() {
        is_working = true;
        timer.schedule(poll, 0, Math.round(tick * 1000));
    }
    
    public void pause() {
        is_working = false;
        timer.cancel();
    }
     
    
    public void AddBall(CueBall b) {
        poll.AddBall(b);
    }
                
    public ArrayList<CueBall> getCueBallList() {
        return poll.getCueBallList();
    }
    
    public Table getTable() {
        return poll.getTable();
    }
    
    public void AddScreenEngine(ScreenEngine e) {
        poll.AddScreenEngine(e);
    }
    
}
