package com.mygdx.game.world.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.utils.inputProcesors.PlayerInputProcessor;

public class Player extends Character {
    private final Animation<TextureRegion> walkAnimation, attackAnimation;
    private boolean isWalking = false;
    private boolean isAttacking = false;
    private float health = 1f;

    public Player(World world) {
        super(world, 50f, Assets.playerTexture);

        makeBody(150, 250, this, world);
        makeSprite();

        walkAnimation = makeAnimation(Assets.playerWalkSheet, 6);
        attackAnimation = makeAnimation(Assets.playerAttack, 6);

        PlayerInputProcessor inputProcessor = new PlayerInputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void update(float delta) {
        setAnimation(delta);
    }

    public void setIsWalking(boolean isWalking) {
        this.isWalking = isWalking;
    }

    public void setIsAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public float getHealth() {
        return health;
    }

    public void setDownHealth() {
        health -= 0.2f;
        playerDeath();
    }

    public void setUpHealth() {
        if (health < 1f) {
            health += 0.2f;
        }
    }

    public void playerDeath() {
        if (health <= 0.1) {
            setIsDestroyed();
        }
    }

    private void setAnimation(float delta) {
        setStateTime(delta);

        if (isWalking) {
            getSprite().setRegion(walkAnimation.getKeyFrame(getStateTime(), true));
        } else if (isAttacking) {
            getSprite().setRegion(attackAnimation.getKeyFrame(getStateTime(), true));
        } else {
            defaultAnimation();
        }
    }
}
