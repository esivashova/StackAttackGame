/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.objects;

import com.stackattack.navigation.DIRECTION;

/**
 *
 * @author User
 */
public interface MovableObject {
    
    public abstract boolean move(DIRECTION dir);
    
    public abstract boolean canBeMoved(DIRECTION dir);
}
