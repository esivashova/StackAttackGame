/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.bonuses;
import com.badlogic.gdx.graphics.Texture;
import com.stackattack.objects.Box;

/**
 *
 * @author User
 */
public abstract class Bonus {
    
    public Bonus(Box b) {
        box = b;
    }
    
    protected Box box;
    
    protected TYPE_BONUS type;
    
    public TYPE_BONUS getType() {
        return type;
    }
    
    protected Texture tx;
    
    public void setTexture(Texture _tx) {
        tx = _tx;
    }
    
    public void activate() {
    }
}
