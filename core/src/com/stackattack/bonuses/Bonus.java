/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.bonuses;
import com.badlogic.gdx.graphics.Texture;
import com.stackattack.screens.GameField;
import com.stackattack.objects.Box;
import java.awt.Point;

/**
 *
 * @author User
 */
public abstract class Bonus {
    
    public Bonus(GameField f) {
        field = f;
    }
    
    protected Box box;
    
    public Box getBox() {
        return box;
    }
    
     public void setBox(Box b) {
        box = b;
    }
    
    protected GameField field;
    
    protected TYPE_BONUS type;
    
    public TYPE_BONUS getType() {
        return type;
    }
    
    protected Texture tx;
    
    public void setTexture(Texture _tx) {
        tx = _tx;
    }
    
    public abstract void activate();
    
    private Point position;
    
    public Point getPosition() {
        
        return position;
    }
    
    public void setPosition(Point pos) {
        
        if(checkPosition(pos))
            position = pos;
    }
    
    private boolean checkPosition(Point pos) {
        
        return (pos.x >= 0 && pos.x < field.getWidth()
                && pos.y >= 0 && pos.y < field.getHeight());
    }
}