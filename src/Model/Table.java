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
    
    public ArrayList<Pocket> pocket_list;
    LeftBoard lb; //Left border
    RightBoard rb; //Right border
    TopBoard tb; // Top border
    BottomBoard bb; // Bottom border
    static TableDrawer drawer;
    
    Table() {
        super(0, 0);
        pocket_list = new ArrayList<>();
        pocket_list.add(new Pocket(0.112, 0.128));
        pocket_list.add(new Pocket(max_width/2 - 0.018, 0.108));
        pocket_list.add(new Pocket(max_width - 0.134, 0.134));
        pocket_list.add(new Pocket(0.112, max_height - 0.128));
        pocket_list.add(new Pocket(max_width/2 - 0.018, max_height - 0.108));
        pocket_list.add(new Pocket(max_width - 0.134, max_height - 0.134));
        lb = new LeftBoard();
        rb = new RightBoard();
        tb = new TopBoard();
        bb = new BottomBoard();
    }
   
    @Override
    boolean interactionCheck(CueBall b) {
        return false;
    }

    @Override
    void interact(CueBall b) {}
    
}
