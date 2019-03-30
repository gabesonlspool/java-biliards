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
abstract public class GameObject {
    
    protected double x;
    protected double y;
    /* Интерактивные объекты могут взаимодействовать во время итераций движка
     * с другими интерактивными объектами */
    protected boolean interactive;
     
    GameObject(double coord1, double coord2, boolean behaviour) {
        x = coord1;
        y = coord2;
        interactive = behaviour;
    }
    
    abstract void update();
        
}
