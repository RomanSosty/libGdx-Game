package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Character {
    private final World world;
    private Body body;

    public Character(World world) {

        this.world = world;
    }

    protected Body makeBody(int x, int y, Texture texture, Object object) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
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

}
