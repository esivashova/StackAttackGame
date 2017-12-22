package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stackattack.StackAttackGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "StackAttack";
		//config.resizable = false;
		config.foregroundFPS = 60;
		config.addIcon("menu/icon.png", Files.FileType.Internal);
		config.width = 800;
		config.height = 500; 
		//config.x = -1;
		//config.y = -1;
		config.fullscreen = false;
		new LwjglApplication(new StackAttackGame(), config);
	}
}
