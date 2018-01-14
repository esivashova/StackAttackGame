package com.stackattack.events;

import java.util.EventListener;
import com.stackattack.managers.DIRECTION;

/**
 * Слушатель изменения состояния игры
 */
public interface GameListener extends EventListener {
    
    public void gameStarted(GameEvent e);
    public void moveIsDone(GameEvent e, DIRECTION dir);
//    public boolean askPanelToGetAttributes(GameEvent e);
}
