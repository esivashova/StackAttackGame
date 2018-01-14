/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.StackAttackGame;
import com.stackattack.events.MoveEvent;
import com.stackattack.events.MoveListener;
import com.stackattack.managers.DIRECTION;
import com.stackattack.managers.GameField;
import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author User
 */
public class Player {
    
    public Player(GameField f, int _liftedWeight, int _heightToJump) {
        
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
    
    private Texture subjtx;
    
    public void setTexture(Texture playerTx) {
        subjtx = playerTx;
        subject = new Sprite(subjtx);
    }
            
    
    private Sprite subject; 
    
    public void paint(SpriteBatch batch) {

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
    
    private final static int HEIGHT = 2;
    
    //--------------------------------------------------
    
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
                && pos.y >= 0 && pos.y < field.getHeight()-1);
    }
    
    //-----------------------------------------------
    
    private GameField field;
    
    //---------------------------------------------------
    
    public boolean makeMove(DIRECTION dir) {
        
        
        if(canMove(dir)) {
            
            ArrayList<Box> boxes = field.findNeighbour(this, dir);
            
            switch (dir) {
                case LEFT:
                     
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            box.move(dir);
                  
                    position.x--;
                    break;
                        
                case RIGHT:
                    
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            box.move(dir);
                  
                    position.x++;
                    break;

                case UP: 
                    
                    position.y += heightToJump;
                    break;

                case DOWN:
                    
                    position.y--;
                    break;

                case LEFT_UP:
                    
                    position.y += heightToJump;
                    
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            box.move(DIRECTION.LEFT);
                  
                    position.x--;
                    break;

                case RIGHT_UP: 
                    
   
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            box.move(DIRECTION.RIGHT);
                  
                    position.y += heightToJump;
                    position.x++;
                    break;
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean canMove(DIRECTION dir) {
        
        switch (dir) {
            case LEFT:
                
                if(field.findNeighbour(this, dir).size() > 0 && position.x - 1 <= 0)
                    return false;
                
                return (position.x - 1 >= 0 && field.findNeighbour(this, dir).size() <= liftedWeight);
                
            case RIGHT:
                
                if(field.findNeighbour(this, dir).size() > 0 && position.x + 1 >= field.getWidth()-1)
                    return false;
                
                return (position.x + 1 <= field.getWidth()-1 && field.findNeighbour(this, dir).size() <= liftedWeight);
                
            case UP: 
                
                return (position.y + heightToJump + 1 <= field.getHeight()-1 && field.findNeighbour(this, dir).size() <= 0 /*<= liftedWeight*/);
                
            case DOWN:
                return (position.y - 1 >= 0 && field.findNeighbour(this, dir).size() <= 0);
               
            case LEFT_UP:
                
                if(field.findNeighbour(this, dir).size() > 0 && position.x - 1 <= 0)
                    return false;
                
                return (position.y + 1 + heightToJump <= field.getHeight()-1 /*&& field.findNeighbour(this, dir).size() <= 0*/
                        && position.x - 1 >= 0 && field.findNeighbour(this, dir).size() <= liftedWeight);
                
            case RIGHT_UP: 
                
                if(field.findNeighbour(this, dir).size() > 0 && position.x + 1 >= field.getWidth()-1)
                    return false;
                
                return (position.y + 1 + heightToJump <= field.getHeight()-1 /*&& field.findNeighbour(this, dir).size() <= 0*/
                        && position.x + 1 <= field.getWidth()-1 && field.findNeighbour(this, dir).size() <= liftedWeight);
        }
        
        return false;
    }
    
    //---------------------------------------------------
    
    public void checkSituation() {
        
        if(field.findNeighbour(this, DIRECTION.UP).size() >= 1)
            die();
    }
    
    private void die() {
        
        // send event to GameModel to stop the game
    }
    
    
    /**
    * Класс представляет слушателя события, возникающего при при совершении хода.
    */
    public class MoveEventObserver implements MoveListener {

        @Override
        public void gameStarted(MoveEvent e) {
            
        }
        
        @Override
        public void moveIsDone(MoveEvent e, DIRECTION dir) {

            makeMove(dir);
        }
    }
}
