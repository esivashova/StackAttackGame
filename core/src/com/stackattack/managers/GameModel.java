/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.managers;
import com.stackattack.objects.Player;
import com.stackattack.objects.Box;
import java.util.TreeMap;

/**
 *
 * @author User
 */
public class GameModel {
    
    public GameModel() {
        
    }
    
    //-------------------------------------
    
    private Player player;
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player p) {
        
    }
    //--------------------------------------
    
    private GameField field;
    
    public GameField getField() {
        return field;
    }
    
    public void setGameField(GameField p) {
        
    }
    
    //----------------------------------------
    
    public void start(TreeMap<String, Integer> attributes) {
        
    }
    
    //----------------------------------------
    
    private void generateGame() {
        
    }
    
    private Box generateRandomBox() {
        
        return new Box(0, "");
    }
    
    private void placeBox(Box box) {
        
    }
    
    private void finish() {
        
    }
}
