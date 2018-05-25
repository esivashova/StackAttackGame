
package com.stackattack.objects.bonuses;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;

/**
 *
 * Бонус - высота прыжка увеличивается вдвое
 */
public class DoubleJump extends Bonus{
    
    public DoubleJump(GameField f) {
        super(f);
        
        type = TYPE_BONUS.DOUBLE_JUMP;
    }
    
    @Override
    public void activate() {
        
        fireActivateDoubleJump();
    }
    
    //---------------------------------------------------------
    
    private void fireActivateDoubleJump() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listenersGameList)
        {
            ((GameListener)listener).activateDoubleJump(event);
        }
    }
}
