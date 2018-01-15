package com.stackattack.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.stackattack.StackAttackGame;

public class MainMenu implements Screen {

    private final float appWidth = 1024;
    private final float appHeight = 660;

    StackAttackGame game;

    OrthographicCamera camera;
    BitmapFont debug;
    Texture title, startbt, exitbt, startbt_select, exitbt_select;

    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();

    public MainMenu(StackAttackGame game) {
        this.game = game;

        title = new Texture("menu/logo.png");
        startbt = new Texture("menu/start_button.png");
        startbt_select = new Texture("menu/start_button_select.png");
        exitbt = new Texture("menu/exit_button.png");
        exitbt_select = new Texture("menu/exit_button_select.png");
        camera = new OrthographicCamera(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.setToOrtho(false, appWidth, appHeight);
        camera.update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor((float) 100/ 255, (float) 200 / 255, (float) 189 / 255, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(title, 200, 300);

        if (Gdx.input.getX() < 396 && Gdx.input.getX() > 100 && Gdx.input.getY() < 560 && Gdx.input.getY()> 432) {
            
            game.getBatch().draw(startbt_select, 100, 100, 296, 128);
            
            if (Gdx.input.isTouched()) {
               
                game.start();
                game.setScreen(game.getField());
            }
        } 
        
        else {
            
            game.getBatch().draw(startbt,  100, 100, 296, 128);
        }

        if (Gdx.input.getX() < 924 && Gdx.input.getX() > 628 && Gdx.input.getY() < 560 && Gdx.input.getY() > 432) {
            
            game.getBatch().draw(exitbt_select, 628, 100, 296, 128);
            
            if (Gdx.input.isTouched()) {

                Gdx.app.exit();
            }
        } 
        
        else {
            
            game.getBatch().draw(exitbt, 628, 100, 296, 128);
        }
        
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {

            Gdx.app.exit();
        }
        
        game.getBatch().end();

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
        title.dispose();
        startbt.dispose();
        startbt_select.dispose();
        exitbt.dispose();
        exitbt_select.dispose();
    }

}
