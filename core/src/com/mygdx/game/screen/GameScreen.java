package com.mygdx.game.screen;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.character.PinkMonster;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.GameSettings;

public class GameScreen extends ScreenAdapter {

    private final MyGdxGame game;
    private final OrthographicCamera camera;
    private final World world;
    private PinkMonster pinkMonster;
    private OrthogonalTiledMapRenderer renderer;


    public GameScreen(MyGdxGame game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        world = new World(new Vector2(0, -9.81f), true);
    }

    @Override
    public void show() {
        pinkMonster = new PinkMonster(world);
        renderer = new OrthogonalTiledMapRenderer(Assets.map);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        world.step(delta, 6, 2);
        game.batch.setProjectionMatrix(camera.combined);

        renderer.setView(camera);
        renderer.render();

        game.batch.begin();
        pinkMonster.render(game.batch);
        pinkMonster.update(delta);
        game.batch.draw(Assets.playerUI, 15, GameSettings.SCREEN_HEIGHT - Assets.playerUI.getHeight());
        game.batch.end();
    }

    @Override
    public void hide() {
        pinkMonster.dispose();
    }
}
