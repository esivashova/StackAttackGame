
package com.stackattack.objects.bonuses;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.objects.GameObject;
import com.stackattack.objects.MovableObject;
import com.stackattack.events.GameListener;
import com.stackattack.events.MoveListener;
import com.stackattack.navigation.DIRECTION;
import com.stackattack.screens.GameField;
import com.stackattack.objects.Box;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * Класс, представляющий собой бонус
 */
public abstract class Bonus extends GameObject implements MovableObject {
    
    public Bonus(GameField f) {
        
        super(f);
    }
    
    //---------------------------------------------------------
    
    protected Box box;
    
    public Box getBox() {
        return box;
    }
    
     public void setBox(Box b) {
        box = b;
    }
    
    //---------------------------------------------------------
    
    protected TYPE_BONUS type;
    
    public TYPE_BONUS getType() {
        return type;
    }
    
    //---------------------------------------------------------
    
    @Override
     public void paint(Texture bonusTx, SpriteBatch batch) {
        
        subjtx = bonusTx; 
        batch.draw(subjtx, position.x*64, position.y*64, 64, 64);
    }
    
    //---------------------------------------------------------
     
    public abstract void activate();
    
    //---------------------------------------------------------
    
    @Override
    protected boolean checkPosition(Point pos) {
        
        return (pos.x >= 0 && pos.x < field.getWidth()
                && pos.y >= 0 && pos.y < field.getHeight());
    }
    
    //---------------------------------------------------------
    
    @Override
    public boolean move(DIRECTION dir) {
        
        if(canBeMoved(dir)) {
            switch (dir) {
                case LEFT:
                    break;

                case RIGHT:
                    break;

                case UP: //fantstic
                    break;

                case DOWN:
                    
                    field.removeBonus(position);
                    position.y--;
                    
                    field.addBonus(this, position);
                    
                    break;

                case LEFT_UP: //fantastic
                    break;

                case RIGHT_UP: //fantastic
                    break;
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean canBeMoved(DIRECTION dir) {
        
        if(dir == DIRECTION.DOWN) {
            return (position.y - 1 >= 0 && field.findNeighbour(this, dir).size() < 1
                    && field.findNeighbourBonuses(this, dir).size() < 1);
        }
        else
            return false;
    }
    
    //---------------------------------------------------------
    
    // Список слушателей
    protected ArrayList _listenersMoveList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(MoveListener l) { 
        _listenersMoveList.add(l); 
    }
      
    // Список слушателей
    protected ArrayList _listenersGameList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(GameListener l) { 
        _listenersGameList.add(l); 
    }
}
