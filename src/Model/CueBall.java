/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.CueBallDrawer;


public class CueBall extends GameObject {
    
    private static final double m = 0.17; // масса шара
    private CueBallDrawer drawer;
    private static final double f = 0.25; //коэффициент трения
    private boolean is_scored;
    
    
    protected static int count = 0; // общее количество шаров на столе
    protected double[] V;
    protected double[] A;
    
    public static final double r = 0.087 / 2; // радиус шара
    public static final double V_MAX = 2.0;
    public int number = 0;
    
    public CueBall(double coord1, double coord2) {
        super(coord1, coord2);
        is_scored = false; 
        V = new double[2];
        A = new double[2];
        number = count;
        
        count++;
    }
        
    protected boolean Move() {
        
        // Если шары перестают двигаться, то движок должен остановиться
        
        if (V[0] == 0 && V[1] == 0) {
            return false;
        }
        
        if (Math.abs(V[0]) < 0.5e-2 && A[0] != 0) {
            A[0] = 0;
            V[0] = 0;
        }
        
        if (Math.abs(V[1]) < 0.5e-2 && A[1] != 0) {
            A[1] = 0;
            V[1] = 0;
        }
                    
        double t = GameEngine.tick;
        x += V[0] * t  + A[0] / 2 * Math.pow(t, 2);
        y += V[1] * t  + A[1] / 2 * Math.pow(t, 2);
        
        V[0] += A[0] * t;
        V[1] += A[1] * t;
        return true;
        
    }
    
    public void setVelocity(double U_x, double U_y ) {
        V[0] = U_x;
        V[1] = U_y;
        A[0] = - f * m * 9.8 * V[0] / Math.hypot(V[0], V[1]);
        A[1] = - f * m * 9.8 * V[1] / Math.hypot(V[0], V[1]);
    }
    
    public boolean update() {
        boolean result = false;
        if (!is_scored) 
            result = result || this.Move();
            drawer.update(x, y);
        return result;
    }
       
    public boolean equals(GameObject o) {
        
        if (!(o instanceof CueBall)) return false;
        return  true && (((CueBall) o).number == number);
        
    }
    
    public void addDrawer(CueBallDrawer d) {
        drawer = d;
    }
    
    @Override
    boolean interactionCheck(CueBall b) {
        if (Math.hypot(x - b.x, y - b.y) <= 1.95 * CueBall.r) {
            return true; 
        }             
        return false;
    }
    
    
    @Override
    protected void interact(CueBall b) {
        Physics.calculateCollision(b, this);
    }

    protected void setScored() {
        is_scored = true;
    }
    
    
}
