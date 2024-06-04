package com.mygdx.game.world.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.world.GameObject;

public abstract class Character extends GameObject {
    private float stateTime = 0f;
    private float speed;

    public Character(World world, float speed, Texture texture) {
        super(world, texture);
        this.speed = speed;
    }

    protected Animation<TextureRegion> makeAnimation(Texture texture, int numOfFrames) {
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / numOfFrames, texture.getHeight());

        Array<TextureRegion> walkFrames = new Array<>();
        for (int i = 0; i < numOfFrames; i++) {
            walkFrames.add(tmp[0][i]);
        }

        return new Animation<>(0.1f, walkFrames);
    }

    public float getSpeed() {
        return speed;
    }

    public void changeDirection() {
        this.speed *= -1;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime += stateTime;
    }

    public void update(float delta) {
    }
}
