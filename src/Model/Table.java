/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import View.TableDrawer;
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
    TableDrawer drawer;
    
    Table() {
        super(0, 0, true);
        pocket_list = new ArrayList<Pocket>();
        pocket_list.add(new Pocket(Pocket.r, Pocket.r));
        pocket_list.add(new Pocket(max_width/2, Pocket.r));
        pocket_list.add(new Pocket(max_width - Pocket.r, Pocket.r));
        pocket_list.add(new Pocket(Pocket.r, max_height - Pocket.r));
        pocket_list.add(new Pocket(max_width/2, max_height - Pocket.r));
        pocket_list.add(new Pocket(max_width - Pocket.r, max_height - Pocket.r));
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
        //drawer.draw();
    }
    
    public void addDrawer(TableDrawer d) {
        drawer = d;
    }
    
}
