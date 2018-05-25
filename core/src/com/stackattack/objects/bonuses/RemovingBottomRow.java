
package com.stackattack.objects.bonuses;

import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;

/**
 *
 * Бонус - нижний ряд ящиков уничтожается
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
    
    //---------------------------------------------------------
    
    private void fireRemoveBottomRow() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listenersGameList)
        {
            ((GameListener)listener).removeBottomRow(event);
        }
    }
}
