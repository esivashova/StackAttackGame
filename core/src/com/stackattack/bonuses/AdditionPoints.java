/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.bonuses;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AdditionPoints extends Bonus{
    
    public AdditionPoints(GameField f) {
        super(f);
        
        type = TYPE_BONUS.ADD_POINTS;
    }
    
    @Override
    public void activate() {
        
        fireAddPoints();
    }
    // ------------------------ События и слушатели -------------------------
  
   
    
    
    /**
     * 
     * 
     * @param  
     */
    private void fireAddPoints() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listGameList)
        {
            ((GameListener)listener).addPoints(event, 5);
        }
    }
}