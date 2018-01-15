
package com.stackattack.objects;

import com.stackattack.navigation.DIRECTION;

/**
 * Интерфейс объекта, который можно перемещать 
 * (не инициирует самостоятельно это действие)
 */
public interface MovableObject {
    
    public abstract boolean move(DIRECTION dir);
    
    public abstract boolean canBeMoved(DIRECTION dir);
}
