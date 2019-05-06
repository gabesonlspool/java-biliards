/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author andrey
 */
public class EngineOutputDataFrame implements Serializable {
    
    public double[] masterballcoords;
    public ArrayList<double[]> ballcoords;
    
    public EngineOutputDataFrame() {
        masterballcoords = null;
        ballcoords = null;
    }
        
    public void setData(double[] new_mcoords, ArrayList<double[]> new_bcords) {
        masterballcoords = new_mcoords;
        ballcoords = new_bcords;
    }
    
}
