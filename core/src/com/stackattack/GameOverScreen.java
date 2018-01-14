/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stackattack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameOverScreen implements Screen{
	StackAttackGame game;
	BitmapFont scoreFont,backFont;
	Preferences pref;
	
	private int score;
	
	public GameOverScreen(StackAttackGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		scoreFont = new BitmapFont();
		scoreFont.setColor(Color.WHITE);
		backFont = new BitmapFont();
		backFont.setColor(Color.BLUE);
	
		
		Preferences pref = Gdx.app.getPreferences("Score");
		score = (int) pref.getFloat("score", 0);
	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();

		scoreFont.draw(game.getBatch(), "You score: " + score, 600, 500);
		backFont.draw(game.getBatch(), "Press any key to back menu", 550,350);
		
		game.getBatch().end();
		
		if(Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
			game.setScreen(new MainMenu(game));
		}
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
		
	}

}
