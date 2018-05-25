package com.stackattack;

import com.stackattack.objects.bonuses.AdditionPoints;
import com.stackattack.objects.bonuses.Bonus;
import com.stackattack.objects.bonuses.RemovingColor;
import com.stackattack.objects.bonuses.RemovingBottomRow;
import com.stackattack.objects.bonuses.DoubleJump;
import com.stackattack.objects.bonuses.AdditionLife;
import com.stackattack.screens.MainMenu;
import com.stackattack.screens.GameOverScreen;
import com.stackattack.modules.Module;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Preferences;
import com.stackattack.objects.Player;
import com.stackattack.objects.Box;
import com.stackattack.objects.Score;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.stackattack.screens.GameField;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.objects.bonuses.TYPE_BONUS;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

/*
 * Класс игры 
 */
public class StackAttackGame extends Game{
    
    public StackAttackGame() {
        
        super();
       
        start(); 
    }
    
    //--------------------------------------
    private Preferences pref;

    private SpriteBatch batch;

    public SpriteBatch getBatch() {

        return batch;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        pref = Gdx.app.getPreferences("Score");

        playerTxRight = new Texture(Gdx.files.local("graphic/playerR.png"));
        playerTxLeft = new Texture(Gdx.files.local("graphic/playerL.png"));

        boxRed = new Texture(Gdx.files.internal("graphic/red.png"));
        boxGrey = new Texture(Gdx.files.internal("graphic/grey.png"));
        boxYellow = new Texture(Gdx.files.internal("graphic/yellow.png"));
        boxGreen = new Texture(Gdx.files.internal("graphic/green.png"));
        boxBlue = new Texture(Gdx.files.internal("graphic/blue.png"));

        boxRedBr = new Texture(Gdx.files.internal("graphic/redBr.png"));
        boxGreyBr = new Texture(Gdx.files.internal("graphic/greyBr.png"));
        boxYellowBr = new Texture(Gdx.files.internal("graphic/yellowBr.png"));
        boxGreenBr = new Texture(Gdx.files.internal("graphic/greenBr.png"));
        boxBlueBr = new Texture(Gdx.files.internal("graphic/blueBr.png"));

        points = new Texture(Gdx.files.internal("graphic/points.png"));
        minusRow = new Texture(Gdx.files.internal("graphic/minusRow.png"));
        colorTx = new Texture(Gdx.files.internal("graphic/color.png"));
        life = new Texture(Gdx.files.internal("graphic/life.png"));
        doubleJump = new Texture(Gdx.files.internal("graphic/doubleJump.png"));

        player.setTexture(playerTxRight);

        for(ArrayList<Box> bx : this.field.getBoxes())
        {
            for(Box b : bx)
            {
                if(b != null)
                {
                    b.setTexture(getTextureByColor(b.getColor(), b.canBeBroken(), b.getBonus()));
                }
            }
        }

        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
       super.render();
    }
        
    //-------------------------------------
    
    private Player player;
    
    public Player getPlayer() {
        
        return player;
    }
    
    public void setPlayer(Player p) {
        
        player = p;
    }
    
    //--------------------------------------
    
    private Module module = null;
    
    public Module getModule() {
        
        return module;
    }
    
    public void setModule(Module m) {
        
        module = m;
    }
    
    public void runModule() {
        module.run();
    }
    
    //--------------------------------------
    
    private GameField field;
    
    public GameField getField() {
        
        return field;
    }
    
    public void setGameField(GameField f) {
        
        f.setBatch(batch);
        field = f;
    }
    
    //----------------------------------------
    
    public void start() {
         
        field = new GameField(this, 16, 10);
        field.setBatch(batch);
        player = new Player(field, 1, 1);
        player.addGameListener(new GameEventObserver(this));
        score = new Score();
        
        generateGame();
    }
    
    private void generateGame() {
        
        // Генерация коробок
        int amountOfColumns = 0;
        
        while(amountOfColumns < field.getWidth()/4)
            amountOfColumns = random.nextInt((field.getWidth()*3)/4);
        
        ArrayList<Integer> sequence = generateSequence(amountOfColumns);
        
        for(int i = 0; i < amountOfColumns; i++) {
            
            int columnHeight = 0;
            
            while(columnHeight <= 0)
                columnHeight = random.nextInt(3);
            
            for(int j = 0; j < columnHeight; j++) {
                
                Box newBox = generateRandomBox();
                newBox.setPosition(new Point(sequence.get(i), j));
                
                if(newBox.getBonus() != null) {
                    newBox.getBonus().setPosition(new Point(newBox.getPosition()));
                }
                
                field.addBox(newBox, newBox.getPosition());
            }
        }
        
        // Генерация позиции игрока
        int xPlayer = random.nextInt(field.getWidth() - 1);
        int targetHeight = 0;
        
        for(int i = 0; i < field.getHeight() - 1; i++) {
            
            if(field.getBoxes().get(i).get(xPlayer) != null)
               
                targetHeight = i + 1;
            else 
                break;
        }
        
        player.setPosition(new Point(xPlayer, targetHeight));
        
    }
    
    //-------------------------------------------------------
      
    private Score score;
    
    public Score getScore() {
        return score;
    }
    
    public void increaseScore(int sc) {
        score.setValue(score.getValue() + sc);
    }
      
    //-------------------------------------------------------
    
    /**
    * Класс представляет слушателя события, возникающего при при совершении хода.
    */
    public class GameEventObserver implements GameListener {

        public GameEventObserver(StackAttackGame _game) {
            super();
            game = _game;
        }
        
