/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import Model.GameEngine;

/**
 *
 * @author andrey
 */
public class GameServiceTask implements Runnable {
    
    private GameEngine engine;
    private Client player1;
    private Client player2;
    private volatile DataFrame frame;
    
    
    protected GameServiceTask(Client client1, Client client2) {
        player1 = client1;
        player2 = client2;
        engine = new GameEngine();
    }
    
    
    @Override
    public void run() {
        
        frame = 
    }
    
}
