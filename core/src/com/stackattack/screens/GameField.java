
package com.stackattack.screens;
import com.stackattack.navigation.DIRECTION;
import com.stackattack.modules.ModuleEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.StackAttackGame;
import com.stackattack.objects.Box;
import com.stackattack.objects.bonuses.Bonus;
import com.stackattack.objects.Player;
import com.stackattack.events.MoveEvent;
import com.stackattack.events.MoveListener;
import java.util.ArrayList;
import java.awt.Point;


/**
 *
 * Класс поля
 */
public class GameField implements Screen {
    
    public GameField(StackAttackGame _game, int _width, int _height) {
        
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
            
            for(int j = 0; j < width; j++) {
                boxes.get(i).add(j, null);
            }
        }
        
        for(int i = 0; i < height; i++) {
            bonuses.add(i, new ArrayList<Bonus>());
            
            for(int j = 0; j < width; j++) {
                bonuses.get(i).add(j, null);
            }
        }
        
    }
    
    //---------------------------------------------
    
    private StackAttackGame game;
    
    //---------------------------------------------
    
    private int width;
    
    public int getWidth() {
        
        return width;
    }
    
    private int height;
    
    public int getHeight() {
        return height;
    }
    
    //---------------------------------------------------------
    
    private Texture backgroundTx;
    private Sprite background;
    
    private BitmapFont score;
    private BitmapFont lives;
    private BitmapFont doubleJump;
    
    private SpriteBatch batch;
   
    
    public void setBatch(SpriteBatch b) {
        
        batch = b;
    }
    
    //------------------- Коробки ---------------------------
    
    private ArrayList<ArrayList<Box>> boxes = new ArrayList<ArrayList<Box>>();
    
    public ArrayList<ArrayList<Box>> getBoxes() {
        
        return boxes;
    }
    
    public boolean addBox(Box box, Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(findBox(pos) == null) {
                boxes.get(pos.y).set(pos.x, box);
                return true;
            }
        }
        return false;
    }
    
    public void removeBox(Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(findBox(pos) != null) 
                boxes.get(pos.y).set(pos.x, null);  
        }
    }
    
    public void removeColorBoxes(String color) {
        
         System.out.println("#1: " + boxes.size()+ "; " /*+ pos.y*/);
        for(int i = 0; i < boxes.size(); i++) {
            
            for(int j = 0; j < boxes.get(i).size(); j++) {
                
                if(boxes.get(i).get(j) != null) {
                    
                    if(boxes.get(i).get(j).getColor().equals(color))
                        boxes.get(i).set(j, null);
                }
            }
        }
    }
    
    //------------------- Поиск коробки ---------------------------
    
    public Box findBox(Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(boxes.get(pos.y).size() > 0) {
                
                if(boxes.get(pos.y).get(pos.x) != null)
                    return boxes.get(pos.y).get(pos.x);
                
                else
                    return null;
            }
            
            else
                return null;
        }
        
        else
            return null;
    }
    
    
    //------------------- Поиск соседей ---------------------------
    
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
    
    public ArrayList<Box> findNeighbour(Player player, DIRECTION dir, boolean secondCell) {
        
        ArrayList<Box> _neighboures = new ArrayList<Box>();
        
        Box temp;
        
        int add;
        
        if(secondCell)
            add = 1;
        else
            add = 0;
        
        switch (dir) {
            
            case LEFT:

                for(int i = player.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(player.getPosition().y + add).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y + add)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, player.getPosition().y + add));
                    
                    for(int j = temp.getPosition().y + 1 + add; j < height; j++) {
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
                    if(boxes.get(player.getPosition().y + add).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y + add)));
                    else {

                        return _neighboures;
                    }
                    
                    temp = findBox(new Point(i, player.getPosition().y + add));
                    
                    for(int j = temp.getPosition().y + 1 + add; j < height; j++) {
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
                    if(boxes.get(player.getPosition().y + add + player.getHeightToJump()).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y + add + player.getHeightToJump())));
                    else {
                        
                        return _neighboures;
                    }
                    
                    temp = findBox(new Point(i, player.getPosition().y + add + player.getHeightToJump()));
                    
                    for(int j = temp.getPosition().y + 1 + add; j < height; j++) {
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
                    if(boxes.get(player.getPosition().y + add + player.getHeightToJump()).get(i) != null)
                        _neighboures.add(findBox(new Point(i, player.getPosition().y + add + player.getHeightToJump())));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, player.getPosition().y + add + player.getHeightToJump()));
                    
                    for(int j = temp.getPosition().y + 1 + add; j < height; j++) {
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
    
    public ArrayList<Box> findNeighbour(Bonus bonus, DIRECTION dir) {
        
        ArrayList<Box> _neighboures = new ArrayList<Box>();
        
        Box temp;
        
        switch (dir) {
            
            case LEFT:

                for(int i = bonus.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(bonus.getPosition().y).get(i) != null)
                        _neighboures.add(findBox(new Point(i, bonus.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, bonus.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT:

                for(int i = bonus.getPosition().x + 1; i < width; i++)
                {
                    if(boxes.get(bonus.getPosition().y).get(i) != null)
                        _neighboures.add(findBox(new Point(i, bonus.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, bonus.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case UP: 

                for(int i = bonus.getPosition().y + 1; i < height; i++)
                {
                    if(boxes.get(i).get(bonus.getPosition().x) != null)
                        _neighboures.add(findBox(new Point(bonus.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case DOWN:

                for(int i = bonus.getPosition().y - 1; i >= 0; i--)
                {
                    if(boxes.get(i).get(bonus.getPosition().x) != null)
                        _neighboures.add(findBox(new Point(bonus.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case LEFT_UP:

                for(int i = bonus.getPosition().x - 1; i >= 0; i--) {
                    if(boxes.get(bonus.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBox(new Point(i, bonus.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, bonus.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(boxes.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBox(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT_UP: 

                for(int i = bonus.getPosition().x + 1; i < width; i++)
                {
                    if(boxes.get(bonus.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBox(new Point(i, bonus.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBox(new Point(i, bonus.getPosition().y + 1));
                    
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
    
    public ArrayList<Bonus> findNeighbourBonuses(Box box, DIRECTION dir) {
        
        ArrayList<Bonus> _neighboures = new ArrayList<Bonus>();
        
        Bonus temp;
        
        switch (dir) {
            
            case LEFT:

                for(int i = box.getPosition().x - 1; i >= 0; i--) {
                    if(bonuses.get(box.getPosition().y).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, box.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, box.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT:

                for(int i = box.getPosition().x + 1; i < width; i++)
                {
                    if(bonuses.get(box.getPosition().y).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, box.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, box.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case UP: 

                for(int i = box.getPosition().y + 1; i < height; i++)
                {
                    if(bonuses.get(i).get(box.getPosition().x) != null)
                        _neighboures.add(findBonus(new Point(box.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case DOWN:

                for(int i = box.getPosition().y - 1; i >= 0; i--)
                {
                    if(bonuses.get(i).get(box.getPosition().x) != null)
                        _neighboures.add(findBonus(new Point(box.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case LEFT_UP:

                for(int i = box.getPosition().x - 1; i >= 0; i--) {
                    if(bonuses.get(box.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, box.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, box.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT_UP: 

                for(int i = box.getPosition().x + 1; i < width; i++)
                {
                    if(bonuses.get(box.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, box.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, box.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;
        }
        
        return _neighboures;
    }
    
    public ArrayList<Bonus> findNeighbourBonuses(Bonus bonus, DIRECTION dir) {
        
        ArrayList<Bonus> _neighboures = new ArrayList<Bonus>();
        
        Bonus temp;
        
        switch (dir) {
            
            case LEFT:

                for(int i = bonus.getPosition().x - 1; i >= 0; i--) {
                    if(bonuses.get(bonus.getPosition().y).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, bonus.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, bonus.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT:

                for(int i = bonus.getPosition().x + 1; i < width; i++)
                {
                    if(bonuses.get(bonus.getPosition().y).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, bonus.getPosition().y)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, bonus.getPosition().y));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case UP: 

                for(int i = bonus.getPosition().y + 1; i < height; i++)
                {
                    if(bonuses.get(i).get(bonus.getPosition().x) != null)
                        _neighboures.add(findBonus(new Point(bonus.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case DOWN:

                for(int i = bonus.getPosition().y - 1; i >= 0; i--)
                {
                    if(bonuses.get(i).get(bonus.getPosition().x) != null)
                        _neighboures.add(findBonus(new Point(bonus.getPosition().x, i)));
                    else
                        return _neighboures;
                }
                
                return _neighboures;

            case LEFT_UP:

                for(int i = bonus.getPosition().x - 1; i >= 0; i--) {
                    if(bonuses.get(bonus.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, bonus.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, bonus.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;

            case RIGHT_UP: 

                for(int i = bonus.getPosition().x + 1; i < width; i++)
                {
                    if(bonuses.get(bonus.getPosition().y + 1).get(i) != null)
                        _neighboures.add(findBonus(new Point(i, bonus.getPosition().y + 1)));
                    else
                        return _neighboures;
                    
                    temp = findBonus(new Point(i, bonus.getPosition().y + 1));
                    
                    for(int j = temp.getPosition().y + 1; j < height; j++) {
                         if(bonuses.get(j).get(temp.getPosition().x) != null)
                            _neighboures.add(findBonus(new Point(temp.getPosition().x, j)));
                        else
                            break;
                    }
                }
                
                return _neighboures;
        }
        
        return _neighboures;
    }
    
    //----------------------------------------------
    
    private int existingBoxesSize(int _height) {
        
        int exisitingBoxesCounter = 0;
        
        for(Box b : boxes.get(_height)) {
            if(b != null)
                exisitingBoxesCounter++;
        }
        
        return exisitingBoxesCounter;
    }
    
    //----------------------------------------------
    
    private ArrayList<ArrayList<Bonus>> bonuses = new ArrayList<ArrayList<Bonus>>();
    
    public ArrayList<ArrayList<Bonus>> getBonuses() {
        
        return bonuses;
    }
    
    public boolean addBonus(Bonus bonus, Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(findBonus(pos) == null) {
                bonuses.get(pos.y).set(pos.x, bonus);
                return true;
            }
        }
        return false;
    }
    
    
    public boolean removeBonus(Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
                bonuses.get(pos.y).set(pos.x, null); 
                return true;
        }
        return false;
    }
    
    public Bonus findBonus(Point pos) {
        
        if(pos.x >= 0 && pos.x < width
                && pos.y >= 0 && pos.y < height) {
            
            if(bonuses.get(pos.y).size() > 0) {
              
                if(bonuses.get(pos.y).get(pos.x) != null) {
                    return bonuses.get(pos.y).get(pos.x);
                }
                
                else
                    return null;
            }
            
            else
                return null;
        }
        
        else
            return null;
    }
    
    //-------------------------------------------------------
    
    private void checkIfRowIsFilled() {
        
        if(existingBoxesSize(0) == width) {
           
            for(int i = 0; i < width; i++) {
                boxes.get(0).set(i, null);
            }
            
            game.increaseScore(1);
        }
    }
    
    //----------------------------------------------
    
    private long timeToFly = 0;
    private long timeToNewBox = 0;
    
    public void setTimeToNewBox(long time) {
        timeToNewBox = time;
    }
    
    private void lowerFlyingObjects() {
        
        for(ArrayList<Box> _boxes : boxes) {
            for(Box b : _boxes) {
                if(b != null) {
                    if(b.getPosition().y != 0
                        && findNeighbour(b, DIRECTION.DOWN).size() == 0)
                        b.move(DIRECTION.DOWN);
                }
            }
        }
        
        for(ArrayList<Bonus> _bon : bonuses) {
            for(Bonus b : _bon) {
                if(b != null) {
                    if(b.getPosition().y != 0
                        && findNeighbour(b, DIRECTION.DOWN).size() == 0)
                        b.move(DIRECTION.DOWN);
                }
            }
        }
        
        if(game.getPlayer().getPosition().y != 0
                && findNeighbour(game.getPlayer(), DIRECTION.DOWN, false).size() == 0)
            game.getPlayer().makeMove(DIRECTION.DOWN);
        
        timeToFly = TimeUtils.nanoTime();
    }
    
    //----------------------------------------------
    
    @Override
    public void show() {

        backgroundTx = new Texture("graphic/background.png");
        background = new Sprite(backgroundTx);

        score = new BitmapFont(Gdx.files.internal("fonts/title.fnt"),Gdx.files.internal("fonts/title.png"),false);
        lives = new BitmapFont(Gdx.files.internal("fonts/title.fnt"),Gdx.files.internal("fonts/title.png"),false);
        doubleJump = new BitmapFont(Gdx.files.internal("fonts/title.fnt"),Gdx.files.internal("fonts/title.png"),false);

    }
    
    public void drawScore() {
        score.draw(game.getBatch(), "Score: " + game.getScore().getValue(), 800, 650);
    }
    
    public void drawLives() {
        lives.draw(game.getBatch(), "Lives: " + game.getPlayer().getAmountOfLives(), 50, 650);
    }
    
    public void drawDoubleJump() {
        doubleJump.draw(game.getBatch(), "DOUBLE JUMP", 400, 650);
    }
    
    public void paintBoxes() {

        for(ArrayList<Box> _boxes : boxes) {
            for(Box b : _boxes) {
                if(b != null) {
                    
                    b.paint(game.getTextureByColor(b.getColor(), b.canBeBroken(), b.getBonus()), game.getBatch());
                }
            }
        }
    }
    
    public void paintBonuses() {
        
        for(ArrayList<Box> _boxes : boxes) {
            for(Box b : _boxes) {
                if(b != null) {
                    if(b.getBonus() != null)
                        b.getBonus().paint(game.getBonusTexture(b.getBonus().getType()), game.getBatch());
                }
            }
        }
        
        for(ArrayList<Bonus> _bon : bonuses) {
            for(Bonus b : _bon) {
                if(b != null) {
                    b.paint(game.getBonusTexture(b.getType()), batch);
                }
            }
        }
    }
    
    public Texture getPlayerTexture(boolean dir) {
        
        return game.getPlayerTexture(dir);
    }
    

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(this.game.getModule() != null &&
            TimeUtils.nanoTime() - timeToFly > 800000000) {
            this.game.runModule();
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.M)){{
            ModuleEngine.main(null, this.game);
        }}
        
        game.getBatch().begin(); //BEGIN

        drawBackground();
        
        paintBoxes();
        paintBonuses();
        
        drawScore();
        drawLives();
        
        if(game.getPlayer().getHeightToJump() == 2)
            drawDoubleJump();
          
        game.getPlayer().paint(game.getBatch());

        if(this.game.getModule() == null)
            checkPlayerActions();
        
        if(TimeUtils.nanoTime() - timeToFly > 800000000)
            lowerFlyingObjects();
         
        if(TimeUtils.millis() - timeToNewBox > 5000) {
            Box newBox = game.createNewBox();
            

            newBox.setTexture(game.getTextureByColor(newBox.getColor(), newBox.canBeBroken(), newBox.getBonus()));
        }
         
        if(TimeUtils.millis() - game.getDoubleJumpTime() > 30000) {
            game.disactivateDoubleJump();
        }
         
        checkIfRowIsFilled();

        game.getPlayer().checkSituation();
          
        game.getBatch().end();

    }
    
    public void drawBackground() {
        
        background.setPosition(0, 0);
        background.draw(game.getBatch());
    }
    
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    
    @Override
    public void dispose() {

        backgroundTx.dispose();
    }
    
    //----------------------------------------------
    
    private void checkPlayerActions()
    {
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                fireBoxIsSelected(DIRECTION.LEFT_UP);
            else
                fireMoveIsDone(DIRECTION.LEFT_UP);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                fireBoxIsSelected(DIRECTION.UP);
            else
                fireMoveIsDone(DIRECTION.UP);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
           
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                fireBoxIsSelected(DIRECTION.RIGHT_UP);
            else
                fireMoveIsDone(DIRECTION.RIGHT_UP);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                fireBoxIsSelected(DIRECTION.LEFT);
            else
                fireMoveIsDone(DIRECTION.LEFT);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
             
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                fireBoxIsSelected(DIRECTION.DOWN);
            else 
                fireMoveIsDone(DIRECTION.DOWN);
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
                fireBoxIsSelected(DIRECTION.RIGHT);
            else
                fireMoveIsDone(DIRECTION.RIGHT);
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
        }
    }
    
     // ------------------------ События и слушатели -------------------------
  
    // Список слушателей
    private ArrayList _listenersList = new ArrayList(); 
 
    /**
     * Присоединяет слушателя
     * 
     * @param l слушатель
     */
    public void addGameListener(MoveListener l) { 
        _listenersList.add(l); 
    }
    
   //----------------------------------------------

    private void fireMoveIsDone(DIRECTION dir) {
        
        MoveEvent event = new MoveEvent(this);
        for (Object listener : _listenersList)
        {
            ((MoveListener)listener).moveIsDone(event, dir);
        }
    }
    
    private void fireBoxIsSelected(DIRECTION dir) {
        
        MoveEvent event = new MoveEvent(this);
        for (Object listener : _listenersList)
        {
            ((MoveListener)listener).boxIsSelected(event, dir);
        }
    }
}
