/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.bonuses;
import com.stackattack.objects.Box;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DoubleJump extends Bonus{
    
    public DoubleJump(GameField f) {
        super(f);
    }
    
    @Override
    public void activate() {
        
        fireActivateDoubleJump();
    }
    
    // ------------------------ События и слушатели -------------------------
  
    // Список слушателей
    private ArrayList _listenersList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(GameListener l) { 
        _listenersList.add(l); 
    }
    
    /**
     * Отсоединяет слушателя
     * 
     * @param l слушатель
     */
    public void removeGameListener(GameListener l) { 
        _listenersList.remove(l); 
    } 
    
    
    /**
     * 
     * 
     * @param  
     */
    private void fireActivateDoubleJump() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listenersList)
        {
            ((GameListener)listener).activateDoubleJump(event);
        }
    }
}
