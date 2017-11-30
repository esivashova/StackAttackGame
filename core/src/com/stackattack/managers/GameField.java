/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.managers;
import com.stackattack.objects.Box;
import java.util.ArrayList;
import java.awt.Point;


/**
 *
 * @author User
 */
public class GameField {
    
    public GameField(int _width, int _height) {
        
    }
    
    //---------------------------------------------
    
    private int width;
    
    public int getWidth() {
        
        return width;
    }
    
    private int height;
    
    public int getHeight() {
        return height;
    }
    
    //----------------------------------------------
    
    private ArrayList<ArrayList<Box>> boxes;
    
    public boolean addBox(Box box, Point pos) {
        
        return false;
    }
    
    public void removeBox(Box box, Point pos) {
        
    }
    
    public Box findBox(Point pos) {
        
        return new Box(0, "");
    }
    
    public ArrayList<Box> findNeighbour(Box box, DIRECTION dir) {
        
        ArrayList<Box> _neighbores = new ArrayList<Box>();
        
        return _neighbores;
    }
    
    //-------------------------------------------------------
    
    private void checkIfRowIsFilled() {
        
    }
}
