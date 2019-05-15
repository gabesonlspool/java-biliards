/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

/**
 *
 * @author andrey
 */
public class StateManager {
    
    public static final int AIMING = 0;
    public static final int MOVEMENT = 1;
    public static final int PAUSE = 2;
    
    public int state;
    
    public StateManager() {
        state = AIMING;
    }
      
    public void switchState(int new_state) {
        state = new_state;
    }
    
}
