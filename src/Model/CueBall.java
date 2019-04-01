/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.CueBallDrawer;

/**
 *
 * @author andrey
 */

public class CueBall extends GameObject {
    
    public static final double m = 0.17;
    public static final double r = 5.72e-2;
    // Count - общее количество шаров на столе
    public static int count = 0;
    // Номер шара
    public int number;
    // Отрисовщик шара
    CueBallDrawer drawer;
    private double[] V;
    private double[] A;

    public CueBall(double coord1, double coord2) {
        super(coord1, coord2, true);
                
        number = count;
        V = new double[2];
        A = new double[2];
        count++;
        
    }
        
    protected void Move() {
        double t = GameEngine.tick;
        x += V[0] * t  + A[0] / 2 * Math.pow(t, 2);
        y += V[1] * t  + A[1] / 2 * Math.pow(t, 2);
        
    }
    
    public void setVelocity(double U_x, double U_y ) {
        V[0] = U_x;
        V[1] = U_y;
    }
    
    private void setAcceleration(double[] U) {
       A[0] = U[0];
       A[1] = U[1];
    }
   
    
    public void update() {
        this.Move();
        drawer.update(x, y);
    }
    
    
    public void interact(GameObject o) {
                
        if (o instanceof CueBall) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        if (o instanceof Pocket) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        if (o instanceof TopBoard) {
            this.setVelocity(V[0], -V[1]);
        }
        
        if (o instanceof BottomBoard) {
            this.setVelocity(V[0], -V[1]);
        }
        
        if (o instanceof LeftBoard) {
            this.setVelocity(-V[0], V[1]);
        }
        
        if (o instanceof RightBoard) {
            this.setVelocity(-V[0], V[1]);
        }
    }
    
    
    public boolean equals(GameObject o) {
        
        if (!(o instanceof CueBall)) return false;
        return  true && (((CueBall) o).number == number);
        
    }
    
    public boolean interactionCheck(GameObject o) {
        
        if (this.equals(o)) return false;
        
        if (o instanceof CueBall) {
            if (Math.hypot(this.x - o.x, this.y - o.y) <= CueBall.r)
                return true;
        }
        
        if (o instanceof Pocket) {
            if (Math.hypot(this.x - o.x, this.y - o.y) <= Pocket.r) {
                System.out.printf("%f %f\n", o.x, o.y);
                return true;
            }
        }
        
        if (o instanceof BottomBoard) {
            if (Math.abs(y - max_height) <= CueBall.r)
                return true;
        }
        
        if (o instanceof TopBoard) {
            if (y <= CueBall.r)
                return true;
        }
        
        if (o instanceof LeftBoard) {
            if (x <= CueBall.r)
                return true;
        }
        
        if (o instanceof RightBoard) {
            if (Math.abs(x - max_width) <= CueBall.r)
                return true;
        }
        
        return false;
        
    }

    public void addDrawer(CueBallDrawer d) {
        drawer = d;
    }
    
}
