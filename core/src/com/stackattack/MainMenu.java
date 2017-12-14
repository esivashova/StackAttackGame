package com.stackattack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.stackattack.managers.GameField;

public class MainMenu implements Screen {

    private final float appWidth = 1280;
    private final float appHeight = 720;

    private static final int START_BUTTON_WIDTH = 220;
    private static final int START_BUTTON_HEIGHT = 100;
    private static final int EXIT_BUTTON_WIDTH = 220;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int START_BUTTON_Y = 180;
    private static final int EXIT_BUTTON_Y = 180;
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
        game.batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor((float) 100/ 255, (float) 200 / 255, (float) 189 / 255, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(title, 325, 325);

        int x1 = 325;
        if (Gdx.input.getX() < x1 + START_BUTTON_WIDTH & Gdx.input.getX() > x1 && Gdx.input.getY() - 280 < START_BUTTON_Y + START_BUTTON_HEIGHT && Gdx.input.getY() - 280 > START_BUTTON_Y) {
            game.batch.draw(startbt_select, x1, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
//            if (Gdx.input.isTouched()) {
// !!!!!!!!!!!!!!!!!
//                game.setScreen(new GameField(game));
//            }
        } else {
            game.batch.draw(startbt, x1, START_BUTTON_Y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        }
        int x2 = 700;
        if (Gdx.input.getX() < x2 + EXIT_BUTTON_WIDTH & Gdx.input.getX() > x2 && Gdx.input.getY() - 280 < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Gdx.input.getY() - 280 > EXIT_BUTTON_Y) {
            game.batch.draw(exitbt_select, x2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {

                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitbt, x2, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {

            Gdx.app.exit();
        }
        game.batch.end();

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
