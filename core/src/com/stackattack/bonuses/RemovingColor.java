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
public class RemovingColor extends Bonus{
    
    public RemovingColor(GameField f) {
        super(f);
        
        type = TYPE_BONUS.REMOVE_COLOR;
    }
    
    @Override
    public void activate() {
        
        fireRemoveBottomRow(box.getColor());
    }
    
    // ------------------------ События и слушатели -------------------------
  
   
    
    
    /**
     * 
     * 
     * @param  
     */
    private void fireRemoveBottomRow(String color) {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listGameList)
        {
            ((GameListener)listener).removeColor(event, color);
        }
    }
}
