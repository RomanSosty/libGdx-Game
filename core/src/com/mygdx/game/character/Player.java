package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.inputProcesors.PlayerInputProcessor;
import com.mygdx.game.settings.Assets;

public class Player extends Character {
    private final Texture texture;
    private final Animation<TextureRegion> walkAnimation, attackAnimation;

    private boolean isWalking = false;
    private boolean isAttacking = false;

    public Player(World world) {
        super(world, 50f);
        texture = Assets.playerTexture;

        body = makeBody(150, 250, texture, this);
        walkAnimation = makeAnimation(Assets.playerWalkSheet, 6);
        attackAnimation = makeAnimation(Assets.playerAttack, 6);

        sprite = new Sprite(texture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

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

    private void setAnimation(float delta) {
        stateTime += delta;

        if (isWalking) {
            sprite.setRegion(walkAnimation.getKeyFrame(stateTime, true));
        } else if (isAttacking) {
            sprite.setRegion(attackAnimation.getKeyFrame(stateTime, true));
        } else {
            sprite.setRegion(texture);
        }
    }
}
