package com.stackattack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.objects.Player;
import com.stackattack.objects.Box;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.stackattack.managers.GameField;
import java.util.TreeMap;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

import java.io.*;

public class StackAttackGame extends Game{
    
    public StackAttackGame(/*boolean firstTime*/) {
        
        super();
       
//        if(firstTime) {
            start(); 
            generateGame();
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
    
        
        
        public final int WIDTH = 1366;
        public final int HEIGHT = 768;

        private SpriteBatch batch;
        
        public SpriteBatch getBatch() {
            
            return batch;
        }
       
        
        private void createTextures() {
            playerTx = new Texture(Gdx.files.local("graphic/playerR.png"));
            boxRed = new Texture(Gdx.files.internal("graphic/red.png"));
            boxGrey = new Texture(Gdx.files.internal("graphic/grey.png"));
            boxYellow = new Texture(Gdx.files.internal("graphic/yellow.png"));
            boxGreen = new Texture(Gdx.files.internal("graphic/green.png"));
            boxBlue = new Texture(Gdx.files.internal("graphic/blue.png"));
        }

        @Override
        public void create() {
            batch = new SpriteBatch();
            
            playerTx = new Texture(Gdx.files.local("graphic/playerR.png"));
            boxRed = new Texture(Gdx.files.internal("graphic/red.png"));
            boxGrey = new Texture(Gdx.files.internal("graphic/grey.png"));
            boxYellow = new Texture(Gdx.files.internal("graphic/yellow.png"));
            boxGreen = new Texture(Gdx.files.internal("graphic/green.png"));
            boxBlue = new Texture(Gdx.files.internal("graphic/blue.png"));
            
            player.setTexture(playerTx);
            
            for(ArrayList<Box> bx : this.field.getBoxes())
            {
                for(Box b : bx)
                {
                    if(b != null)
                    {
                        b.setTexture(getTextureByColor(b.getColor()));
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
    
    private void start(/*TreeMap<String, Integer> attributes*/) {
        
        /*
            Состав атрибутов:
        
            1. Длина поля
            2. Высота поля
        */
        
        field = new GameField(this, 16, 10);
        player = new Player(field, 1, 1);
    }
    
    private void generateGame() {
        
        for(int i = 0; i < field.getWidth() - 2; i++) {
            
            if(i==5) continue;
            Box newBox = generateRandomBox();
            newBox.setPosition(new Point(i, 0));
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
        box.setPosition(new Point(column, field.getHeight() - 1));
        field.addBox(box, new Point(column, field.getHeight() - 1));
    }
    
    public Box createNewBox() {
        
        Box newBox = generateRandomBox();
        placeBox(newBox);
        
        field.setTimeToNewBox(TimeUtils.millis());
        
        return newBox;
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
    
    public Texture getTextureByColor(String col) {
        
        if(col == "grey")
            return boxGrey;
                
        if(col == "red")
            return boxRed;
                
        if(col == "blue")
            return boxBlue;
             
        if(col == "yellow")
            return boxYellow;
                
        if(col == "green")
            return boxGreen;
        
   
        return null;
    }
    
    Texture playerTx;
    Texture boxRed;
    Texture boxGrey;
    Texture boxYellow;
    Texture boxGreen;
    Texture boxBlue;
}
