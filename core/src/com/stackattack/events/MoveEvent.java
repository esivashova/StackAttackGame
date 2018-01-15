package com.stackattack.events;

import java.util.EventObject;

/*
 * Событие, связанное с совершением хода игры
 */
public class MoveEvent extends EventObject {
    
    public MoveEvent(Object source) { 
        super(source); 
    } 
} 