/*
change 
this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.Vector;

public class Physics {
    public static final Physics phys = new Physics();
    
    static void calculateCollision(CueBall b1, CueBall b2) {
                
        /*Сначала повернем систему координат так,   
         *чтобы центры шаров лежали на оси x */
            
        double dist = Math.hypot(b1.x - b2.x, b1.y - b2.y);        
        double turn_cos = 
            (b1.x > b2.x) ? 
            ((b1.x - b2.x) / dist) : ((b2.x - b1.x) / dist);
        
        double turn_sin = 
            (b1.x > b2.x) ? 
            ((b1.y - b2.y) / dist) : ((b2.y - b1.y) / dist);
              
        double b1_vx_b = b1.V[0] * turn_cos + b1.V[1] * turn_sin;
        double b1_vy_b = b1.V[1] * turn_cos - b1.V[0] * turn_sin;
        
        double b2_vx_b = b2.V[0] * turn_cos + b2.V[1] * turn_sin;
        double b2_vy_b = b2.V[1] * turn_cos - b2.V[0] * turn_sin;
               
        //Будем считать, что тангенциальной компоненты силы при ударе не возникает
       
        double b1_vx_a = b2_vx_b;
        double b1_vy_a = b1_vy_b;
                
        double b2_vx_a = b1_vx_b;
        double b2_vy_a = b2_vy_b;
        
        // Вернемся в исходную систему координат, и пересчитаем скорости шаров
        
        b1.setVelocity(
                b1_vx_a * turn_cos - b1_vy_a * turn_sin,
                b1_vy_a * turn_cos + b1_vx_a * turn_sin
        );
        
        b2.setVelocity(
                b2_vx_a * turn_cos - b2_vy_a * turn_sin,
                b2_vy_a * turn_cos + b2_vx_a * turn_sin
        );               
    } 
       
    Vector<Double> calculateCueInteraction(CueBall b) {
        
        return new Vector<Double>();
        
    }
}
