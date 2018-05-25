
package com.stackattack.objects.bonuses;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.screens.GameField;

/**
 *
 * Бонус - дополнительные очки
 */
public class AdditionPoints extends Bonus{
    
    public AdditionPoints(GameField f) {
        super(f);
        
        type = TYPE_BONUS.ADD_POINTS;
    }
    
    @Override
    public void activate() {
        
        fireAddPoints();
    }
    
    //---------------------------------------------------
    
    private void fireAddPoints() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listenersGameList)
        {
            ((GameListener)listener).addPoints(event, 5);
        }
    }
}