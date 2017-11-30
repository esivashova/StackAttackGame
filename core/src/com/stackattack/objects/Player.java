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
public class Player {
    
    public Player(int _liftedWeight, int _heightToJump) {
        
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
        
        position = pos;
    }
    
    //---------------------------------------------------
    
    public boolean makeMove(DIRECTION dir) {
        
        return false;
    }
    
    private void moveBox(Box box, DIRECTION dir) {
        
    }
    
    //---------------------------------------------------
    
    private void die() {
        
    }
}
