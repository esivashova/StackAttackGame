/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.stackattack.managers.DIRECTION;
import com.stackattack.managers.GameField;
import java.awt.Point;

/**
 *
 * @author User
 */
public class Box {
    
    public Box(GameField _field, int _weight, String _color) {
        if(_weight > 0)
            weight = _weight;
        else
            weight = 1;
        
        color = _color; 
        field = _field;
        
        subjtx = new Texture("color");
        subject = new Sprite(subjtx);
    }
    
    //--------------------------------------
    
    private Texture subjtx;
    
    private Sprite subject; 
    
    //--------------------------------------
    
    private int weight;
    
    public int getWeight() {
        
        return weight;
    }
    
    private String color;
    
    public String getColor() {
        
        return color;
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
        
        return (pos.x > 0 && pos.x <= field.getWidth()-1
                && pos.y > 0 && pos.y <= field.getHeight()-1);
    }
    
    //-----------------------------------------------
    
    private GameField field;
    
    //-----------------------------------------------
    
    public boolean move(DIRECTION dir) {
        
        if(canBeMoved(dir)) {
            switch (dir) {
                case LEFT:
                    position.x--;
                    break;

                case RIGHT:
                    position.x++;
                    break;

                case UP: //fantstic
                    break;

                case DOWN:
                    position.y--;
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
