/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author andrey
 */
public class Table extends GameObject {
    
    ArrayList<Pocket> pocket_list;
    LeftBoard lb; //Left border
    RightBoard rb; //Right border
    TopBoard tb; // Top border
    BottomBoard bb; // Bottom border
    protected static final double width = 2.54;
    protected static double height = 1.27;
    
    Table() {
        super(0, 0, true);
        pocket_list.add(new Pocket(Pocket.r, Pocket.r));
        pocket_list.add(new Pocket(width/2, Pocket.r));
        pocket_list.add(new Pocket(width - Pocket.r, Pocket.r));
        pocket_list.add(new Pocket(Pocket.r, height - Pocket.r));
        pocket_list.add(new Pocket(width/2, height - Pocket.r));
        pocket_list.add(new Pocket(width - Pocket.r, height - Pocket.r));
        lb = new LeftBoard();
        rb = new RightBoard();
        tb = new TopBoard();
        bb = new BottomBoard();
    }

    void update() {
        
        pocket_list.forEach((p) -> {
            p.update();
        });
           
        bb.update();
        bb.update();
        lb.update();
        rb.update();
     
    }
    
}
