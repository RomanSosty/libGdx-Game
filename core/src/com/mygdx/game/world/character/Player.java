package com.mygdx.game.world.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.settings.WalkDirection;
import com.mygdx.game.utils.inputProcesors.PlayerInputProcessor;

public class Player extends Character {
    private Animation<TextureRegion> walkRight, walkUp, walkLeft, walkDown;
    private Animation<TextureRegion> attackAnimation, idleAnimation;
    private boolean isWalking = false;
    private boolean isAttacking = false;
    private float health = 1f;
    private WalkDirection walkDirection = WalkDirection.RIGHT;
    private int score = 0;

    public Player(World world) {
        super(world, 50f, Assets.playerTexture);

        makeBody(150, 250, this, world);
        makeSprite();
        initAnimation();

        PlayerInputProcessor inputProcessor = new PlayerInputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void initAnimation() {
        walkRight = makeAnimation(Assets.playerWalkRight, 6);
        walkLeft = makeAnimation(Assets.playerWalkLeft, 6);
        walkUp = makeAnimation(Assets.playerWalkUp, 4);
        walkDown = makeAnimation(Assets.playerWalkDown, 4);
        attackAnimation = makeAnimation(Assets.playerAttack, 6);
        idleAnimation = makeAnimation(Assets.playerTexture, 4);
    }

    @Override
    public void update(float delta) {
        setAnimation(delta);
    }

    private void setAnimation(float delta) {
        setStateTime(delta);

        if (isWalking && walkDirection == WalkDirection.RIGHT) {
            getSprite().setRegion(walkRight.getKeyFrame(getStateTime(), true));
        } else if (isWalking && walkDirection == WalkDirection.UP) {
            getSprite().setRegion(walkUp.getKeyFrame(getStateTime(), true));
        } else if (isWalking && walkDirection == WalkDirection.LEFT) {
            getSprite().setRegion(walkLeft.getKeyFrame(getStateTime(), true));
        } else if (isWalking && walkDirection == WalkDirection.DOWN) {
            getSprite().setRegion(walkDown.getKeyFrame(getStateTime(), true));
        } else if (isAttacking) {
            getSprite().setRegion(attackAnimation.getKeyFrame(getStateTime(), true));
        } else {
            getSprite().setRegion(idleAnimation.getKeyFrame(getStateTime(), true));
        }
    }

    public void setIsWalking(boolean isWalking) {
        this.isWalking = isWalking;
    }

    public void setWalkDirection(WalkDirection walkDirection) {
        this.walkDirection = walkDirection;
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

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }
}
