package com.mygdx.game.screen;


import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.character.Character;
import com.mygdx.game.character.Player;
import com.mygdx.game.character.WoodEnemy;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.GameContactListener;
import com.mygdx.game.settings.GameSettings;
import com.mygdx.game.world.GroundMap;

public class GameScreen extends ScreenAdapter {

    private final OrthographicCamera camera;
    private final Box2DDebugRenderer debugRenderer;
    private final SpriteBatch batch;

    private World world;
    private Player player;
    private WoodEnemy woodEnemy, woodEnemy2;
    private GroundMap groundMap;

    public GameScreen(MyGdxGame game, OrthographicCamera camera) {
        this.batch = game.batch;
        this.camera = camera;

        setWorld();
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
        groundMap.render();
        debugRenderer.render(world, camera.combined);

        batch.begin();
        characterRender(woodEnemy, batch, delta, world);
        characterRender(woodEnemy2, batch, delta, world);
        characterRender(player, batch, delta, world);
        playerUIRender();
        batch.end();
    }

    @Override
    public void hide() {
        player.dispose();
        woodEnemy.dispose();
        woodEnemy2.dispose();
        groundMap.dispose();
    }

    private void characterRender(Character character, SpriteBatch batch, float delta, World world) {
        if (!character.isDead()) {
            character.render(batch);
            character.update(delta);
        } else if (character.isDead() && character.getBody() != null) {
            character.dispose();
        }
    }

    private void playerUIRender() {
        float playerUiXposition = camera.position.x - camera.viewportWidth / 2;
        float playerUiYposition = camera.position.y + camera.viewportHeight / 2 - Assets.playerUI.getHeight();

        batch.draw(Assets.playerUI, playerUiXposition, playerUiYposition);
        healthRender();
    }

    private void healthRender() {
        int playerHealthXoffset = 42;
        int playerHealthYoffset = 5;
        float playerHealthXposition = camera.position.x - camera.viewportWidth / 2 + playerHealthXoffset;
        float playerHealthYposition = camera.position.y + camera.viewportHeight / 2 - Assets.playerHealth.getHeight() - playerHealthYoffset;

        batch.draw(Assets.playerHealth, playerHealthXposition, playerHealthYposition, Assets.playerHealth.getWidth() * player.getHealth(), Assets.playerHealth.getHeight());
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
        woodEnemy = new WoodEnemy(world, 400, 350);
        woodEnemy2 = new WoodEnemy(world, 400, 150);
    }
}
