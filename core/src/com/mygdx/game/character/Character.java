package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public abstract class Character {
    private final World world;
    protected Texture texture;
    protected Sprite sprite;
    protected float stateTime = 0f;
    protected float speed;
    protected Body body;
    private boolean isDead = false;

    public Character(World world, float speed) {
        this.world = world;
        this.speed = speed;
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
        //Data for collison difference
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

    public void render(SpriteBatch batch) {

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
    }

    public float getSpeed() {
        return speed;
    }

    public Body getBody() {
        return body;
    }

    public void update(float delta) {
    }

    public void dispose() {
        texture.dispose();
        world.destroyBody(body);
        body = null;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead() {
        isDead = true;
    }
}
