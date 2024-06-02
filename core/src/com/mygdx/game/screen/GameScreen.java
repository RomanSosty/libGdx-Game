package com.mygdx.game.screen;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.character.Player;
import com.mygdx.game.character.WoodEnemy;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.GameContactListener;
import com.mygdx.game.settings.GameSettings;
import com.mygdx.game.settings.Renderer;
import com.mygdx.game.world.GroundMap;

public class GameScreen extends ScreenAdapter {

    private final OrthographicCamera camera;
    private final Box2DDebugRenderer debugRenderer;
    private final SpriteBatch batch;
    private final Renderer renderer;
    private final Array<WoodEnemy> woodEnemies = new Array<>();
    private final MyGdxGame game;
    private World world;
    private Player player;
    private GroundMap groundMap;

    public GameScreen(MyGdxGame game, OrthographicCamera camera) {
        this.batch = game.batch;
        this.camera = camera;
        this.game = game;

        setWorld();
        renderer = new Renderer(batch, camera);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    }

    @Override
    public void show() {
        createCharacters();
        groundMap = new GroundMap(world, camera);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        setCamera();
        world.step(delta, 6, 2);

        if (player.isDead()) {
            game.setScreen(new MainMenu(game));
        } else {
            groundMap.render();
            debugRenderer.render(world, camera.combined);

            batch.begin();
            for (WoodEnemy enemy : woodEnemies) {
                renderer.characterRender(enemy, delta);
            }
            renderer.playerUIRender();
            renderer.characterRender(player, delta);
            renderer.healthRender(player);
            batch.end();
        }
    }

    @Override
    public void hide() {
        groundMap.dispose();
    }

    private void setCamera() {
        camera.position.set((float) GameSettings.SCREEN_WIDTH / 3 + (player.getBody().getPosition().x - (float) Assets.playerUI.getWidth() / 2)
                , (float) GameSettings.SCREEN_HEIGHT / 2 + (player.getBody().getPosition().y - Assets.playerUI.getHeight()) / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void setWorld() {
        world = new World(new Vector2(0, -9.81f), true);
        world.setContactListener(new GameContactListener());
    }

    private void createCharacters() {
        player = new Player(world);

        WoodEnemy woodEnemy = new WoodEnemy(world, 400, 350);
        WoodEnemy woodEnemy2 = new WoodEnemy(world, 400, 150);
        woodEnemies.add(woodEnemy);
        woodEnemies.add(woodEnemy2);
    }
}