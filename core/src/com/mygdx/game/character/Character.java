package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public abstract class Character {
    private final World world;
    protected float stateTime = 0f;

    public Character(World world) {

        this.world = world;
    }

    protected Body makeBody(int x, int y, Texture texture, Object object) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(texture.getWidth() / 3, texture.getHeight() / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        body.setGravityScale(0.0f);
        body.setUserData(object);
        shape.dispose();
        return body;
    }

    protected Animation<TextureRegion> makeAnimation(Texture texture, int numOfFrames) {
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / numOfFrames, texture.getHeight());

        Array<TextureRegion> walkFrames = new Array<>();
        for (int i = 0; i < numOfFrames; i++) {
            walkFrames.add(tmp[0][i]);
        }

        return new Animation<>(0.1f, walkFrames);
    }

}
