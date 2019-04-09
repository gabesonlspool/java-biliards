/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.CueBallGraphicProperties;


public class CueBall extends GameObject {
    
    public static final double m = 0.17; // масса шара
    public static final double r = 0.057; // радиус шара
    private static final double f = 0.25; //коэффициент трения
    // Count - общее количество шаров на столе
    protected static int count = 0;
    // Номер шара
    public int number = 0;
    // Графические свойства шара
    private CueBallGraphicProperties gprop;
    protected double[] V;
    protected double[] A;

    
    public CueBall(double coord1, double coord2) {
        super(coord1, coord2);
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
        
        if (Math.abs(V[0]) < 1e-3 && A[0] != 0) {
            A[0] = 0;
            V[0] = 0;
        }
        
        if (Math.abs(V[1]) < 1e-3 && A[1] != 0) {
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
        boolean result = this.Move();
        gprop.update(x, y);
        return result;
    }
    
    
    public void interact(GameObject o) {
                
        if (o instanceof CueBall) {
            Physics.calculateCollision(this, (CueBall) o);
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
    
    public void addGraphicProperties(CueBallGraphicProperties d) {
        gprop = d;
    }
    
    @Override
    boolean interactionCheck(CueBall b) {
        if (Math.hypot(x - b.x, y - b.y) <= 1.95 * CueBall.r) {
            return true; 
        }             
        return false;
    }
    
    
    
}
