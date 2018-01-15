package com.stackattack;

import com.stackattack.screens.MainMenu;
import com.stackattack.screens.GameOverScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Preferences;
import com.stackattack.objects.Player;
import com.stackattack.objects.Box;
import com.stackattack.objects.Score;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.stackattack.bonuses.*;
import com.stackattack.screens.GameField;
import com.stackattack.events.GameEvent;
import com.stackattack.events.GameListener;
import com.stackattack.bonuses.TYPE_BONUS;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;


public class StackAttackGame extends Game{
    
    public StackAttackGame(/*boolean firstTime*/) {
        
        super();
       
//        if(firstTime) {
            start(); 
            
//        }
    }
//	SpriteBatch batch;
//	Texture img;
	
//	@Override
//	public void create () {
////		batch = new SpriteBatch();
////		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
////		Gdx.gl.glClearColor(1, 0, 0, 1);
////		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
////		batch.begin();
////		batch.draw(img, 0, 0);
////		batch.end();
//	}
//	
//	@Override
//	public void dispose () {
////		batch.dispose();
////		img.dispose();
//	}
//        
//        public void update() {
//            
//        }
    
        private Preferences pref;
        
//        public final int WIDTH = 1366;
//        public final int HEIGHT = 768;
        
        

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
           // batch.begin();
           super.render();
          //  batch.end();
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
    
    private GameField field;
    
    public GameField getField() {
        
        return field;
    }
    
    public void setGameField(GameField f) {
        
        f.setBatch(batch);
        field = f;
    }
    
    //----------------------------------------
    
    public void start(/*TreeMap<String, Integer> attributes*/) {
        
        /*
            Состав атрибутов:
        
            1. Длина поля
            2. Высота поля
        */
        
        field = new GameField(this, 16, 10);
        player = new Player(field, 1, 1);
        player.addGameListener(new GameEventObserver(this));
        score = new Score();
        
        generateGame();
    }
    
    private void generateGame() {
        
        for(int i = 0; i < field.getWidth() - 2; i++) {
            
            if(i==5) continue;
            Box newBox = generateRandomBox();
            newBox.setPosition(new Point(i, 0));
            
            if(newBox.getBonus() != null) {
                newBox.getBonus().setPosition(new Point(i, 0));
            }
            
            field.addBox(newBox, newBox.getPosition());
        }
        
        for(int i = 0; i < 5; i++) {
            
            Box newBox = generateRandomBox();
            newBox.setPosition(new Point(i, 1));
            
            if(newBox.getBonus() != null) {
                newBox.getBonus().setPosition(new Point(i, 1));
            }
            
            field.addBox(newBox, newBox.getPosition());
        }
//        // Генерация коробок
//        int amountOfColumns = 0;
//        
//        while(amountOfColumns < field.getWidth()/4)
//            amountOfColumns = random.nextInt((field.getWidth()*3)/4);
//        
//        ArrayList<Integer> sequence = generateSequence(amountOfColumns);
//        int count = 0;
//        
//        String data = new String("Sequence: ");
//        for(int x : sequence) {
//            data += String.valueOf(x);
//            data += "; ";
//        }
//        
//        for(int i = 0; i < amountOfColumns; i++) {
//            
//            int columnHeight = 0;
//            
//            while(columnHeight <= 0)
//                columnHeight = random.nextInt(field.getHeight()/2);
//            
//            for(int j = 0; j < columnHeight; j++) {
//                
//                boolean hui = false;
//                Box newBox = generateRandomBox();
//                newBox.setPosition(new Point(sequence.get(i), j));
//                if(
//                !field.addBox(newBox, newBox.getPosition()))
//                    hui = true;
//                
//                data += "\n";
//                 
//                    data += String.valueOf(count);
//                       data += "\t";
//                    data += newBox.getColor();
//                    data += "\t";
//                    data += String.valueOf(newBox.getPosition().x);
//                    data += "; ";
//                    data += String.valueOf(newBox.getPosition().y);
//                    data += "\n";
//                    if(hui) {
//                        data += "\t"; 
//                    data += "она не добавилась";
//                    }
//                    count++;
//            }
//        }
        
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
        
//        String data = new String("Sequence: ");
//        for(int x : sequence) {
//            data += String.valueOf(x);
//            data += "; ";
//        }
        
      //  data += "\n";
        
        int counterBox = 0;
//        data += "Boxes:\n";
//        for(ArrayList<Box> bx : this.field.getBoxes())
//        {
//            for(Box b : bx)
//            {
//                if(b != null)
//                {
//                    data += "\n";
//                    data += String.valueOf(counterBox);
//                    data += "\t";
//                    data += b.getColor();
//                    data += "\t";
//                    data += String.valueOf(b.getPosition().x);
//                    data += "; ";
//                    data += String.valueOf(b.getPosition().y);
//                    data += "\n";
//                    counterBox++;
//                }
//            }
//        }
        
//        data += "\n";
//        data += "Player";
//        data += "\t";
//        data += String.valueOf(this.player.getPosition().x);
//        data += "; ";
//        data += String.valueOf(this.player.getPosition().y);
//        data += "\n";
    
        
//         OutputStream os = null;
//        try {
//            os = new FileOutputStream(new File("C:\\Users\\User\\Documents\\GDX_projects\\log.txt"));
//            os.write(data.getBytes(), 0, data.length());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
    
//    private void finish() {
//        
//        if (snake.die()) {
//            pref.putFloat("score", game.sc.getSc());
//            game.setScreen(new GameOverScreen(game));
//        
//    
//    }
      
    private Score score;
    
    public Score getScore() {
        return score;
    }
    
    public void increaseScore(int sc) {
        score.setValue(score.getValue() + sc);
    }
            
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
            
            field.removeColor(color);
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
    
    //----------------------------------------
    
    private Box generateRandomBox() {
        
        int color = random.nextInt(4);
        
        boolean canBeBroken = false;
        Bonus newBonus = null;
        
        int chance = random.nextInt(4);
        if(chance == 3) {
            
            int bonusChance = random.nextInt(2);
            System.out.println("bonus chance " + bonusChance);
            
            if(bonusChance == 1) {
                // генерируем бонус
                System.out.println("bonus # " + bonusChance);
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
    
    //----------------------------------------
    
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
    
    private Texture getTextureByNumber(int num) {
        
        switch(num) {
            
            case 0:
                return boxGrey;
                
            case 1:
                return boxRed;
                
            case 2:
                return boxBlue;
             
            case 3:    
                return boxYellow;
                
            case 4:
                return boxGreen;
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
    
    Texture playerTxLeft;
    Texture playerTxRight;
    
    Texture boxRed;
    Texture boxGrey;
    Texture boxYellow;
    Texture boxGreen;
    Texture boxBlue;
    Texture boxRedBr;
    Texture boxGreyBr;
    Texture boxYellowBr;
    Texture boxGreenBr;
    Texture boxBlueBr;
    
    Texture points;
    Texture minusRow;
    Texture colorTx;
    Texture life;
    Texture doubleJump;
}