        private StackAttackGame game;
        
        @Override
        public void gameFinished(GameEvent e) {
            pref.putFloat("score", score.getValue());
            setScreen(new GameOverScreen(this.game));
        }
        
        @Override
        public void addPoints(GameEvent e, int points) {
            increaseScore(points);
        }
        
        @Override
        public void removeBottomRow(GameEvent e) {
            
            for(int i = 0; i < field.getWidth(); i++) {
                field.getBoxes().get(0).set(i, null);
            }
            
            increaseScore(1);
        }
        
        @Override
        public void removeColor(GameEvent e, String color) {
            
            field.removeColorBoxes(color);
        }
        
        @Override
        public void bonusBind(GameEvent e, Bonus bonus) {
            bonus.addGameListener(new GameEventObserver(game));
        }
        
        
        @Override
        public void activateDoubleJump(GameEvent e) {
            player.setHeightToJump(2);
            doubleJumpTime = TimeUtils.millis();
        }
    }
    
    private long doubleJumpTime = 0;
   
    public long getDoubleJumpTime() {
        return doubleJumpTime;
    }
    
    public void disactivateDoubleJump() {
        player.setHeightToJump(1);
        doubleJumpTime = 0;
    }
    
    // -------------------------------- Рандом --------------------------------
    
    /** Рандом */
    private final Random random = new Random();
    
    private ArrayList<Integer> generateSequence(int amount) {
        
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        
        while(sequence.size() < amount) {
            
            Integer element = random.nextInt(field.getWidth() - 1);
            
            if(!sequence.contains(element))
                sequence.add(element);
        }
        
        return sequence;
    }
    
    private Box generateRandomBox() {
        
        int color = random.nextInt(4);
        
        boolean canBeBroken = false;
        Bonus newBonus = null;
        
        int chance = random.nextInt(4);
        if(chance == 3) {
            
            int bonusChance = random.nextInt(2);
            
            if(bonusChance == 1) {
                // генерируем бонус
                bonusChance = random.nextInt(5);
                newBonus = createBonus(bonusChance);
            }
            
            canBeBroken = true;
        }
           
        Box newBox = new Box(field, 1, getColorByNumber(color), canBeBroken, newBonus);
        
        if(newBonus != null)
            newBonus.setBox(newBox);
        
        return newBox;
    }
    
    private void placeBox(Box box) {
        
        int column = random.nextInt(field.getWidth());
        box.setPosition(new Point(column, field.getHeight() - 1));
        field.addBox(box, new Point(column, field.getHeight() - 1));
        
        if(box.getBonus() != null) {
            box.getBonus().setPosition(new Point(column, field.getHeight() - 1));
        }
    }
    
    public Box createNewBox() {
        
        Box newBox = generateRandomBox();
        placeBox(newBox);
        
        field.setTimeToNewBox(TimeUtils.millis());
        
        return newBox;
    }
    
    //------------------------------------------------------- 
    
    private Bonus createBonus(int num) {
        switch(num) {
            
            case 0:
                return new AdditionLife(field);
                
            case 1:
                return new AdditionPoints(field);
                
            case 2:
                return new DoubleJump(field);
             
            case 3:    
                return new RemovingBottomRow(field);
                
            case 4:
                return new RemovingColor(field);
        }
        
        return null;
    }
    
    public Texture getBonusTexture(TYPE_BONUS type) {
        
        
        switch (type) {
            case ADD_LIFE:
                
                return life;
                
            case DOUBLE_JUMP:
                
                return doubleJump;
               
            case ADD_POINTS:
                
                return points;
                
            case REMOVE_BOTTOM_ROW: 
                
                return minusRow;
                
            case REMOVE_COLOR: 
                
                return colorTx;
        }
        
        return null;
    }
    
    private String getColorByNumber(int num) {
        
        switch(num) {
            
            case 0:
                return "grey";
                
            case 1:
                return "red";
                
            case 2:
                return "blue";
             
            case 3:    
                return "yellow";
                
            case 4:
                return "green";
        }
        
        return null;
    }
    
    public Texture getTextureByColor(String col, boolean canBeBroken, Bonus bonus) {
        
        if(col.equals("grey")) {
            
            if(canBeBroken && bonus == null)
                return boxGreyBr;
            
            return boxGrey;
        }
                
        if(col.equals("red")) {
            
            if(canBeBroken && bonus == null)
                return boxRedBr;
            
            return boxRed;
        }
                
        if(col.equals("blue")) {
            
            if(canBeBroken && bonus == null)
                return boxBlueBr;
            
            return boxBlue;
        }
             
        if(col.equals("yellow")) {
            
            if(canBeBroken && bonus == null)
                return boxYellowBr;
            
            return boxYellow;
        }
                
        if(col.equals("green")) {
            
            if(canBeBroken && bonus == null)
                return boxGreenBr;
            
            return boxGreen;
        }

        return null;
    }
    
    public Texture getPlayerTexture(boolean dir) {
        if(dir)
            return playerTxRight;
        else
            return playerTxLeft;
    }
    
    private Texture playerTxLeft;
    private Texture playerTxRight;
    
    private Texture boxRed;
    private Texture boxGrey;
    private Texture boxYellow;
    private Texture boxGreen;
    private Texture boxBlue;
    private Texture boxRedBr;
    private Texture boxGreyBr;
    private Texture boxYellowBr;
    private Texture boxGreenBr;
    private Texture boxBlueBr;
    
    private Texture points;
    private Texture minusRow;
    private Texture colorTx;
    private Texture life;
    private Texture doubleJump;
}
