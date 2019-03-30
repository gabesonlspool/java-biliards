/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 *
 * @author andrey
 */
public class EnginePoll extends TimerTask{
    
    Table t;
    ArrayList<CueBall> balls; 
    
    EnginePoll() {
        t = new Table();
        balls = new ArrayList<CueBall>();
    }
    
    protected void AddBall(CueBall obj) {
        balls.add(obj);
    }

    public void run() {
        
        for (CueBall b: balls) {
            
            for (CueBall b2: balls) {
                 if (b.interactionCheck(b2)) System.out.println(b2.number);
                if (b.interactionCheck(b2)) b.interact(b2);
            }
            
            for (Pocket p: t.pocket_list) {
                if (b.interactionCheck(p)) b.interact(p);
            }
            
            if (b.interactionCheck(t.bb)) b.interact(t.bb);
            if (b.interactionCheck(t.tb)) b.interact(t.tb);
            if (b.interactionCheck(t.lb)) b.interact(t.lb);
            if (b.interactionCheck(t.rb)) b.interact(t.rb);
            
            b.update();
        }
        
        t.update();
    }
    
}
