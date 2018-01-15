
package com.stackattack.objects;

/**
 *
 * Класс, представляющий собой количество очков, набранных за время игры
 */
public class Score {
    
    private int value = 0;

    public Score() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int sc) {
        this.value = sc;
    }
}
