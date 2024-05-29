package com.mygdx.game.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;

public class WoodEnemy extends Character {
    private final Animation<TextureRegion> walkAnimation;

    public WoodEnemy(World world, int x, int y) {
        super(world, 30f);
        texture = Assets.woodEnemyTexture;
        body = makeBody(x, y, texture, this);
        walkAnimation = makeAnimation(Assets.woodEnemyWalkSheet, 4);

        sprite = new Sprite(texture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        sprite.setRegion(walkAnimation.getKeyFrame(stateTime, true));
        body.setLinearVelocity(speed, body.getLinearVelocity().y);
    }

    public void turnAroud() {
        speed *= -1;
    }

}
