package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.world.character.Character;
import com.mygdx.game.world.character.Player;

public class MyRenderer {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    public MyRenderer(SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;
    }

    public void characterRender(Character character, float delta) {
        if (!character.isDestroyed()) {
            character.render(batch);
            character.update(delta);
        } else if (character.isDestroyed() && character.getBody() != null) {
            character.dispose();
        }
    }

    public void healthRender(Player player) {
        int playerHealthXoffset = 42;
        int playerHealthYoffset = 5;
        float playerHealthXposition = camera.position.x - camera.viewportWidth / 2 + playerHealthXoffset;
        float playerHealthYposition = camera.position.y + camera.viewportHeight / 2 - Assets.playerHealth.getHeight() - playerHealthYoffset;

        batch.draw(Assets.playerHealth, playerHealthXposition, playerHealthYposition, Assets.playerHealth.getWidth() * player.getHealth(), Assets.playerHealth.getHeight());
    }

    public void playerUIRender() {
        float playerUiXposition = camera.position.x - camera.viewportWidth / 2;
        float playerUiYposition = camera.position.y + camera.viewportHeight / 2 - Assets.playerUI.getHeight();

        batch.draw(Assets.playerUI, playerUiXposition, playerUiYposition);
    }

}
