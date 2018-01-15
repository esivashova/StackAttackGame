/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.bonuses;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.events.GameListener;
import com.stackattack.events.MoveListener;
import com.stackattack.screens.GameField;
import com.stackattack.objects.Box;
import java.awt.Point;
import java.util.ArrayList;

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
    
     public void paint(Texture bonusTx, SpriteBatch batch) {
        
        tx = bonusTx;
//        subject.setPosition(position.x*10, position.y*10);
//        subject.draw(batch);  
        batch.draw(tx, position.x*64, position.y*64, 64, 64);
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
    
    // Список слушателей
    protected ArrayList _listMoveList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(MoveListener l) { 
        _listMoveList.add(l); 
    }
    
    /**
     * Отсоединяет слушателя
     * 
     * @param l слушатель
     */
    public void removeGameListener(MoveListener l) { 
        _listMoveList.remove(l); 
    } 
    
    // Список слушателей
    protected ArrayList _listGameList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(GameListener l) { 
        _listGameList.add(l); 
    }
    
    /**
     * Отсоединяет слушателя
     * 
     * @param l слушатель
     */
    public void removeGameListener(GameListener l) { 
        _listGameList.remove(l); 
    }
}
