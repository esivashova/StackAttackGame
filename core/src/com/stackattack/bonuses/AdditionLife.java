/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.bonuses;
import com.stackattack.events.MoveEvent;
import com.stackattack.events.MoveListener;
import com.stackattack.screens.GameField;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AdditionLife extends Bonus{
    
    public AdditionLife(GameField f) {
        super(f);
        
        type = TYPE_BONUS.ADD_LIFE;
    }
    
    @Override
    public void activate() {
        
        fireAddLife();
        //field.getGame().getPlayer().addLife();
    }
    
    
    
    /**
     * 
     * 
     * @param  
     */
    private void fireAddLife() {
        
        MoveEvent event = new MoveEvent(this);
        for (Object listener : _listMoveList)
        {
            ((MoveListener)listener).addLife(event);
        }
    }
}
