package com.stackattack.events;

import java.util.EventListener;


/**
 * Слушатель изменения состояния игры
 */
public interface GameListener extends EventListener {
    
    public void gameFinished(GameEvent e);
    public void activateDoubleJump(GameEvent e);
    public void addPoints(GameEvent e, int points);
}
