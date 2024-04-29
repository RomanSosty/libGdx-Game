package com.mygdx.game.screen;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.character.PinkMonster;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.GameSettings;
import com.mygdx.game.world.GroundMap;

public class GameScreen extends ScreenAdapter {

    private final MyGdxGame game;
    private final OrthographicCamera camera;
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private PinkMonster pinkMonster;
    private GroundMap groundMap;

    public GameScreen(MyGdxGame game, OrthographicCamera camera) {
        this.game = game;
        this.camera = camera;
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    }

    @Override
    public void show() {
        pinkMonster = new PinkMonster(world);
        groundMap = new GroundMap(world, camera);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.position.set(GameSettings.SCREEN_WIDTH / 2 + (pinkMonster.getBody().getPosition().x - Assets.playerUI.getWidth() / 2)
                , GameSettings.SCREEN_HEIGHT / 2, 0);
        camera.update();
        world.step(delta, 6, 2);

        game.batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined);

        groundMap.render();

        game.batch.begin();
        pinkMonster.render(game.batch);
        pinkMonster.update(delta);
        game.batch.draw(Assets.playerUI, 0 + (pinkMonster.getBody().getPosition().x - Assets.playerUI.getWidth() / 2), GameSettings.SCREEN_HEIGHT - Assets.playerUI.getHeight());
        game.batch.end();
    }

    @Override
    public void hide() {
        pinkMonster.dispose();
    }
}
