
package com.stackattack.objects;
import com.stackattack.objects.bonuses.Bonus;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.objects.bonuses.TYPE_BONUS;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.events.MoveEvent;
import com.stackattack.events.MoveListener;
import com.stackattack.navigation.DIRECTION;
import com.stackattack.screens.GameField;
import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * Класс игрока
 */
public class Player extends GameObject {
    
    public Player(GameField f, int _liftedWeight, int _heightToJump) {
        
        super(f);
        
        field = f;
        
        if(_liftedWeight > 0)
            liftedWeight = _liftedWeight;
        else
            liftedWeight = 1;
        
        if(_heightToJump > 0) 
            heightToJump = _heightToJump;
        else
            heightToJump = 1;
       
        field.addGameListener(new MoveEventObserver());
    }
    
    //--------------------------------------
    
    private int amountOfLives = 1;
    
    public int getAmountOfLives() {
        return amountOfLives;
    }
    
    public void addAmountOfLives(int delta) {
        amountOfLives += delta;
    }
    
    //--------------------------------------
    
    @Override
    public void setTexture(Texture playerTx) {
        subjtx = playerTx;
        subject = new Sprite(subjtx);
    }
            
    private boolean direction = true;
    

    @Override
    public void paint(Texture tx, SpriteBatch batch) {
        
    }
            
    public void paint(SpriteBatch batch) {

        subjtx = field.getPlayerTexture(direction);
        batch.draw(subjtx, position.x*64, position.y*64, 64, 128);
    }
    
    //---------------------------------------------------
    
    private int liftedWeight;
    
    public int getLiftedWeight() {
        
        return liftedWeight;
    }
    
    private int heightToJump;
            
    public int getHeightToJump() {
        
        return heightToJump;
    }
    
    public void setHeightToJump(int h) {
        
        heightToJump = h;
    }
    
    private final static int HEIGHT = 2;
    
    //--------------------------------------------------
    
    @Override
    protected boolean checkPosition(Point pos) {
        
        return (pos.x >= 0 && pos.x < field.getWidth()
                && pos.y >= 0 && pos.y < field.getHeight()-1);
    }
    
    //-----------------------------------------------
    
    private GameField field;
    
    //---------------------------------------------------
    
