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
    }
    
    // ------------------------ События и слушатели -------------------------
  
    // Список слушателей
    private ArrayList _listenersList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(MoveListener l) { 
        _listenersList.add(l); 
    }
    
    /**
     * Отсоединяет слушателя
     * 
     * @param l слушатель
     */
    public void removeGameListener(MoveListener l) { 
        _listenersList.remove(l); 
    } 
    
    
    /**
     * 
     * 
     * @param  
     */
    private void fireAddLife() {
        
        MoveEvent event = new MoveEvent(this);
        for (Object listener : _listenersList)
        {
            ((MoveListener)listener).addLife(event);
        }
    }
}
