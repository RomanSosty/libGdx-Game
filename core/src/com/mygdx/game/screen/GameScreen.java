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
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.GameSettings;
import com.mygdx.game.utils.GameContactListener;
import com.mygdx.game.utils.MyRenderer;
import com.mygdx.game.world.GroundMap;
import com.mygdx.game.world.candies.BlueCandy;
import com.mygdx.game.world.character.Player;
import com.mygdx.game.world.character.WoodEnemy;

public class GameScreen extends ScreenAdapter {

    private final OrthographicCamera camera;
    private final Box2DDebugRenderer debugRenderer;
    private final SpriteBatch batch;
    private final MyRenderer myRenderer;
    private final Array<WoodEnemy> woodEnemies = new Array<>();
    private final Array<BlueCandy> blueCandies = new Array<>();
    private final MyGdxGame game;
    private World world;
    private Player player;
    private GroundMap groundMap;

    public GameScreen(MyGdxGame game, OrthographicCamera camera) {
        this.batch = game.getBatch();
        this.camera = camera;
        this.game = game;
        setWorld();
        myRenderer = new MyRenderer(batch, camera);
        debugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);
    }

    private void setWorld() {
        world = new World(new Vector2(0, -9.81f), true);
        world.setContactListener(new GameContactListener());
    }

    @Override
    public void show() {
        createCharacters();
        groundMap = new GroundMap(world, camera);
    }

    private void createCharacters() {
        player = new Player(world);

        WoodEnemy woodEnemy = new WoodEnemy(world, 400, 350);
        WoodEnemy woodEnemy2 = new WoodEnemy(world, 400, 150);
        woodEnemies.add(woodEnemy);
        woodEnemies.add(woodEnemy2);

        BlueCandy blueCandy = new BlueCandy(world, 440, 250);
        //BlueCandy blueCandy2 = new BlueCandy(world, 740, 250);
        blueCandies.add(blueCandy);
        //blueCandies.add(blueCandy2);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        defaultCameraSetting();
        world.step(delta, 6, 2);

        //TODO: refaktor, maybe new Class for game logic ?
        if (player.isDestroyed()) {
            game.setScreen(new MainMenu(game));
        } else {
            groundMap.render();
            debugRenderer.render(world, camera.combined);

            batch.begin();
            for (WoodEnemy enemy : woodEnemies) {
                myRenderer.objectRenderer(enemy, delta);
            }
            for (BlueCandy candy : blueCandies) {
                myRenderer.objectRenderer(candy, delta);
            }

            myRenderer.playerUIRender();
            myRenderer.objectRenderer(player, delta);
            myRenderer.healthRender(player);
            batch.end();
        }
    }

    private void defaultCameraSetting() {
        camera.position.set((float) GameSettings.SCREEN_WIDTH / 3 + (player.getBody().getPosition().x - (float) Assets.playerUI.getWidth() / 2)
                , (float) GameSettings.SCREEN_HEIGHT / 2 + (player.getBody().getPosition().y - Assets.playerUI.getHeight()) / 2, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void hide() {
        groundMap.dispose();
    }
}