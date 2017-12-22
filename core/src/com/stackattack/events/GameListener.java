package com.stackattack.events;

import java.util.EventListener;

/**
 * Слушатель изменения состояния игры
 */
public interface GameListener extends EventListener {
    
    public void gameStarted(GameEvent e);
//    public void moveIsDone(GameEvent e);
//    public boolean askPanelToGetAttributes(GameEvent e);
}
