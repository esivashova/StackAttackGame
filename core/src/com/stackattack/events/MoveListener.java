package com.stackattack.events;

import java.util.EventListener;
import com.stackattack.managers.DIRECTION;

/**
 * Слушатель изменения состояния игры
 */
public interface MoveListener extends EventListener {
    
    public void gameStarted(MoveEvent e);
    public void moveIsDone(MoveEvent e, DIRECTION dir);
}