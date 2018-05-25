package com.stackattack.modules;

import com.stackattack.StackAttackGame;

import java.io.File;
import javax.swing.JFileChooser;

public class ModuleEngine {

  public static void main(String args[], StackAttackGame g) {

//    JFileChooser fileopen = new JFileChooser("C:\\Users\\User\\Documents\\GDX_projects\\core\\build\\classes\\main\\com\\stackattack");
//    int ret = fileopen.showDialog(null, "Загрузить");
//    String moduleName = null;
//    String modulePath = null;
//    if (ret == JFileChooser.APPROVE_OPTION) {
//        File file = fileopen.getSelectedFile();
//        moduleName = file.getName().split("\\.class")[0];
//        modulePath = (String)file.getPath();
//    }

    JFileChooser fileopen = new JFileChooser();             
    int ret = fileopen.showDialog(null, "Открыть файл");
    String moduleName = null;
    String modulePath = "C:\\Users\\User\\Documents\\GDX_projects\\core\\build\\classes\\main\\com\\stackattack\\modules";
    if (ret == JFileChooser.APPROVE_OPTION) {
        File file = fileopen.getSelectedFile();
        moduleName = file.getName().split(".class")[0];
        modulePath = (String)file.getPath();
    }
    
    ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

    try {
            System.out.print("Executing loading module: ");
            System.out.println(moduleName);

            Class c = loader.loadClass("com.stackattack."+moduleName);
            Module execute = (Module) c.newInstance();

            execute.load(g, execute);
            execute.unload();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

  }

}