package Model.Server;

/**
 * An API for managing shared states of model and view
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
