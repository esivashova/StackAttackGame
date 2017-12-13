/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack.managers;
import com.stackattack.objects.Player;
import com.stackattack.objects.Box;
import java.util.TreeMap;
import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class GameModel {
    
    public GameModel() {
        
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
        
        field = f;
    }
    
    //----------------------------------------
    
    public void start(TreeMap<String, Integer> attributes) {
        
        /*
            Состав атрибутов:
        
            1. Длина поля
            2. Высота поля
        */
        
        field = new GameField(this, 16, 10);
        player = new Player(field, 1, 1);
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
        
        player.setPosition(new Point(xPlayer, targetHeight));
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
                return "graphic/grey";
                
            case 1:
                return "graphic/red";
                
            case 2:
                return "graphic/blue";
             
            case 3:    
                return "graphic/yellow";
                
            case 4:
                return "graphic/green";
        }
        
        return "";
    }
}
