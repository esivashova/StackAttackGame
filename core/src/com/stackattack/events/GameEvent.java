package com.stackattack.events;

import java.util.EventObject;

/*
 * Событие, связанное с изменением состояния игры
 */
public class GameEvent extends EventObject {
    
    public GameEvent(Object source) { 
        super(source); 
    } 
} 