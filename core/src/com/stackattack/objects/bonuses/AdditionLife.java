
package com.stackattack.objects.bonuses;
import com.stackattack.events.MoveEvent;
import com.stackattack.events.MoveListener;
import com.stackattack.screens.GameField;

/**
 *
 * Бонус - дополнительная жизнь
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
    
    //---------------------------------------------
    
    private void fireAddLife() {
        
        MoveEvent event = new MoveEvent(this);
        for (Object listener : _listMoveList)
        {
            ((MoveListener)listener).addLife(event);
        }
    }
}
