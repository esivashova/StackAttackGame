/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stackattack.navigation.DIRECTION;
import com.stackattack.screens.GameField;
import com.stackattack.bonuses.Bonus;
import java.awt.Point;

/**
 *
 * @author User
 */
public class Box {
    
    public Box(GameField _field, int _weight, String _color, boolean canBr, Bonus b) {
        if(_weight > 0)
            weight = _weight;
        else
            weight = 1;
        
        color = _color; 
        field = _field;
        
        //subjtx = boxTx;
        //subject = new Sprite(subjtx);
        
        canBeBroken = canBr;
        bonus = b;
    }
    
    //--------------------------------------
    
    private Texture subjtx;
    
    public void setTexture(Texture boxTx) {
        subjtx = boxTx;
    }
    
    private Sprite subject; 
    
    public void paint(Texture boxTx, SpriteBatch batch) {
        
        subjtx = boxTx;
//        subject.setPosition(position.x*10, position.y*10);
//        subject.draw(batch);  
        batch.draw(subjtx, position.x*64, position.y*64, 64, 64);
    }
    
    //--------------------------------------
    
    private int weight;
    
    public int getWeight() {
        
        return weight;
    }
    
    public boolean hasColor() {
        return (color != null);
    }
    
    public boolean hasPosition() {
        return (position != null);
    }
    
    private String color;
    
    public String getColor() {
        
        return color;
    }
    
    //-----------------------------------------------
    
    private boolean canBeBroken;
    
    public boolean canBeBroken() {
        return canBeBroken;
    }
    
    //-----------------------------------------------
    
    private Bonus bonus;
    
    public Bonus getBonus() {
        return bonus;
    }
    
    //-----------------------------------------------
    
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
    
    //-----------------------------------------------
    
    private GameField field;
    
    //-----------------------------------------------
    
    public boolean move(DIRECTION dir) {
        
        if(canBeMoved(dir)) {
            switch (dir) {
                case LEFT:
                    
                    field.removeBox(position);
                    position.x--;
                    field.addBox(this, position);
                    
                    break;

                case RIGHT:
                    field.removeBox(position);
                    position.x++;
                    field.addBox(this, position);
                    break;

                case UP: //fantstic
                    break;

                case DOWN:
                    field.removeBox(position);
                    position.y--;
                    field.addBox(this, position);
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
    
    public boolean canBeMoved(DIRECTION dir) {
        
        switch (dir) {
            case LEFT:
                return (position.x - 1 >= 0 && field.findNeighbour(this, dir).size() < 1);
                
            case RIGHT:
                return (position.x + 1 <= field.getWidth()-1 && field.findNeighbour(this, dir).size() < 1);
                
            case UP: //fantstic
                break;
                
            case DOWN:
                return (position.y - 1 >= 0 && field.findNeighbour(this, dir).size() < 1);
               
            case LEFT_UP: //fantastic
                break;
                
            case RIGHT_UP: //fantastic
                break;
        }
        
        return false;
    }
}
