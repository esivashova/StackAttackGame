package com.stackattack.events;

import java.util.EventListener;


/**
 * Слушатель изменения состояния игры
 */
public interface GameListener extends EventListener {
    
    public void gameFinished(GameEvent e);
}
