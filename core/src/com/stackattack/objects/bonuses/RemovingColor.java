
package com.stackattack.objects.bonuses;

import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;

/**
 *
 * Бонус - уничтожаются ящики определенного цвета 
 * (зависит от цвета ящика, в котором был бонус)
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
    
    //---------------------------------------------------------
    
    private void fireRemoveBottomRow(String color) {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listGameList)
        {
            ((GameListener)listener).removeColor(event, color);
        }
    }
}
