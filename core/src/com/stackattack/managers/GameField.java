/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.managers;
import com.stackattack.objects.Box;
import com.stackattack.objects.Player;
import java.util.ArrayList;
import java.awt.Point;


/**
 *
 * @author User
 */
public class GameField {
    
    public GameField(GameModel _game, int _width, int _height) {
        
        game = _game;
        
        if(_width < 16)
            width = 16;
        else
            width = _width;
        
        if(_height < 10)
            height = 10;
        else
            height = _height;
        
        for(int i = 0; i < height; i++) {
            boxes.add(i, new ArrayList<Box>());
        }
        
    }
    
    //---------------------------------------------
    
    private GameModel game;
    
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
    
    public ArrayList<ArrayList<Box>> getBoxes() {
        
        return boxes;
    }
    
    public boolean addBox(Box box, Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(findBox(pos) == null) {
                boxes.get(pos.y).add(pos.x, box);
                return true;
            }
        }
        return false;
    }
    
    public void removeBox(Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(findBox(pos) != null) 
                boxes.get(pos.y).remove(pos.x);  
        }
    }
    
    public Box findBox(Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height)
            
            return boxes.get(pos.y).get(pos.x);
        
        else
            return null;
    }
    
    public ArrayList<Box> findNeighbour(Box box, DIRECTION dir) {
        
        ArrayList<Box> _neighboures = new ArrayList<Box>();
        
        Box temp;
        
        switch (dir) {
            
            case LEFT:

                for(int i = box.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(box.getPosition().y).get(i) != null)
                        _neighboures.add(findBox(new Point(i, box.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, box.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT:

                for(int i = box.getPosition().x + 1; i < width; i++)
                {
                    if(boxes.get(box.getPosition().y).get(i) != null)
                        _neighboures.add(findBox(new Point(i, box.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, box.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case UP: 

                for(int i = box.getPosition().y + 1; i < height; i++)
                {
                    if(boxes.get(i).get(box.getPosition().x) != null)
                        _neighboures.add(findBox(new Point(box.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case DOWN:

                for(int i = box.getPosition().y - 1; i >= 0; i--)
                {
                    if(boxes.get(i).get(box.getPosition().x) != null)
                        _neighboures.add(findBox(new Point(box.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case LEFT_UP:

                for(int i = box.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(box.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBox(new Point(i, box.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, box.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT_UP: 

                for(int i = box.getPosition().x + 1; i < width; i++)
                {
                    if(boxes.get(box.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBox(new Point(i, box.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, box.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;
        }
        
        return _neighboures;
    }
    
    public ArrayList<Box> findNeighbour(Player player, DIRECTION dir) {
        
        ArrayList<Box> _neighboures = new ArrayList<Box>();
        
        Box temp;
        
        switch (dir) {
            
            case LEFT:

                for(int i = player.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(player.getPosition().y).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, player.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT:

                for(int i = player.getPosition().x + 1; i < width; i++)
                {
                    if(boxes.get(player.getPosition().y).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, player.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case UP: 

                for(int i = player.getPosition().y + 1 + player.getHeightToJump(); i < height; i++)
                {
                    if(boxes.get(i).get(player.getPosition().x) != null)
                        _neighboures.add(findBox(new Point(player.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case DOWN:

                for(int i = player.getPosition().y - 1; i >= 0; i--)
                {
                    if(boxes.get(i).get(player.getPosition().x) != null)
                        _neighboures.add(findBox(new Point(player.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case LEFT_UP:

                for(int i = player.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(player.getPosition().y + 1 + player.getHeightToJump()).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y + 1 + player.getHeightToJump())));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, player.getPosition().y + 1 + player.getHeightToJump()));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT_UP: 

                for(int i = player.getPosition().x + 1; i < width; i++)
                {
                    if(boxes.get(player.getPosition().y + 1 + player.getHeightToJump()).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y + 1 + player.getHeightToJump())));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, player.getPosition().y + 1 + player.getHeightToJump()));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;
        }
        
        return _neighboures;
    }
    
    //-------------------------------------------------------
    
    private void checkIfRowIsFilled() {
        
        if(boxes.get(0).size() == width) {
            
            ArrayList<ArrayList<Box>> boxesCopy = new ArrayList<ArrayList<Box>>(boxes);
            
            for(int i = 0; i < height - 1; i++)
            {
                boxes.set(i, boxesCopy.get(i + 1));
            }
            
            boxes.set(height - 1, new ArrayList<Box>());
            
            for(ArrayList<Box> _boxes : boxes) {
                for(Box b : _boxes) {
                    b.move(DIRECTION.DOWN);
                }
            }
            
            game.getPlayer().makeMove(DIRECTION.DOWN);
        }
    }
    
    private void lowerFlyingObjects() {
        
        for(ArrayList<Box> _boxes : boxes) {
            for(Box b : _boxes) {
                
                if(b.getPosition().y != 0
                        && findNeighbour(b, DIRECTION.DOWN).size() == 0)
                    b.move(DIRECTION.DOWN);
            }
        }
        
        if(game.getPlayer().getPosition().y != 0
                && findNeighbour(game.getPlayer(), DIRECTION.DOWN).size() == 0)
            game.getPlayer().makeMove(DIRECTION.DOWN);
    }
}
