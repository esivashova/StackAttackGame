package com.stackattack;

import java.util.Random;
import com.stackattack.modules.Module;
import com.stackattack.navigation.DIRECTION;
import com.stackattack.objects.Box;
import com.stackattack.objects.Player;
import com.stackattack.screens.GameField;
import java.util.*;
import java.awt.Point;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;


public class ModuleGame implements Module {

    StackAttackGame game;
    Random random = new Random();
    
    @Override
    public void load(StackAttackGame g, Module batch) {
        this.game = g;
        this.game.setModule(batch);
        //this.rnd = new Random(System.currentTimeMillis());
    }

    
    @Override
    public int run() {
        
        String lineDir = new String();
        
        try {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\User\\Documents\\GDX_projects\\core\\assets\\BotDirection.txt"), StandardCharsets.UTF_8);
        lineDir = lines.get(0);
        }
                catch(IOException e) {
          e.printStackTrace();
        }
        DIRECTION botDir = getDirectionByNumber(Integer.parseInt(lineDir)); 
       // "C:\\Users\\User\\Documents\\GDX_projects\\core\\assets"
        
        Player p = game.getPlayer();
        GameField f = game.getField();
        
        ArrayList<Box> boxes = f.findNeighbour(p, DIRECTION.UP, false);
        
       // Point pos = p.getPosition();
       // p.setPosition(new Point(pos.x, pos.y + 1));
                
        ArrayList<Box> addBoxes = f.findNeighbour(p, DIRECTION.UP, true);
                
       //p.setPosition(new Point(pos.x, pos.y - 1));
        
        // коробка сверху есть
        if(!boxes.isEmpty() || !addBoxes.isEmpty()) {
            // если ее можно разбить - разбиваем
            if(!boxes.isEmpty()) {
                if(boxes.get(0).canBeBroken()) {
                    p.destroyBox(DIRECTION.UP);
                    return 0;
                }
            }
            
            // если нет - убегаем 
            else {
                
                boolean ok = false;

                while(!ok) {

                    int dir = random.nextInt(3);

                    if(p.makeMove(getDirectionByNumber(dir)))
                        return 0;
                }
            }
        }
        // коробок нет, выбираем оптимальный ход
        else {
            
            Point po = p.getPosition();
            
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i = 0; i < 5; i++) {
                list.add(0);
            }
            
            while(!allDirectionsAreChecked(list)) {
                
                int dir = random.nextInt(4);
                
                switch (dir) {
                    case 0:
                        if(f.findBonus(new Point(po.x - 1, po.y)) != null)
                        {
                            if(p.makeMove(DIRECTION.LEFT)) {
                                writeToFile(String.valueOf(DIRECTION.LEFT));
                                return 0;
                            }
                        }
                        else if(f.findBox(new Point(po.x - 1, po.y)) != null)
                        {
                            Box b = f.findBox(new Point(po.x - 1, po.y));
                            if(b.getBonus() != null || b.canBeBroken()) {
                                p.destroyBox(DIRECTION.LEFT);
                               // writeToFile(String.valueOf(DIRECTION.LEFT));
                                return 0;
                            }
                        }
                        list.add(0, 1);
                    case 1:
                        if(f.findBonus(new Point(po.x + 1, po.y)) != null)
                        {
                            if(p.makeMove(DIRECTION.RIGHT)) {
                                 writeToFile(String.valueOf(DIRECTION.RIGHT));
                                return 0;
                            }
                        }
                        else if(f.findBox(new Point(po.x + 1, po.y)) != null)
                        {
                            Box b = f.findBox(new Point(po.x + 1, po.y));
                            if(b.getBonus() != null || b.canBeBroken()) {
                                p.destroyBox(DIRECTION.RIGHT);
                                //writeToFile(String.valueOf(DIRECTION.RIGHT));
                                return 0;
                            }
                        }
                        list.add(1, 1);
                    case 2:
                        if(f.findBonus(new Point(po.x - 1, po.y + 1)) != null)
                        {
                            if(p.makeMove(DIRECTION.LEFT_UP))
                                {
                                 writeToFile(String.valueOf(DIRECTION.LEFT_UP));
                                return 0;
                            }
                        }
                        else if(f.findBox(new Point(po.x - 1, po.y + 1)) != null)
                        {
                            Box b = f.findBox(new Point(po.x - 1, po.y + 1));
                            if(b.getBonus() != null || b.canBeBroken()) {
                                p.destroyBox(DIRECTION.LEFT_UP);
                                return 0;
                            }
                        }
                        list.add(2, 1);
                    case 3:
                        if(f.findBonus(new Point(po.x + 1, po.y + 1)) != null)
                        {
                            if(p.makeMove(DIRECTION.RIGHT_UP))
                                 {
                                 writeToFile(String.valueOf(DIRECTION.RIGHT_UP));
                                return 0;
                            }
                        }
                        else if(f.findBox(new Point(po.x + 1, po.y + 1)) != null)
                        {
                            Box b = f.findBox(new Point(po.x + 1, po.y + 1));
                            if(b.getBonus() != null || b.canBeBroken()) {
                                p.destroyBox(DIRECTION.RIGHT_UP);
                                return 0;
                            }
                        }
                        list.add(3, 1);
                    case 4:
                        if(f.findBonus(new Point(po.x, po.y - 1)) != null)
                        {
                            if(p.makeMove(DIRECTION.DOWN))
                                return 0;
                        }
                        else if(f.findBox(new Point(po.x, po.y - 1)) != null)
                        {
                            Box b = f.findBox(new Point(po.x, po.y - 1));
                            if(b.getBonus() != null || b.canBeBroken()) {
                                p.destroyBox(DIRECTION.DOWN);
                                return 0;
                            }
                        }
                        list.add(4, 1);
                }
            }
            
            if(p.makeMove(botDir))
                return 0;
            else {
            
                int counter = 0;
            
                while(counter < 4) {
                    int dir = random.nextInt(3);

                    if(p.makeMove(getDirectionByNumber(dir))) {
                        
                        writeToFile(String.valueOf(dir));
                        
                        return 0;
                    }

                    counter++;
                }
            }  
        }
        
        return 0;
    }
        
    private void writeToFile(String s) {
        try {
            Files.write(Paths.get("C:\\Users\\User\\Documents\\GDX_projects\\core\\assets\\BotDirection.txt"), s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private DIRECTION getDirectionByNumber(int n) {
        
        switch (n) {
            case 0:
                return DIRECTION.LEFT;
            case 1:
                return DIRECTION.RIGHT;
            case 2:
                return DIRECTION.LEFT_UP;
            case 3:
                return DIRECTION.RIGHT_UP;
        }
        return DIRECTION.DOWN;
    }
    
    private boolean allDirectionsAreChecked(ArrayList<Integer> l) {
        
        for(int i = 0; i < 5; i++) {
            if(l.get(i) == 0)
                return false;
        }
        
        return true;
    }


    @Override
    public void unload() {
        System.out.println("unload");
    }
   
}
