package com.stackattack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.objects.Player;
import com.stackattack.objects.Box;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.managers.GameField;
import java.util.TreeMap;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

public class StackAttackGame extends Game{
    
    public StackAttackGame() {
        
        super();
        start();
        generateGame();
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
    

        
        public final int WIDTH = 1366;
        public final int HEIGHT = 768;

        private SpriteBatch batch;
        
        public SpriteBatch getBatch() {
            
            return batch;
        }

        @Override
        public void create() {
            batch = new SpriteBatch();
            
            
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
    
    private void start(/*TreeMap<String, Integer> attributes*/) {
        
        /*
            Состав атрибутов:
        
            1. Длина поля
            2. Высота поля
        */
        
        field = new GameField(this, 16, 10);
        player = new Player(field, 1, 1, this);
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
                columnHeight = random.nextInt(field.getHeight()/2);
            
            for(int j = 0; j < columnHeight; j++) {
                
                Box newBox = generateRandomBox();
                newBox.setPosition(new Point(sequence.get(i), j));
                field.addBox(newBox, new Point(sequence.get(i), j));
            }
        }
        
        // Генерация позиции игрока
        int xPlayer = random.nextInt(field.getWidth() - 1);
        int targetHeight = 0;
        
        for(int i = 0; i < field.getHeight() - 1; i++) {
            
            if(field.getBoxes().get(i).get(xPlayer) != null)
                targetHeight = i;
            else
                break;
        }
        
        player.setPosition(new Point(xPlayer, targetHeight + 1));
    }
    
    private void finish() {
        
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
            
        return new Box(field, 1, getColorByNumber(color));   
    }
    
    private void placeBox(Box box) {
        
        int column = random.nextInt(field.getWidth());
        
        field.addBox(box, new Point(column, field.getHeight() - 1));
    }
    
    //----------------------------------------
    
    private String getColorByNumber(int num) {
        
        switch(num) {
            
            case 0:
                return "graphic/grey.png";
                
            case 1:
                return "graphic/red.png";
                
            case 2:
                return "graphic/blue.png";
             
            case 3:    
                return "graphic/yellow.png";
                
            case 4:
                return "graphic/green.png";
        }
        
        return "";
    }
}
