package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.settings.GameSettings;

public class PinkMonster extends DynamicGameObject {
    private static final Texture PINK_MONSTER_TEXTURE = new Texture("Pink_Monster/Pink_Monster.png");
    private static final float PINK_MONSTER_WIDTH = 32;
    private static final float PINK_MONSTER_HEIGHT = 32;
    private static final float yVelocity = 300;
    private static final float xVelocity = 200;
    private boolean jump = false;

    public PinkMonster(float x, float y) {
        super(x, y, PINK_MONSTER_WIDTH, PINK_MONSTER_HEIGHT);
    }

    public void update() {
        if (this.getPosition().y > 0 && !jump)
            this.getPosition().y -= GameSettings.gravity * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) this.getPosition().x -= xVelocity * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) this.getPosition().x += xVelocity * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) jump();

        if (this.getPosition().x < 0) this.getPosition().x = 0;
        if (this.getPosition().x > GameSettings.SCREEN_WIDTH - PINK_MONSTER_WIDTH)
            this.getPosition().x = GameSettings.SCREEN_WIDTH - PINK_MONSTER_WIDTH;

        if (jump) {
            this.getPosition().y += yVelocity * Gdx.graphics.getDeltaTime();

            if (this.getPosition().y > 100) {
                jump = false;
            }
        }
    }

    public void jump() {
        if (!jump) {
            this.getVelocity().y = yVelocity;
            jump = true;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(PINK_MONSTER_TEXTURE, this.getPosition().x, this.getPosition().y, PINK_MONSTER_WIDTH, PINK_MONSTER_HEIGHT);
    }

}
