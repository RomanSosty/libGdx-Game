package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.inputProcesors.MainMenuInputProcessor;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.GameSettings;


public class MainMenu extends ScreenAdapter {
    private final MyGdxGame game;
    public OrthographicCamera camera;

    public MainMenu(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        camera.position.set(GameSettings.SCREEN_WIDTH / 2, GameSettings.SCREEN_HEIGHT / 2, 0);

        Gdx.input.setInputProcessor(new MainMenuInputProcessor(this, game));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.22f, 0.25f, 0.24f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(Assets.playButton, GameSettings.SCREEN_WIDTH / 2 - Assets.playButton.getWidth() / 2, 200);
        game.batch.draw(Assets.optionsButton, GameSettings.SCREEN_WIDTH / 2 - Assets.optionsButton.getWidth() / 2, 150);
        game.batch.draw(Assets.quitButton, GameSettings.SCREEN_WIDTH / 2 - Assets.quitButton.getWidth() / 2, 100);
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public boolean isQuit(float x, float y) {
        int width = (GameSettings.SCREEN_WIDTH / 2 - Assets.quitButton.getWidth() / 2) + Assets.quitButton.getWidth();
        int height = 100 + Assets.quitButton.getHeight();

        return x >= 50 && x <= width
                && y >= 110 && y <= height;
    }

    public boolean isPlay(float x, float y) {
        int width = (GameSettings.SCREEN_WIDTH / 2 - Assets.playButton.getWidth() / 2) + Assets.playButton.getWidth();
        int height = 200 + Assets.playButton.getHeight();

        return x >= 50 && x <= width
                && y >= 210 && y <= height;
    }
}
