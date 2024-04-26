package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.settings.GameSettings;

public class PinkMonster extends DynamicGameObject {
    private static final Texture PINK_MONSTER_TEXTURE = new Texture("characters/Pink_Monster/Pink_Monster.png");
    private static final float PINK_MONSTER_WIDTH = 32;
    private static final float PINK_MONSTER_HEIGHT = 32;
    private static final float speed = 100;

    public PinkMonster(float x, float y) {
        super(x, y, PINK_MONSTER_WIDTH, PINK_MONSTER_HEIGHT);
    }

    public void update() {
        move();
    }

    private void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) this.getPosition().x -= speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) this.getPosition().x += speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) this.getPosition().y += speed * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) this.getPosition().y -= speed * Gdx.graphics.getDeltaTime();

        if (this.getPosition().x < 0) this.getPosition().x = 0;
        if (this.getPosition().x > GameSettings.SCREEN_WIDTH - PINK_MONSTER_WIDTH)
            this.getPosition().x = GameSettings.SCREEN_WIDTH - PINK_MONSTER_WIDTH;
    }

    public void render(SpriteBatch batch) {
        batch.draw(PINK_MONSTER_TEXTURE, this.getPosition().x, this.getPosition().y, PINK_MONSTER_WIDTH, PINK_MONSTER_HEIGHT);
    }

}
