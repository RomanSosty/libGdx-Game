package com.mygdx.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;

public abstract class GameObject {
    private final World world;
    private final Texture texture;
    private Sprite sprite;
    private Body body;
    private boolean isDestroyed;

    public GameObject(World world, Texture texture) {
        this.world = world;
        this.texture = texture;
    }

    public void makeBody(int x, int y, Object object, World world) {
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
        //Data for collison difference
        body.setUserData(object);
        shape.dispose();
    }

    public Body getBody() {
        return body;
    }

    public void makeSprite() {
        sprite = new Sprite(texture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }

    public void defaultAnimation() {
        sprite.setRegion(texture);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
    }

    public void setIsDestroyed() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void update(float delta) {
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
        body = null;
    }
}
