package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;

public class WoodEnemy extends Character {
    private final Body body;
    private final Sprite sprite;
    private final Texture texture;
    private final Animation<TextureRegion> walkAnimation;
    private float speed = 30f;

    public WoodEnemy(World world) {
        super(world);
        texture = Assets.woodEnemyTexture;
        body = makeBody(400, 350, texture, this);
        walkAnimation = makeAnimation(Assets.woodEnemyWalkSheet, 4);

        sprite = new Sprite(texture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

    }

    public void update(float delta) {
        stateTime += delta;
        sprite.setRegion(walkAnimation.getKeyFrame(stateTime, true));
        body.setLinearVelocity(speed, body.getLinearVelocity().y);
    }


    public void render(SpriteBatch batch) {

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

        sprite.draw(batch);
    }

    public void turnAroud() {
        speed *= -1;
    }

    public void dispose() {
        texture.dispose();
    }

}
