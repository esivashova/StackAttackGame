/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects;
import com.stackattack.managers.DIRECTION;
import java.awt.Point;

/**
 *
 * @author User
 */
public class Box {
    
    public Box(int _weight, String _color) {
        
    }
    
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
        
    }
    
    //-----------------------------------------------
    
    public boolean move(DIRECTION dir) {
        
        return false;
    }
    
    public boolean canBeMoved(DIRECTION dir) {
        
        return false;
    }
}
