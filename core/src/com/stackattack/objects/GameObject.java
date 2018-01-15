/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.navigation.DIRECTION;
import com.stackattack.screens.GameField;
import java.awt.Point;

/**
 *
 * @author User
 */
public abstract class GameObject {
    
    public GameObject(GameField f) {
        field = f;
    }
    
    protected GameField field;
    
    protected Texture subjtx;
    
    public void setTexture(Texture tx) {
        subjtx = tx;
    }
    
    protected Sprite subject; 
    
    public abstract void paint(Texture tx, SpriteBatch batch);
    
    
    //-----------------------------------------------
    
    protected Point position;
    
    public Point getPosition() {
        
        return position;
    }
    
    public void setPosition(Point pos) {
        
        if(checkPosition(pos))
            position = pos;
    }
    
    protected abstract boolean checkPosition(Point pos);
    
    //-----------------------------------------------
    
    
}
