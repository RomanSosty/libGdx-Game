package com.mygdx.game.screen;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.character.PinkMonster;
import com.mygdx.game.settings.GameSettings;

public class GameScreen extends ScreenAdapter {

    private MyGdxGame game;
    private OrthographicCamera camera;
    private PinkMonster pinkMonster;
    private Texture background;
    private TextureRegion backgroundRegion;


    public GameScreen(MyGdxGame game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
    }

    @Override
    public void show() {
        background = new Texture("parallax-mountain-bg.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 250, 200);
        pinkMonster = new PinkMonster(0, 0);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundRegion, 0, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        pinkMonster.render(game.batch);
        game.batch.end();

        pinkMonster.update();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {
    }
}
