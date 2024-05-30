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
    private final int width, height, buttonWidth, buttonHeight;
    public OrthographicCamera camera;

    public MainMenu(MyGdxGame game) {
        this.game = game;

        width = GameSettings.SCREEN_WIDTH;
        height = GameSettings.SCREEN_HEIGHT;
        buttonWidth = GameSettings.BUTTTON_WIDTH;
        buttonHeight = GameSettings.BUTTTON_HEIGHT;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(width, height);
        camera.position.set((float) width / 2, (float) height / 2, 0);

        Gdx.input.setInputProcessor(new MainMenuInputProcessor(this, game));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.22f, 0.25f, 0.24f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        drawMenuButtons();
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public boolean selectQuit(float x, float y) {
        int widthQuitArea = (width / 2 - buttonWidth / 2) + buttonWidth;
        int heightQuitArea = 100 + buttonHeight;

        return x >= 50 && x <= widthQuitArea
                && y >= 110 && y <= heightQuitArea;
    }

    public boolean selectPlay(float x, float y) {
        int widthPlayArea = (width / 2 - buttonWidth / 2) + buttonWidth;
        int heightPlayArea = 200 + buttonHeight;

        return x >= 50 && x <= widthPlayArea
                && y >= 210 && y <= heightPlayArea;
    }

    private void drawMenuButtons() {
        game.batch.draw(Assets.playButton, (float) width / 2 - (float) buttonWidth / 2, 200);
        game.batch.draw(Assets.optionsButton, (float) width / 2 - (float) buttonWidth / 2, 150);
        game.batch.draw(Assets.quitButton, (float) width / 2 - (float) buttonWidth / 2, 100);

    }
}