    public boolean makeMove(DIRECTION dir) {
        
        boolean flag = true;
        
        if(canMove(dir)) {
            
            ArrayList<Box> boxes = field.findNeighbour(this, dir, false);
            
            switch (dir) {
                case LEFT:

                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) { 
                            flag = box.move(dir);
                        }
                    
                    if(flag) {
                        position.x--;
                        direction = false;
                    }
                    break;
                        
                case RIGHT:
 
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                       for(Box box : boxes) { 
                            flag = box.move(dir);
                        }
                  
                    if(flag) {
                        position.x++;
                        direction = true;
                    }
                    break;

                case UP: 
 
                    position.y += heightToJump;
                    break;

                case DOWN:
                    
                    position.y--;
                    break;

                case LEFT_UP:
    
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            flag = box.move(DIRECTION.LEFT);
                  
                    if(flag) {
                        position.y += heightToJump;
                        position.x--;
                        direction = false;
                    }
                    break;

                case RIGHT_UP: 
                    
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            flag = box.move(DIRECTION.RIGHT);
                  
                    if(flag) {
                        position.y += heightToJump;
                        position.x++;
                        direction = true;
                    }
                    break;
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean canMove(DIRECTION dir) {
        
        switch (dir) {
            case LEFT:

                if((field.findNeighbour(this, dir, false).size() > 0 
                        || field.findNeighbour(this, dir, true).size() > 0) 
                        && position.x - 1 <= 0)
                    return false;
                
                return (position.x - 1 >= 0 && field.findNeighbour(this, dir, false).size() <= liftedWeight
                        && field.findNeighbour(this, dir, true).size() < 1);
                
            case RIGHT:
                
                if((field.findNeighbour(this, dir, false).size() > 0
                        || field.findNeighbour(this, dir, true).size() > 0)
                        && position.x + 1 >= field.getWidth()-1)
                    return false;
                
                return (position.x + 1 <= field.getWidth()-1 && field.findNeighbour(this, dir, false).size() <= liftedWeight
                        && field.findNeighbour(this, dir, true).size() < 1);
                
            case UP: 

                return (position.y + heightToJump + 1 <= field.getHeight()-1 && field.findNeighbour(this, dir, false).size() <= 0 
                        && (position.y == 0 || field.findNeighbour(this, DIRECTION.DOWN, false).size() > 0/*<= liftedWeight*/));
                
            case DOWN:
                return (position.y - 1 >= 0 && field.findNeighbour(this, dir, false).size() <= 0);
               
            case LEFT_UP:
                
                if((field.findNeighbour(this, dir, false).size() > 0
                        || field.findNeighbour(this, dir, true).size() > 0)
                        && position.x - 1 <= 0)
                    return false;
                
                return (position.y + 1 + heightToJump <= field.getHeight()-1 /*&& field.findNeighbour(this, dir).size() <= 0*/
                        && position.x - 1 >= 0 && field.findNeighbour(this, dir, false).size() <= liftedWeight
                        && (position.y == 0 || field.findNeighbour(this, DIRECTION.DOWN, false).size() > 0)
                        && field.findNeighbour(this, dir, true).size() < 1);
                
            case RIGHT_UP: 
                
                if((field.findNeighbour(this, dir, false).size() > 0
                        || field.findNeighbour(this, dir, true).size() > 0)
                        && position.x + 1 >= field.getWidth()-1)
                    return false;
                
                return (position.y + 1 + heightToJump <= field.getHeight()-1 /*&& field.findNeighbour(this, dir).size() <= 0*/
                        && position.x + 1 <= field.getWidth()-1 && field.findNeighbour(this, dir, false).size() <= liftedWeight
                        && (position.y == 0 || field.findNeighbour(this, DIRECTION.DOWN, false).size() > 0)
                        && field.findNeighbour(this, dir, true).size() < 1);
        }
        
        return false;
    }
    
    //-------------------------------------- 
    
    private void destroyBox(DIRECTION dir) {
        
        if(canDestroy(dir)) {
            
            Box box = field.findNeighbour(this, dir, false).get(0);
            
            if(box.getBonus() != null) {
                field.addBonus(box.getBonus(), box.getBonus().getPosition());
            }
            
            box.setBonus(null);
            
            if(box.canBeBroken()) {
                
                field.removeBox(box.getPosition());
            }
        }
        
        switch (dir) {
            case LEFT:
                
                direction = false;
                break;
                
            case RIGHT:
                
                direction = true;
                break;
               
            case LEFT_UP:
                
                direction = false;
                break;
                
            case RIGHT_UP: 
                
                direction = true;
        }
    }
 
    
    public boolean canDestroy(DIRECTION dir) {

        return (field.findNeighbour(this, dir, false).size() > 0);
    }
    
    //---------------------------------------------------
    
    public void checkSituation() {
        
        if(field.findNeighbour(this, DIRECTION.UP, false).size() >= 1) {
            
            if(!field.findNeighbour(this, DIRECTION.UP, false).get(0).canBeBroken()
                    || !Gdx.input.isKeyPressed(Input.Keys.SPACE))
                die(field.findNeighbour(this, DIRECTION.UP, false));
        }
        
        if(field.getBonuses().get(position.y).get(position.x) != null) {
 
           MoveEventObserver temp = new MoveEventObserver();
                    
            if(field.getBonuses().get(position.y).get(position.x).getType() == TYPE_BONUS.ADD_LIFE) {
               field.getBonuses().get(position.y).get(position.x).addGameListener(temp);
            
            }
            else {
                fireBonusBind(field.getBonuses().get(position.y).get(position.x));
            }

            field.getBonuses().get(position.y).get(position.x).activate();
            field.removeBonus(new Point(position.x, position.y));
        }
        
        if(field.getBonuses().get(position.y + 1).get(position.x) != null) {
            MoveEventObserver temp = new MoveEventObserver();
                    
            if(field.getBonuses().get(position.y + 1).get(position.x).getType() == TYPE_BONUS.ADD_LIFE) {
               field.getBonuses().get(position.y + 1).get(position.x).addGameListener(temp);
            
            }
            else {
                fireBonusBind(field.getBonuses().get(position.y + 1).get(position.x));
            }

            field.getBonuses().get(position.y + 1).get(position.x).activate();
            field.removeBonus(new Point(position.x, position.y + 1));
        }
    }
    
    //--------------------------------------
    
    private void die(ArrayList<Box> boxes) {
        
        amountOfLives--;
        
        if(amountOfLives > 0) {
            
            for(Box b : boxes) {
                field.removeBox(b.getPosition());
            }
        }
          
        else {
            
            // send event to GameModel to stop the game
            fireGameFinished();
        }
    }
    
    //------------------------------------------------
    
    /**
    * Класс представляет слушателя события, возникающего при при совершении хода.
    */
    public class MoveEventObserver implements MoveListener {
        
        @Override
        public void moveIsDone(MoveEvent e, DIRECTION dir) {

            makeMove(dir);
        }
        
         @Override
        public void boxIsSelected(MoveEvent e, DIRECTION dir) {

            destroyBox(dir);
        }
        
        @Override
        public void addLife(MoveEvent e) {
            addAmountOfLives(1);
        }
    }
    
    public void addLife() {
            addAmountOfLives(1);
        }
    
    // ------------------------ События и слушатели -------------------------
  
    // Список слушателей
    private ArrayList _listenersList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(GameListener l) { 
        _listenersList.add(l); 
    }
    
    /**
     * Отсоединяет слушателя
     * 
     * @param l слушатель
     */
    public void removeGameListener(GameListener l) { 
        _listenersList.remove(l); 
    } 
    
    
    private void fireGameFinished() {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listenersList)
        {
            ((GameListener)listener).gameFinished(event);
        }
    }
    
    private void fireBonusBind(Bonus b) {
        
        GameEvent event = new GameEvent(this);
        for (Object listener : _listenersList)
        {
            ((GameListener)listener).bonusBind(event, b);
        }
    }
}
