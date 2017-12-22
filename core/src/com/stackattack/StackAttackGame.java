package com.stackattack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stackattack.managers.GameModel;

public class StackAttackGame extends Game{
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
    
        private GameModel model;
        
        public GameModel getGameModel() {
            
            return model;
        }
        
        public final int WIDTH = 1366;
        public final int HEIGHT = 768;

        public SpriteBatch batch;

        @Override
        public void create() {
            batch = new SpriteBatch();
            
            model = new GameModel(batch);
            
            this.setScreen(new MainMenu(this));
        }

        @Override
        public void render() {
           // batch.begin();
           super.render();
          //  batch.end();
        }
}
