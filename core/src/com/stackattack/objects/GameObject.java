
package com.stackattack.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.screens.GameField;
import java.awt.Point;

/**
 *
 * Класс игрового объекта
 */
public abstract class GameObject {
    
    public GameObject(GameField f) {
        field = f;
    }
    
    protected GameField field;
    
    protected Texture subjtx;
    
    public void setTexture(Texture tx) {
        subjtx = tx;
    }
    
    protected Sprite subject; 
    
    public abstract void paint(Texture tx, SpriteBatch batch);
    
    
    //-----------------------------------------------
    
    protected Point position;
    
    public Point getPosition() {
        
        return position;
    }
    
    public void setPosition(Point pos) {
        
        if(checkPosition(pos))
            position = pos;
    }
    
    protected abstract boolean checkPosition(Point pos);
    
    //-----------------------------------------------
    
    
}
