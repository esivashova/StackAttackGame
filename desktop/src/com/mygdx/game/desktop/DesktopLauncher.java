package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stackattack.StackAttackGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "StackAttack";
		config.foregroundFPS = 60;
		config.addIcon("menu/icon.png", Files.FileType.Internal);
		config.width = 1024;
		config.height = 660; 
		config.fullscreen = false;
		new LwjglApplication(new StackAttackGame(), config);
	}
}
