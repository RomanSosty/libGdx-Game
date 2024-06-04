package com.mygdx.game.world.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;

public class WoodEnemy extends Character {
    private final Animation<TextureRegion> walkAnimation;

    public WoodEnemy(World world, int x, int y) {
        super(world, 30f, Assets.woodEnemyTexture);

        makeBody(x, y, this, world);
        makeSprite();
        walkAnimation = makeAnimation(Assets.woodEnemyWalkSheet, 4);
    }

    @Override
    public void update(float delta) {
        setStateTime(delta);
        getSprite().setRegion(walkAnimation.getKeyFrame(getStateTime(), true));
        getBody().setLinearVelocity(getSpeed(), getBody().getLinearVelocity().y);
    }

    public void turnAroud() {
        changeDirection();
    }
}
