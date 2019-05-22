/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.GameEngine;
import Content.BallInfo;
import Content.EngineOutputDataFrame;
import Model.Server.GameServiceTask;
import Model.Server.StateManager;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author andrey
 */

public class GameEngine implements Runnable {
                 
    protected static final double tick = 0.02;
    
    private GameServiceTask game_service_task;
    private Table t;
    private StateManager state_manager;
    private ArrayList<CueBall> balls;
    private MasterBall master; 
    private EngineOutputDataFrame df;
    private final String config_file_path = "src/Model/";
       
    public GameEngine(GameServiceTask tsk, StateManager mng, String type) {
        
        balls = new ArrayList<>();
        game_service_task = tsk;
        t = new Table();
        state_manager = mng;
        
        // Ball layout is initialized through .properties files
        
        try {
            
            Properties config = new Properties();
            FileInputStream fis =
                new FileInputStream(config_file_path + type + ".properties");
            
            config.load(fis);
            
            master = new MasterBall(
                    Double.parseDouble(config.getProperty("master.x")),
                    Double.parseDouble(config.getProperty("master.y"))
            );
            
            int ball_number = 
                Integer.parseInt(config.getProperty("ball.total"));
            
            ArrayList<BallInfo> ball_info = new ArrayList<>();
            
            for (int i = 1; i < ball_number + 1; i++) {
                double x = Double.parseDouble(config.getProperty(
                        "ball" + Integer.toString(i) + ".x"
                ));
                double y =  Double.parseDouble(config.getProperty(
                        "ball" + Integer.toString(i) + ".y"
                ));
                AddBall(new CueBall(x, y));
                ball_info.add(new BallInfo(x, y, false));
            }
            
            df = new EngineOutputDataFrame(ball_number);
            df.setData(
                new BallInfo(master.x, master.y, false),
                ball_info
            );         
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
          
    @Override
    public void run() {
        
        while (true) {
            
            long startTime = System.currentTimeMillis();
            
            if (state_manager.state == StateManager.MOVEMENT) {
            
                boolean stop = false;
                ArrayList<BallInfo> new_coords = new ArrayList<>();
                
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
                    stop = stop || tmp;
                    new_coords.add(new BallInfo(b.x, b.y, b.is_scored));
                
                }
            
                if (t.bb.interactionCheck(master)) t.bb.interact(master);
                if (t.tb.interactionCheck(master)) t.tb.interact(master);
                if (t.lb.interactionCheck(master)) t.lb.interact(master);
                if (t.rb.interactionCheck(master)) t.rb.interact(master);
                
                for (Pocket p: t.pocket_list) {
                    if (p.interactionCheck(master)) p.interact(master);
                }
           
                boolean tmp = master.update();
                stop = stop || tmp;
                if (!stop) {
                    state_manager.switchState(StateManager.AIMING);
                }
            
            
                game_service_task.dataframe.setData(
                    new BallInfo(master.x, master.y, false),
                    new_coords
                );
              
            }
            
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
    
    
    private void AddBall(CueBall b) {
        balls.add(b);
    }

    public EngineOutputDataFrame getDataFrame() {
        return df;
    }
    
    public MasterBall getMasterBall() {
        return master;
    }
   
}
