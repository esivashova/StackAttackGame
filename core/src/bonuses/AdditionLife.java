/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonuses;
import com.stackattack.objects.Box;
import com.stackattack.events.MoveEvent;
import com.stackattack.events.MoveListener;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AdditionLife extends Bonus{
    
    public AdditionLife(Box b) {
        super(b);
        
        
    }
    
    public void increaseLife() {
        
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
