package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.inputProcesors.PinkMonsterInputProcessor;
import com.mygdx.game.settings.Assets;

public class PinkMonster extends Character {
    private final Sprite sprite;
    private boolean isWalking = false;
    private Animation<TextureRegion> walkAnimation;
    private Body body;
    private float stateTime = 0f;

    public PinkMonster(World world) {
        super(world);

        body = makeBody(150, 250, Assets.playerTexture, this);

        TextureRegion[][] tmp = TextureRegion.split(Assets.walkSheet, Assets.walkSheet.getWidth() / 6, Assets.walkSheet.getHeight());

        Array<TextureRegion> walkFrames = new Array<>();
        for (int i = 0; i < 6; i++) {
            walkFrames.add(tmp[0][i]);
        }

        walkAnimation = new Animation<>(0.1f, walkFrames);
        sprite = new Sprite(Assets.playerTexture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        PinkMonsterInputProcessor inputProcessor = new PinkMonsterInputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public void update(float delta) {
        stateTime += delta;

        if (isWalking) {
            sprite.setRegion(walkAnimation.getKeyFrame(stateTime, true));
        } else {
            sprite.setRegion(Assets.playerTexture);
        }
    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

        sprite.draw(batch);
    }

    public void dispose() {
        Assets.playerTexture.dispose();
    }

    public Body getBody() {
        return this.body;
    }

    public void setIsWalking(boolean isWalking) {
        this.isWalking = isWalking;
    }
}
