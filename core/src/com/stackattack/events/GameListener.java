package com.stackattack.events;

import java.util.EventListener;
import com.stackattack.objects.bonuses.Bonus;


/**
 * Слушатель изменения состояния игры
 */
public interface GameListener extends EventListener {
    
    public void gameFinished(GameEvent e);
    public void activateDoubleJump(GameEvent e);
    public void addPoints(GameEvent e, int points);
    public void removeBottomRow(GameEvent e);
    public void removeColor(GameEvent e, String color);
    public void bonusBind(GameEvent e, Bonus bonus);
}
