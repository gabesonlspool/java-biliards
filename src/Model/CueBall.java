/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author andrey
 */

public class CueBall extends GameObject {
    
    public static final int m = 170;
    public static final double r = 5.72e-2;
    public int number;
    double[] V;
    double[] A;

    CueBall(double coord1, double coord2, int n) {
        
        super(coord1, coord2, true);
        number = n;
        V = new double[2];
        A = new double[2];
        
    }
    
    
    protected void Move() {
        
        double t = GameEngine.tick;
        x += V[0] * t  + A[0] / 2 * Math.pow(t, 2);
        y += V[1] * t  + A[1] / 2 * Math.pow(t, 2);
        
    }
    
    public void SetVelocity() {
        
       
    }
   
    
    public void update() {
        this.Move();            
    }
    
    
    public void interact(GameObject o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public boolean equals(GameObject o) {
        
        if (!(o instanceof CueBall)) return false;
        boolean result = true && (((CueBall) o).number == number);
        return result;
        
    }
    
    public boolean interactionCheck(GameObject o) {
        
        if (!o.interactive || this.equals(o)) return false;
        
        if (o instanceof CueBall) {
            if (Math.hypot(this.x - o.x, this.y - o.y) <= CueBall.r)
                return true;
        }
        
        if (o instanceof Pocket) {
            if (Math.hypot(this.x - o.x, this.y - o.y) <= Pocket.r)
                return true;
        }
        
        if (o instanceof TopBoard) {
            if (Math.abs(o.y - Table.height) <= CueBall.r)
                return true;
        }
        
        if (o instanceof BottomBoard) {
            if (o.y <= CueBall.r)
                return true;
        }
        
        if (o instanceof LeftBoard) {
            if (o.x <= CueBall.r)
                return true;
        }
        
        if (o instanceof LeftBoard) {
            if (Math.abs(o.y - Table.height) <= CueBall.r)
                return true;
        }
        
        return false;
        
    }

    void setVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
