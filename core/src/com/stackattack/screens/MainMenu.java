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

    private static final int START_BUTTON_WIDTH = 300;
    private static final int START_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int START_BUTTON_Y = 200;
    private static final int EXIT_BUTTON_Y = 200;
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
        game.getBatch().draw(title, 350, 350);

        int x1 = 250;
        if (Gdx.input.getX() < START_BUTTON_WIDTH && Gdx.input.getX() > x1 && Gdx.input.getY() < START_BUTTON_Y + START_BUTTON_HEIGHT && Gdx.input.getY()> START_BUTTON_Y) {
            game.getBatch().draw(startbt_select, x1, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
               // game.setGameField(new GameField(game, 16, 10));
                game.start();
                game.setScreen(game.getField());
            }
        } else {
            game.getBatch().draw(startbt, x1, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        }
        int x2 = 700;
        if (Gdx.input.getX() < x2 + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x2 && Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Gdx.input.getY() > EXIT_BUTTON_Y) {
            game.getBatch().draw(exitbt_select, x2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {

                Gdx.app.exit();
            }
        } else {
            game.getBatch().draw(exitbt, x2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
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
