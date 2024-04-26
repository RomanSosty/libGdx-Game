package com.mygdx.game.character;

import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject {
   private final Vector2 velocity;

    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.velocity = new Vector2();
    }

    public Vector2 getVelocity() {
        return velocity;
    }
}
