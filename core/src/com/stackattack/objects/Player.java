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
import com.stackattack.managers.DIRECTION;
import com.stackattack.managers.GameField;
import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author User
 */
public class Player {
    
    public Player(GameField f, int _liftedWeight, int _heightToJump, StackAttackGame _game) {
        
        field = f;
        
        if(_liftedWeight > 0)
            liftedWeight = _liftedWeight;
        else
            liftedWeight = 1;
        
        if(_heightToJump > 0) 
            heightToJump = _heightToJump;
        else
            heightToJump = 1;
        
        game = _game;
       // subjtx = new Texture(Gdx.files.internal("graphic/player.png"));
      //  subjtx = new Texture(Gdx.files.internal("C:\\Users\\User\\Documents\\GDX_projects\\core\\assets\\graphic\\player.png"));
        //subject = new Sprite(subjtx);
    }
    
    //--------------------------------------
    
    private Texture subjtx;
    private StackAttackGame game; 
    private Sprite subject; 
    
    public void paint(SpriteBatch batch) {
        
        
        subjtx = new Texture(Gdx.files.internal("graphic/playerR.png"));
      //  subjtx = new Texture(Gdx.files.internal("C:\\Users\\User\\Documents\\GDX_projects\\core\\assets\\graphic\\player.png"));
        subject = new Sprite(subjtx);
        batch.draw(subjtx, position.x*64, position.y*128, 64, 128);
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
                    
                    position.y++;
                    break;

                case DOWN:
                    
                    position.y--;
                    break;

                case LEFT_UP:
                    
                    position.y++;
                    
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            box.move(dir);
                  
                    position.x--;
                    break;

                case RIGHT_UP: 
                    
                    position.y++;
                    
                    if(boxes.size() <= liftedWeight && boxes.size() > 0)
                        for(Box box : boxes) 
                            box.move(dir);
                  
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
                return (position.x - 1 >= 0 && field.findNeighbour(this, dir).size() <= liftedWeight);
                
            case RIGHT:
                return (position.x + 1 <= field.getWidth()-1 && field.findNeighbour(this, dir).size() <= liftedWeight);
                
            case UP: 
                return (position.y + 1 + heightToJump <= field.getHeight()-1 && field.findNeighbour(this, dir).size() <= 0 /*<= liftedWeight*/);
                
            case DOWN:
                return (position.y - 1 >= 0 && field.findNeighbour(this, dir).size() <= 0);
               
            case LEFT_UP:
                return (position.y + 1 + heightToJump <= field.getHeight()-1 && field.findNeighbour(this, dir).size() <= 0
                        && position.x - 1 >= 0 && field.findNeighbour(this, dir).size() <= liftedWeight);
                
            case RIGHT_UP: 
                return (position.y + 1 + heightToJump <= field.getHeight()-1 && field.findNeighbour(this, dir).size() <= 0
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
}
