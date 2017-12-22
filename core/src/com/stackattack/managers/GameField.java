/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.objects.Box;
import com.stackattack.objects.Player;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import java.util.ArrayList;
import java.awt.Point;


/**
 *
 * @author User
 */
public class GameField implements Screen {
    
    final float appWidth = 1280;
    final float appHeight = 720;

    private Texture backgroundTx;
    private Sprite background;
    
    private SpriteBatch batch;
    
    public void setBatch(SpriteBatch b) {
        
        batch = b;
    }
    
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
            
            for(int j = 0; j < width; j++) {
                boxes.get(i).add(j, null);
            }
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
    
    private ArrayList<ArrayList<Box>> boxes = new ArrayList<ArrayList<Box>>();
    
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
            
            ArrayList<Box> temp = new ArrayList<Box>();
            for(int i = 0; i < width; i++) {
                temp.add(i, null);
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
    
    @Override
    public void show() {

//        pref = Gdx.app.getPreferences("Score");
//
//        game.sc = new Score();
//        score = new BitmapFont();
//        score.setColor(Color.RED);
//
          backgroundTx = new Texture("graphic/background.png");
          background = new Sprite(backgroundTx);
          
//          paint(game.getField().getBoxes());
//          
//          game.getPlayer().paint(game.getBatch());
//
//        snake = new Snake(game);
//        snake.snakeBodyRectangle();
//        snake.startCoordinate();
//
//        apple1 = new Apple1();
//        apple2 = new Apple2();
//        apple3 = new Apple3();
//        stump = new Stump();
//        regionAcceleration = new RegionAcceleration();
//        regionSlowdown = new RegionSlowdown();
//        pear = new Pear();
//     
//        snake.passSubjects(apple1,apple2,apple3,stump,regionAcceleration, regionSlowdown, pear);
//        
//        snake.snakeHeadRectangle();
//
//        snake.snakeSprites();

    }
    
    public void paintBoxes() {
        
        for(ArrayList<Box> _boxes : boxes) {
            for(Box b : _boxes) {
                if(b != null)
                    b.paint(game.getBatch());
            }
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin(); //BEGIN

        drawBackground();
        
        paintBoxes();
          
       game.getPlayer().paint(game.getBatch());
//        spawnSubjects();
//        drawScore();
//        snake.drawBody();
//        snake.moveHead();
//        snake.moveBody();
//        snake.bodyRectangle();
//        rectanglesPosition();
//        snake.isPressed();
//        snake.collisionWithSubjects();
//        snake.collisionWithEdges();
//        snake.collisionWithBody();
        //snake.frameTimer();
//        snake.snakeSize = snake.snake_body.size;
        //gameOver();
        game.getBatch().end();

    }
    
   // private void
    
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
//        snake.snake_headtx.dispose();
//        apple1.subjtx.dispose();
//        apple2.subjtx.dispose();
//        apple3.subjtx.dispose();
//        stump.subjtx.dispose();
//        regionAcceleration.subjtx.dispose();
//        regionSlowdown.subjtx.dispose();
//        pear.subjtx.dispose();
        backgroundTx.dispose();
//        score.dispose();
    }
    
    
     // ------------------------- Слушатели хода --------------------------------
    
    /**
    * Класс представляет слушателя события, возникающего при при совершении хода.
    */
    public class GameEventObserver implements GameListener {

        @Override
        public void gameStarted(GameEvent e) {
            
        }
    }
}
