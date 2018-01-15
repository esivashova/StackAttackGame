/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects.bonuses;

import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class RemovingBottomRow extends Bonus{
    
    public RemovingBottomRow(GameField f) {
        super(f);
        
        type = TYPE_BONUS.REMOVE_BOTTOM_ROW;
    }
    
    @Override
    public void activate() {
        
        fireRemoveBottomRow();
    }
    
    // ------------------------ События и слушатели -------------------------
  

    
    
    /**
     * 
     * 
     * @param  
     */
    private void fireRemoveBottomRow() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listGameList)
        {
            ((GameListener)listener).removeBottomRow(event);
        }
    }
}
