/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author andrey
 */
public class EngineOutputDataFrame implements Serializable {
    
    public BallInfo master_ball_info;
    public ArrayList<BallInfo> ball_info;
    public int ball_num;
    public int state;
    
    public EngineOutputDataFrame(int n) {
        master_ball_info = null;
        ball_info = null;
        ball_num = n;
        state = StateManager.AIMING;
    }
        
    public void setData(
            int st, BallInfo new_mbinfo,
            ArrayList<BallInfo> new_binfo
    ) {
        state = st;
        master_ball_info = new_mbinfo;
        ball_info = new_binfo;
    }
    
}
